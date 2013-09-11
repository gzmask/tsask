(ns tsask.core
    (:gen-class)
    (:use compojure.core 
          tsask.env
          ring.adapter.jetty
          tsask.wrap)

    (:require [compojure.handler :as handler]
              [me.raynes.laser :as l]
              [ring.middleware.session :as session]
              [ring.middleware.params :as params]
              [ring.middleware.multipart-params :as mulparams]
              [clojure.java.jdbc :as j]
              [clojure.java.io :as io]
              [clojure.java.jdbc.sql :as sql]
              [compojure.route :as route]
              [ring.middleware.json :as json]

              [tsask.csv.crud :as csv]
              [tsask.session.log :as log]))

(defroutes app-routes
  (route/resources "/")
  (GET "/login" [] (log/login))
  (POST "/check" {{username :username password :password} :params session :session} (log/check username password session))
  (GET "/check" {{x :x y :y} :params session :session} (log/check-tsask x y session))
  (GET "/csv/export" {session :session} (wrap-session-verify (csv/export) session))
  (GET "/csv/new" {session :session} (wrap-session-verify (csv/new) session))
  (GET "/csv/payment-report" {session :session} (wrap-session-verify (csv/payment-report) session))
  (GET "/csv/download/:filename" {params :params session :session} (wrap-session-verify (csv/download-csv (:filename params)) session))
  (POST "/csv/create" {params :params session :session} (wrap-session-verify (csv/create params) session)))

(def app
    (params/wrap-params (session/wrap-session (handler/site app-routes))))
   
(defn -main []
      (run-jetty #'app {:port 3000 :join? false}))
