(ns csvexporter.env
  (:use csvexporter.pages.template-pg))

(def SQLDB {:subprotocol "mysql"
                              :subname "//127.0.0.1:3306/isaac"
                              :user "isaac"
                              :password "F=ma"})

(defn err-handler
  [& [opts]]
  error-page)
