(ns csvexporter.core
    (:gen-class)
    (:use compojure.core 
          csvexporter.env
          ring.adapter.jetty
          csvexporter.pages.template-pg
          csvexporter.wrap)

    (:require [compojure.handler :as handler]
              [me.raynes.laser :as l]
              [ring.middleware.session :as session]
              [ring.middleware.params :as params]
              [ring.middleware.multipart-params :as mulparams]
              [clojure.java.jdbc :as j]
              [clojure.java.io :as io]
              [clojure.java.jdbc.sql :as sql]
              [compojure.route :as route]
              [ring.middleware.json :as json]))

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


(defn csv_create [params session]
  (j/insert! SQLDB :CSVt_report 
             {:app_name    (:app_name    params) 
              :address     (:address     params) 
              :phone       (:phone       params) 
              :email       (:email       params) 
              :app_type    (:app_type    params) 
              :app_detail  (:app_detail  params) 
              :invoice_id  (empty-to-nil (:invoice_id  params)) 
              :paid_by     (:paid_by     params) 
              :card_type   (:card_type   params) 
              :payment_amt (empty-to-nil (:payment_amt params))}))

(defn check [username password session]
  (let [user (first (j/query SQLDB (sql/select * :user (sql/where {:username username}))))]
    {:status 302
     :session (assoc session :login (= password (:password user)))
     ;;:headers {"Content-Type" "text/html; charset=UTF-8"}
     :headers {"Location" "/csvs/new"}
     ;;:body "good man"
     }))

(defn csv-new [params session]
  (pages
   [:div
    [:form {:action "/csvs/create" :method "post"}
     [:table {}
      (map (fn [lable input] [:tr [:td [:lable lable]] [:td [:input {:type "text" :name (name input)}]]])
           (map #(str (name %) ": ")
                [:app_name :address :phone :email :app_type :app_detail :invoice_id :paid_by :card_type :payment_amt])
           [:app_name :address :phone :email :app_type :app_detail :invoice_id :paid_by :card_type :payment_amt])
      [:tr
       [:td {:colspan "2" :align ""}
        [:input {:type "submit" :value "submit"}]]]]]]
   "/css/form.css"))


(defn login []
  (pages
   [:div
    [:form {:action "/check" :method "post"}
     [:input {:name "username" :type "text"}]
     [:br]
     [:input {:name "password" :type "password"}]
     [:br]
     [:input {:type "submit" :value "submit"}]]]))


(defroutes app-routes
  (route/resources "/")
  (GET "/login/" [] (login))
  (POST "/check" {{username :username password :password} :params session :session} (check username password session))
  (GET "/csvs/export" [] (export-csv))
  (GET "/csvs/new" {params :params session :session} (wrap-session-verify (csv-new params session) session))
  (POST "/csvs/create" {params :params session :session} (wrap-session-verify (csv_create params session) session)))

(def app
    (params/wrap-params (session/wrap-session (handler/site app-routes))))
   
(defn -main []
      (run-jetty #'app {:port 3000 :join? false}))
