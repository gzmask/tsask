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


(def main-nav
  (let [nav-list [{:content "Form" :title "forms" :href "http://www.tsaskforms.ca/backend.php/forms"}
                  {:content "Order" :title "orders" :href "http://www.tsaskforms.ca/backend.php/orders"}
                  {:content "User" :title "users" :href "http://www.tsaskforms.ca/backend.php/users"}
                  {:content "Payment Report" :title "payment report" :href "/csv/payment-report"}]]
    [:div.mainnav
     [:ul.navlist
      (map (fn [nav] (let [nav-ele (second nav)] 
                       [:li [(keyword (str "a.nav" (+ 1 (first nav))))
                             {:href (:href nav-ele) :title  (:title nav-ele)}
                             [:span (:content nav-ele)]]]))
           (map-indexed vector nav-list))]
     [:div.clear]]))

(def sub-nav
  [:div.subnav
   [:a.newform {:href "http://www.tsaskforms.ca/backend.php/forms/new" :title "new form"} "New Form"]
   [:a.newuser {:href "http://www.tsaskforms.ca/backend.php/users/new" :title "new user"} "New User"]])

(defn pages 
  "get page by pagename"
  [page & {:keys [main-nav sub-nav css-files js-files]
            :or {main-nav main-nav
                 sub-nav sub-nav}}]
  (html5
   [:head
    (include-css "/css/common.css")
    (include-css "/css/font-awesome.css")
    (apply include-css css-files)
    (apply include-js js-files)
    [:title "technical safety authority interface design_login"]]
   [:body
    [:div.wrapper
     [:div.header
      [:div.icon_logo
       [:a {:href "#" :title "technical safety authority interface design"}
        [:img {:src "/images/icon_logo.jpg" :alt "technical safety authority interface design"}]]]
      [:div.icon_exit
       [:a {:href "#!" :title "log out"}]]]
     [:div.container
      main-nav
      [:div.mainbox
       sub-nav
       page]]]]))

(def error-page
  (pages
   [:dvi
    [:h1 "Ooooops..."]
    [:h3 "Server is busying..."]]))
