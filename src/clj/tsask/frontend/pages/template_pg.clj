(ns tsask.frontend.pages.template-pg
  (:use hiccup.core
        hiccup.page
        hiccup.util))

;these are like atoms that can be changed
(def ^:dynamic *js-files* nil)
(def ^:dynamic *css-files* nil)
(def ^:dynamic *js-css-files* nil)
(def ^:dynamic *main-nav*
  (let [nav-list [{:id "carts" :content "Cart" :title "carts" :href "/carts"}
                  {:id "calendar" :content "Calendar" :title "calendar" :href "/calendar"}
                  {:id "signup" :content "Signup" :title "signup" :href "/fuser/signup"}]]
    [:div.mainnav
     [:ul.navlist
      (map (fn [nav] (let [nav-ele (second nav)] 
                       [:li [(keyword (str "a.nav" (+ 1 (first nav))))
                             {:id (:id nav-ele) :href (:href nav-ele) :title (:title nav-ele)}
                             [:span (:content nav-ele)]]]))
           (map-indexed vector nav-list))]
     [:div.clear]]))
(def ^:dynamic *sub-nav*
  [:div.subnav
   [:a.newform {:href "/form/new" :title "new form"} "New Form"]
   [:a.newuser {:href "/fuser/new" :title "new user"} "New User"]])

(def user-files ["/css/fcommon.css" "/css/fix.css" "/js/jquery-1.7.2.js" "/js/DD_belatedPNG.js" "/js/layout.js" "/js/login.js" "/js/user.js"])

(defn include-js-css [files]
  (for [f files]
    (if (.endsWith f "js")
      (include-js f)
      (include-css f))))

(defn commit-page [page]
   (html5
    [:head
     (apply include-css ["/css/common.css" "/css/fix.css"])
     [:title "technical safety authority of saskatchewan"]]
    [:body
     [:div.wrapper
      [:div.header
       [:div.icon_logo
        [:img {:src "/images/icon_logo.jpg" :alt "technical safety authority interface design" :title "technical safety authority interface design"}]]]
      [:div.container
       [:div.mainbox
        page]]]]))

(defn pages 
  "get page by pagename"
  [page]
  (html5
   [:head
    (apply include-css *css-files*)
    (apply include-js *js-files*)
    (include-js-css *js-css-files*)
    [:title "technical safety authority interface design_login"]]
   [:body
    [:div.wrapper
     [:div.header
      [:div.icon_logo
       [:a {:href "/" :title "technical safety authority interface design"}
        [:img {:src "/images/icon_logo.jpg" :alt "technical safety authority interface design"}]]]]
     [:div.container
      *main-nav*
      (include-js "/js/highlight-active-tab.js")
      [:div.mainbox
       *sub-nav*
       page]]]]))

(def error-page
  (pages
   [:dvi
    [:h1 "Ooooops..."]
    [:h3 "Server is busying..."]]))

