(ns tsask.env
  (:use tsask.pages.template-pg))

#_(def SQLDB {:subprotocol "mysql"
                              :subname "//127.0.0.1:3306/authority"
                              :user "root"
                              :password "121212"})

(def SQLDB {:subprotocol "mysql"
             :subname "//localhost:3306/isaac"
             :user "isaac"
             :password "F=ma"})

(defn err-handler
  [& [opts]]
  error-page)

(def CSV_ROOT_PATH "resources")

(def MAIL_TEMPLATE
  ^{:host "mail.melcher.ca"
    :user "ray@melcher.ca"
    :pass "121212"}
  {:from "Ray Lei"})
