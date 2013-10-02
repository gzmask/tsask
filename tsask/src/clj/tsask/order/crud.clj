(ns tsask.order.crud
  (:use tsask.env
        tsask.util)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as sql]))

(defn create [params]
  (j/insert! SQLDB :sa_orders
             {:order_content (:order_content params)
              :form_name (:form_name params)
              :created_at (java.util.Date.)
              :updated_at (java.util.Date.)}))
