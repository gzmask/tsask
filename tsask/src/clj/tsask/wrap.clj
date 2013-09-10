(ns tsask.wrap
  (:use tsask.env))

(comment ;; comment begin
(defn- wrap-session-verify
  [handler]
  (fn [request]
    (let [request request]
      (if (:login (:session request))
        (handler request)
        {:status 302
         :headers {"Location" "/login"}
         :body ""}))))

(defmacro wrap-session-verify
  [handler]
  `(fn [request#]
     (if (:login (:session request#))
       (~handler request#)
       {:status 302
        :headers {"Location" "/login"}
        :body ""})))

) ;; comment end

(defmacro wrap-session-verify
  [handle-body session]
  `(if (:login ~session)
     ~handle-body
     {:status 302
      :headers {"Location" "/login"}
      :body ""}))


(defmacro wrapped-by-try-catch
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

(defmacro wrap-error-handler
  [handler]
  `(wrapped-by-try-catch ~handler (err-handler)))

(defmacro wrap-error-handler+session-verify
  [handler session]
  `(wrap-error-handler (wrap-session-verify ~handler ~session)))
