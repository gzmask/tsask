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
    [:title "technical safety authority interface design_login"]
    (include-css "/css/common.css")
    (if include-files
      (apply include include-files))]
   [:body
    [:div.wrapper
     [:div.header
      [:div.icon_logo
       [:a {:href "#" :title "technical safety authority interface design"}
        [:img {:src "/images/icon_logo.jpg" :alt "technical safety authority interface design"}]]]
      [:div.icon_exit
       [:a {:href "#!" :title "log out"}]]]
     [:div.container
      [:div.mainnav
       [:ul.navlist
        [:li [:a.nav1 {:href "http://www.tsaskforms.ca/backend.php/forms" :title "forms"} [:span "Form"]]]
        [:li [:a.nav2 {:href "http://www.tsaskforms.ca/backend.php/orders" :title "orders"} [:span "Order"]]]
        [:li [:a.nav3 {:href "http://www.tsaskforms.ca/backend.php/users" :title "users"} [:span "User"]]]
        [:li [:a.nav4 {:href "/csv/payment-report" :title "payment report"} [:span "Payment Report"]]]]
       [:div.clear]]
      [:div.mainbox
       [:div.subnav
        [:a.newform {:href "#!" :title "new form"} "New Form"]
        [:a.newuser {:href "#!" :title "new user"} "New User"]]
       page]]]]))


(def error-page
  (pages
   [:dvi
    [:h1 "Ooooops..."]
    [:h3 "Server is busying..."]]))
