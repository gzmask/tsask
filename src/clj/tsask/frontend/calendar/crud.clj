(ns tsask.frontend.calendar.crud
  (:use tsask.env
        tsask.util
        hiccup.core
        hiccup.page
        hiccup.util)
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

(def CLNDR-template
  [:script 
   {:type "text/template"
    :id "template-calendar"}
   [:div.clndr-controls
    [:div.clndr-previous-button "&lsaquo;"]
    [:div.month "<%= month %>"]
    [:div.clndr-next-button "&rsaquo;"]]
   [:div.clndr-grid
    [:div.days-of-the-week 
     "<% _.each(daysOfTheWeek, function(day) { %>"
     [:div.header-day "<%= day %>"]
     "<% }); %>"
     [:div.days 
      "<% _.each(days, function(day) { %>"
      "<div class=\"<%= day.classes %>\"><%= day.day %></div>"
      "<% }); %>"
      ]]] 
   [:div.clndr-today-button "today"]])

(defn index [session]
    (binding [template/*js-css-files* template/calendar-files 
              template/*sub-nav* nil]
      (template/pages
        [:div#calendar_target.cal2 "this is calendar. Clndr is ready."
         CLNDR-template
         (include-js "/js/calendar/cal.js")])))

