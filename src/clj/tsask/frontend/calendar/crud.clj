(ns tsask.frontend.calendar.crud
  (:use tsask.env
        tsask.util
        hiccup.core
        hiccup.page
        hiccup.util)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.io :as io]
            [ring.util.response :as ut :refer [response]]
            [tsask.order.crud :as order]
            [tsask.form.crud :as form]
            [tsask.frontend.pages.template-pg :as template]
            [com.reasonr.scriptjure :as sj]
            [clojure.java.jdbc.sql :as sql]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(def one-day 86400000)

(comment "debug repls"
  (j/query SQLDB (sql/select [:id :title :description :form_id :start :end] :calevent))
  (j/query SQLDB (sql/select [:created_at] :sa_orders))
  (j/insert! SQLDB :calevent 
             {:title "form 2" 
              :description "this is description of form 2" 
              :form_id 1 
              :start (- (.getTime (java.util.Date.)) one-day) 
              :end (+ (.getTime (java.util.Date.)) one-day)})
  (java.util.Date.)
  (type (.getTime (java.util.Date.))))

(defn calevents
  "json response forms that should be in calendar"
  []
  (let [events (j/query SQLDB 
                        (sql/select [:id :title :description :form_id :start :end] :calevent))]
    (ut/response events)))

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
   [:div.clndr-today-button "today"]
   [:div.event-listing
    [:div.event-listing-title "EVENTS THIS MONTH" ]
    "<% _.each(eventsThisMonth, function(event) { %>"
    [:div.event-item 
     [:div.event-item-name "<%= event.title %>"]
     [:div.event-item-location "<%= event.description %>"]]
    "<% }); %>" ]])

(def calendar-files  ["/css/fcommon.css" "/css/fix.css" "/vendor/clndr.css" "/js/jquery-1.7.2.js" "/js/DD_belatedPNG.js" "/vendor/moment.min.js" "/vendor/underscore-min.js" "/vendor/clndr.min.js" "/js/layout.js" "/js/login.js"])

(defn index [session]
    (binding [template/*js-css-files* calendar-files 
              template/*sub-nav* nil]
      (template/pages
        [:div#calendar_target.cal2 "this is calendar. Clndr is ready."
         CLNDR-template
         (include-js "/js/calendar/cal.js")])))

