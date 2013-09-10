(ns tsask.core
    (:gen-class)
    (:use compojure.core 
          tsask.env
          ring.adapter.jetty
          tsask.pages.template-pg
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

              [tsask.csv.crud :as csv]))

(defroutes app-routes
  (route/resources "/")
  (GET "/login/" [] (login))
  (POST "/check" {{username :username password :password} :params session :session} (check username password session))
  (GET "/csvs/export" [] (csv/export))
  (GET "/csvs/new" {params :params session :session} (wrap-session-verify (csv/new params session) session))
  (POST "/csvs/create" {params :params session :session} (wrap-session-verify (csv/create params session) session)))

(def app
    (params/wrap-params (session/wrap-session (handler/site app-routes))))
   
(defn -main []
      (run-jetty #'app {:port 3000 :join? false}))
