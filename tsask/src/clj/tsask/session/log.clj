(ns tsask.session.log
  (:use tsask.pages.template-pg
        tsask.env)
  (require [clojure.java.jdbc :as j]
           [clojure.java.jdbc.sql :as sql]))

(defn login []
  (binding [*css-files* ["/css/common.css" "/css/main.css"]]
  (pages
   [:div
    [:form {:action "/check" :method "post"}
     [:input {:name "username" :type "text"}]
     [:br]
     [:input {:name "password" :type "password"}]
     [:br]
     [:input {:type "submit" :value "submit"}]]])))

(defn check [username password session]
  (let [user (first (j/query SQLDB (sql/select * :user (sql/where {:username username}))))]
    {:status 302
     :session (assoc session :login (= password (:password user)))
     :headers {"Location" "/csv/payment-report"}}))

(defn check-tsask [x y session]
  (let [x (Integer/parseInt x)
        y (Integer/parseInt y)]
    {:status 302
     :headers {"Location" "https://www.tsaskforms.ca/backend.php"}
     :session (assoc session :login (= x (int (/ 314 y))))}))
