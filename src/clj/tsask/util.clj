(ns tsask.util
  (:use tsask.env)
  (:import java.util.Date
           java.util.Calendar))

(defmulti strftime 
  "Format time t according to format string fmt."
  (fn [fmt t] (class t)))

(defmethod strftime Date
  [fmt t]
  ; Convert strftime to String.format format (e.g. %m -> %1$tm)
  (let [fmt (.replaceAll fmt "%([a-zA-Z])" "%1\\$t$1")]
    (format fmt t)))

(defmethod strftime Long
  [fmt t]
  (strftime fmt (Date. t)))

(defmethod strftime Calendar
  [fmt t]
  (strftime fmt (.getTime t)))

(defmacro wrap-session-verify
  [session & handler-bodys]
  `(if (:login ~session)
     ~@handler-bodys
     {:status 302
      :headers {"Location" "/login"}
      :body ""}))

(defmacro wrap-with-try-catch
  [handler & error-handlers]
  `(try
     ~handler
     (catch Exception e#
       (.printStackTrace e#)
       ~@error-handlers)))

(defn empty-to-nil [value]
  (if (empty? value)
    nil
    value))

(defn get_last_id [record]
  ((keyword "last_insert_rowid()") (first record)))
  

(defmacro wrap-error-handler
  [handler]
  `(wrap-with-try-catch ~handler (err-handler)))

(defmacro wrap-error-handler+session-verify
  [handler session]
  `(wrap-error-handler (wrap-session-verify ~handler ~session)))
