(ns tsask.core
    (:gen-class)
    (:use compojure.core 
          tsask.env
          ring.adapter.jetty
          tsask.wrap
          ;; use to test
          clojure.pprint)

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
              [tsask.session.log :as log]
              [tsask.form.crud :as form]))

(defn test-url [request]
  (pprint request))

(defroutes app-routes
  (route/resources "/")
  (GET "/" [] tsask.pages.template-pg/home-pg)
  (GET "/test-url" request (test-url request))

  (GET "/login" [] (log/login))
  (POST "/check" {{username :username password :password} :params session :session} (log/check username password session))
  (GET "/check" {{x :x y :y} :params session :session} (log/check-tsask x y session))

  (GET "/forms" {{sort :sort sort-type :sort_type} :params} (form/index sort sort-type))
  (GET "/form/:id/delete" {{id :id} :params} (form/delete id))
  (GET "/form/:id/edit" {{id :id} :params} (form/edit id))
  (GET "/form/:id/view" {{id :id} :params} (form/view id))

  (GET "/csv/export" {session :session} (wrap-session-verify session (csv/export)))
  (GET "/csv/new" {session :session} (wrap-session-verify session (csv/new)))
  (GET "/csv/payment-report" {session :session} (wrap-session-verify session (csv/payment-report)))
  (GET "/csv/download/:filename" {params :params session :session} (wrap-session-verify session (csv/download-csv (:filename params))))
  (POST "/csv/create" {params :params session :session} (wrap-session-verify session (csv/create params))))

(def app
    (params/wrap-params (session/wrap-session (handler/site app-routes))))
   
(defn -main []
      (run-jetty #'app {:port 3000 :join? false}))
