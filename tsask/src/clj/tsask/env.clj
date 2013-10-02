(ns tsask.env
  (:use tsask.pages.template-pg))

(def SQLDB {:subprotocol "mysql"
                              :subname "//127.0.0.1:3306/authority"
                              :user "root"
                              :password "121212"})


#_(def SQLDB {:subprotocol "mysql"
                              :subname "//127.0.0.1:3306/isaac"
                              :user "isaac"
                              :password "F=ma"})

(defn err-handler
  [& [opts]]
  error-page)

(def CSV_ROOT_PATH "resources")

(def MAIL_TEMPLATE
  ^{:host "smtp.melcher.ca"
    :user "ray"
    :pass "password need you to complete"}
  {:from "Ray Lei"})
