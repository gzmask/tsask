(ns csvexporter.core
    (:gen-class)
    (:use compojure.core 
          csvexporter.env
          ring.adapter.jetty)
    (:require [compojure.handler :as handler]
              [me.raynes.laser :as l]
              [ring.middleware.session :as session]
              [ring.middleware.params :as params]
              [ring.middleware.multipart-params :as mulparams]
              [clojure.java.jdbc :as j]
              [clojure.java.io :as io]
              [clojure.java.jdbc.sql :as sql]
              [compojure.route :as route]))

(defn export-csv []
  (let [reports 
        (j/query SQLDB (sql/select * :CSV_report))
        csv_rows (for [r reports] (str (:id r) "," (:app_name r) "," (:address r) "," 
                                       (:phone r) "," (:email r) "," (:app_type r) "," 
                                       (:app_detail r) "," (:invoice_id r) "," (:paid_by r) "," 
                                       (:card_type r) "," (:payment_amt r) ",\n"))
        csv_str (reduce 
                  (fn [r1 r2] 
                    (str r1 r2)) 
                  (str "id," "applicant name," "address," 
                       "phone number," "email address," "application type," 
                       "application detail," "invoice id," "paid by," 
                       "card type," "payment amount," "\n") 
                  csv_rows)]
    (do
      (spit "resources/public/export.csv" csv_str :append false)
      (l/document
        (l/parse (io/file "resources/templates/csv_export.html"))
        (l/class= "content")
        (l/content "The database of order has been exported:")))))

(defn csv_create [params]
  (j/insert! SQLDB :CSV_report 
             {:app_name (:app_name params) 
              :address (:address params) 
              :phone (:phone params) 
              :email (:email params) 
              :app_type (:app_type params) 
              :app_detail (:app_detail params) 
              :invoice_id (:invoice_id params) 
              :paid_by (:paid_by params) 
              :card_type (:card_type params) 
              :payment_amt (:payment_amt params)}))

(defroutes app-routes
  (route/resources "/")
  (GET "/csvs/export" [] (export-csv))
  (POST "/csvs/create" {params :params} (csv_create params)))

(def app
    (params/wrap-params (session/wrap-session (handler/site app-routes))))
   
(defn -main []
      (run-jetty #'app {:port 3000 :join? false}))
