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
              [clojure.java.io :refer [file]]
              [clojure.java.jdbc.sql :as sql]
              [compojure.route :as route]))

(defn export-csv []
  (l/document
    (l/parse (file "resources/templates/index.html"))
    (l/class= "content")
    (l/content "injected content")))

(defn csv [params]
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
   (POST "/csvs/create" {params :params} (csv params)))

(def app
    (params/wrap-params (session/wrap-session (handler/site app-routes))))
   
(defn -main []
      (run-jetty #'app {:port 3000 :join? false}))
