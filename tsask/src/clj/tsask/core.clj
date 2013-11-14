(ns tsask.core
    (:gen-class)
    (:use compojure.core 
          tsask.env
          ring.adapter.jetty
          tsask.util)
    (:require [compojure.handler :as handler]

              [ring.middleware.session :as session]
              [ring.middleware.params :as params]
              [ring.middleware.multipart-params :as mulparams]
              [ring.middleware.json :as json]

              [clojure.java.jdbc :as j]
              [clojure.java.io :as io]
              [clojure.java.jdbc.sql :as sql]

              [compojure.route :as route]

              [tsask.csv.crud :as csv]
              [tsask.session.log :as log]
              [tsask.form.crud :as form]
              [tsask.order.crud :as order]))


(defn test-get [params]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hello, " (:name params))})
(defroutes app-routes
  (route/resources "/")
  (GET "/" [] tsask.pages.template-pg/home-pg)
  (GET "/test-get" {params :params} (test-get params))

  (GET "/login" [] (log/login))
  (POST "/check" {{username :username password :password} :params session :session} (log/check username password session))
  (GET "/check" {{x :x y :y} :params session :session} (log/check-tsask x y session))

  (GET "/forms" {{sort :sort sort-type :sort_type} :params} (form/index sort sort-type))
  (GET "/form/:id/delete" {{id :id} :params} (form/delete id))
  (GET "/form/:id/edit" {{id :id} :params} (form/edit id))
  (GET "/form/:id/view" {{id :id} :params} (form/view id))
  (GET "/form/:id/copy" {{id :id} :params} (form/copy id))
  (GET "/form/new" [] (form/new))
  (POST "/form/create" {params :params} (form/create params))
  (POST "/form/:id/create" {params :params} (form/update params))
  (POST "/form/commit" {params :params} (form/commit params))

  (GET "/orders" {{sort :sort sort-type :sort_type} :params} (order/index sort sort-type))
  (GET "/order/:id/delete" {{id :id} :params} (order/delete id))
  (GET "/order/:id/view" {{id :id} :params} (order/view id))
  (POST "/order/delete-selected" {params :params} (order/delete-selected params))

  (GET "/csv/export" {session :session params :params} (wrap-session-verify session (csv/export params)))
  (GET "/csv/new" {session :session} (wrap-session-verify session (csv/new)))
  (GET "/csv/payment-report" {session :session} (wrap-session-verify session (csv/payment-report)))
  (GET "/csv/download/:filename" {params :params session :session} (wrap-session-verify session (csv/download-csv (:filename params))))
  (POST "/csv/create" {params :params} (csv/create params)))

(def app
    (params/wrap-params (session/wrap-session (handler/site app-routes))))
   
(defn -main []
      (run-jetty #'app {:port 3000 :join? false}))
