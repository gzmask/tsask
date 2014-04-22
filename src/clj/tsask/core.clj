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
              [tsask.order.crud :as order]
              [tsask.user.crud :as user]
              [tsask.file.crud :as file]))


(defn test-get [params]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "Hello, " (:name params))})

(defroutes app-routes
  (route/resources "/")
  (GET "/" {session :session} (wrap-session-verify session tsask.pages.template-pg/home-pg))
  (GET "/test-get" {params :params} (test-get params))

  (GET "/login" [] (log/login))
  (GET "/logout" {session :session} (log/logout session))
  (POST "/check" {{username :username password :password} :params session :session} (log/check username password session))
  (GET "/check" {{x :x y :y} :params session :session} (log/check-tsask x y session))

  (GET "/forms" {{sort :sort sort-type :sort_type} :params session :session} (wrap-session-verify session (form/index sort sort-type)))
  (GET "/form/:id/delete" {{id :id} :params session :session} (wrap-session-verify session (form/delete id)))
  (GET "/form/:id/edit" {{id :id} :params session :session} (wrap-session-verify session (form/edit id)))
  (GET "/form/:id/view" {{id :id} :params} (form/view id))
  (GET "/form/:id/copy" {{id :id} :params session :session} (wrap-session-verify session (form/copy id)))
  (GET "/form/new" {session :session} (wrap-session-verify session (form/new)))
  (POST "/form/create" {params :params session :session} (wrap-session-verify session (form/create params)))
  (POST "/form/:id/create" {params :params session :session} (wrap-session-verify session (form/update params)))
  (POST "/form/commit" {params :params} (form/commit params))

  (GET "/orders" {{sort :sort sort-type :sort_type page :page} :params session :session} (wrap-session-verify session (order/index page sort sort-type)))
  (GET "/order/:id/delete" {{id :id} :params session :session} (wrap-session-verify session (order/delete id)))
  (GET "/order/:id/view" {{id :id} :params session :session} (wrap-session-verify session (order/view id)))
  (POST "/order/delete-selected" {params :params session :session} (wrap-session-verify session (order/delete-selected params)))

  (GET "/users" {{sort :sort sort-type :sort_type} :params session :session} (wrap-session-verify session (user/index sort sort-type)))
  (GET "/user/new" {session :session} (wrap-session-verify session (user/new)))
  (POST "/user/create" {params :params session :session} (wrap-session-verify session (user/create params)))
  (POST "/user/:id/create" {params :params session :session} (wrap-session-verify session (user/update params)))
  (GET "/user/:id/edit" {{id :id} :params session :session} (wrap-session-verify session (user/edit id)))
  (GET "/user/:id/delete" {{id :id} :params session :session} (wrap-session-verify session (user/delete id)))

  (GET "/csv/export" {session :session params :params} (wrap-session-verify session (csv/export params)))
  (GET "/csv/new" {session :session} (wrap-session-verify session (csv/new)))
  (GET "/csv/payment-report" {session :session} (wrap-session-verify session (csv/payment-report)))
  (GET "/csv/download/:filename" {params :params session :session} (wrap-session-verify session (csv/download-csv (:filename params))))
  (POST "/csv/create" {params :params} (csv/create params))

  (GET "/files" {session :session} (wrap-session-verify session (file/files)))
  (GET "/files/:filename" {params :params session :session} (wrap-session-verify session (file/view-file (:filename params))))
  (POST "/file/upload" {params :params} (file/upload-file params)))

(def app
    (params/wrap-params (session/wrap-session (handler/site app-routes))))

(defn -main []
      (run-jetty #'app {:port 80
                        :join? false 
                        :ssl? true
                        :ssl-port 443
                        :keystore "/etc/keystore"
                        :key-password "melcher.ca123"
                        :client-auth :need}))
