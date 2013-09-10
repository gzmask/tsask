(ns tsask.pages.template-pg
  (:use hiccup.core
        hiccup.page
        hiccup.util))


(defn include
  [& files]
  (for [f files]
    (if (.endsWith f "js")
      [:script {:type "text/javascript" :src (to-uri f)}]
      [:link {:type "text/css" :href (to-uri f) :rel "stylesheet"}])))

(defn pages [page & include-files]
  "get page by pagename"
  (html5
   [:head
    [:title "csv exporter 欢迎使用"]
    (if include-files
      (apply include include-files))]
   [:body page]))


(def error-page
  (pages
   [:dvi
    [:h1 "Ooooops..."]
    [:h3 "Server is busying..."]]))
