(ns tsask.frontend.calendar.crud
  (:use tsask.env
        tsask.util)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.io :as io]
            [tsask.order.crud :as order]
            [tsask.form.crud :as form]
            [tsask.frontend.pages.template-pg :as template]
            [com.reasonr.scriptjure :as sj]
            [clojure.java.jdbc.sql :as sql]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(defn index [session]
    (binding [template/*js-css-files* template/calendar-files 
              template/*sub-nav* nil]
      (template/pages
        [:div "this is calendar. Clndr is ready."])))
