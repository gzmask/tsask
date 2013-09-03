(ns csvexporter.core
    (:gen-class)
    (:use compojure.core 
          ring.adapter.jetty)
    (:require [compojure.handler :as handler]
              [ring.middleware.session :as session]
              [ring.middleware.params :as params]
              [ring.middleware.multipart-params :as mulparams]
              [compojure.route :as route]))

(defn csv [params]
  (println params))

(defroutes app-routes
  (route/resources "/") 
   (POST "/csvs/create" {params :params} (csv params)))

(def app
    (params/wrap-params (session/wrap-session (handler/site app-routes))))
   
(defn -main []
      (run-jetty #'app {:port 3000 :join? false}))
