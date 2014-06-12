(ns tsask.file.crud
  (:use tsask.env
        tsask.util
        tsask.pages.template-pg
        com.reasonr.scriptjure
        postal.core

        hiccup.page
        hiccup.core)
  (:require [tsask.csv.crud :as csv]
            [clojure.java.jdbc :as j]
            [tsask.order.crud :as order]
            [clojure.java.jdbc.sql :as sql]
            [clj-http.client :as client]
            [net.cgrand.enlive-html :as html]
            [me.raynes.laser :as l]
            [clojure.java.io :as io]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(defn index []
  (binding [*js-css-files* forms-files]
    (pages
      [:form {:method "post" :action "/file/upload" :enctype "multipart/form-data"}
        [:input {:type "file" :name "real-input"}]
        [:input {:type "submit" :value "upload"}]])))

(defn files []
  (binding [*js-css-files* orders-files]
    (pages
      [:table.fcc_tab {:width "100%" :cellspacing "0" :cellpadding "0" :border "0"}
      (let [fs (file-seq (io/file "resources/public/files"))]
        (for [f fs]
          (let [fname (subs (str f) (inc (.lastIndexOf (str f) "/")))]
            (if (not (= "files" fname))
              [:tr 
                [:td {:width "500px"} [:a {:target "_blank" :href (str "/files/" fname)} (str fname)]]
                [:td [:a {:href (str "/file/delete/" fname)} "Delete"]]]))))])))

(defn delete-file [filename]
  (io/delete-file (str "resources/public/files/" filename))
  (redirect "/files"))

(defn view-file [filename]
  {:status 200
   :body (io/file (str "resources/public/files/" filename))})

