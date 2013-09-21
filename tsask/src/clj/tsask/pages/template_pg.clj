(ns tsask.pages.template-pg
  (:use hiccup.core
        hiccup.page
        hiccup.util))

(def js-file-list
  [
   "/js/control/RadioButton.js"
   "/js/control/DropDown.js"
   "/js/control/FileUpload.js"
   "/js/control/Payment.js"
   "/js/control/SubmitButton.js"
   "/js/control/TextBox.js"
   "/js/control/Email.js"
   "/js/control/Phone.js"
   "/js/control/TextArea.js"
   "/js/control/Number.js"
   "/js/control/Address.js"
   "/js/control/Heading.js"
   "/js/control/ClientEmail.js"
   "/js/control/CheckBox.js"
   "/js/control/DateTime.js"
   "/js/control/BirthDatePicker.js"
   "/js/control/UniqueId.js"
   "/js/control/FullName.js"
   "/js/control/form/ContentUniqueId.js"
   "/js/control/form/ContentNumber.js"
   "/js/control/form/FormEmail.js"
   "/js/control/form/ContentPayment.js"
   "/js/control/form/FormRadioButton.js"
   "/js/control/form/FormTextBox.js"
   "/js/control/form/ContentHeading.js"
   "/js/control/form/FormBirthDatePicker.js"
   "/js/control/form/FormFullName.js"
   "/js/control/form/FormPhone.js"
   "/js/control/form/FormDropDown.js"
   "/js/control/form/ContentTextArea.js"
   "/js/control/form/FormAddress.js"
   "/js/control/form/FormCheckBox.js"
   "/js/control/form/ContentDateTime.js"
   "/js/control/form/FormClientEmail.js"
   "/js/control/ResetButton.js"
   "/js/jquery.min.js"
   "/js/layout.js"
   "/js/jquery-1.7.2.js"
   "/js/form_design.js"
   "/js/login.js"
   "/js/highlight-active-tab.js"
   "/js/form_commit.js"
   ])

(defn include
  [& files]
  (for [f files]
    (if (.endsWith f "js")
      [:script {:type "text/javascript" :src (to-uri f)}]
      [:link {:type "text/css" :href (to-uri f) :rel "stylesheet"}])))


(def main-nav
  (let [nav-list [{:id "forms" :content "Form" :title "forms" :href "http://www.tsaskforms.ca/backend.php/forms"}
                  {:id "orders" :content "Order" :title "orders" :href "http://www.tsaskforms.ca/backend.php/orders"}
                  {:id "users" :content "User" :title "users" :href "http://www.tsaskforms.ca/backend.php/users"}
                  {:id "payment-report" :content "Payment Report" :title "payment report" :href "/csv/payment-report"}]]
    [:div.mainnav
     [:ul.navlist
      (map (fn [nav] (let [nav-ele (second nav)] 
                       [:li [(keyword (str "a.nav" (+ 1 (first nav))))
                             {:id (:id nav-ele) :href (str (:href nav-ele) "#" (:id nav-ele)) :title  (:title nav-ele)}
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
    (include-css "/css/fix.css")
    (apply include-js js-file-list)
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
       [:a {:href "#!" :title "log out"} "log out"]]]
     [:div.container
      main-nav
      (include-js "/js/highlight-active-tab.js")
      [:div.mainbox
       sub-nav
       page]]]]))

(def home-pg (pages nil))

(def error-page
  (pages
   [:dvi
    [:h1 "Ooooops..."]
    [:h3 "Server is busying..."]]))
