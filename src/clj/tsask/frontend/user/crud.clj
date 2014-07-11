(ns tsask.frontend.user.crud
  (:use tsask.env
        tsask.util
        tsask.pages.template-pg
        com.reasonr.scriptjure
        digest
        hiccup.page
        hiccup.core)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as sql]
            [clj-http.client :as client]
            [net.cgrand.enlive-html :as html]
            [me.raynes.laser :as l]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(defn edit [id]
  (binding [*js-css-files* user-files]
    (let [user (first (j/query SQLDB
                               (sql/select [:id :username :email_address :first_name :last_name] :user
                                           (sql/where {:id id}))))]
      (user-design-pages user))))

(defn new []
  (binding [*js-css-files* user-files]
    (user-design-pages)))

(defn create [params]
  (j/insert! SQLDB :user
             {:username 	(:username params)
              :email_address	(:email_address params)
              :first_name 	(:first_name params)
              :last_name        (:last_name params)
              :password         (digest/sha-256 (:password params))
              :created_at	(.getTime (java.util.Date.))
              :updated_at	(.getTime (java.util.Date.))})
  (redirect "/users"))

(defn update [params]
  (if (empty? (:password params))
    (j/update! SQLDB :user
               {:username 	(:username params)
                :email_address	(:email_address params)
                :first_name 	(:first_name params)
                :last_name      (:last_name params)
                :updated_at	(.getTime (java.util.Date.))}
              (sql/where {:id (:id params)}))
    (j/update! SQLDB :user
               {:username 	(:username params)
                :email_address	(:email_address params)
                :first_name 	(:first_name params)
                :last_name      (:last_name params)
                :password       (digest/sha-256 (:password params))
                :updated_at	(.getTime (java.util.Date.))}
              (sql/where {:id (:id params)})))
  (redirect "/users"))
