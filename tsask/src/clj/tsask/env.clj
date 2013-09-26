(ns tsask.env
  (:use tsask.pages.template-pg))

(def SQLDB {:subprotocol "mysql"
                              :subname "//127.0.0.1:3306/authority"
                              :user "root"
                              :password "121212"})

(defn err-handler
  [& [opts]]
  error-page)

(def CSV_ROOT_PATH "resources")
