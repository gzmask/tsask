(ns tsask.pages.template-pg
  (:use hiccup.core
        hiccup.page
        hiccup.util))

(def ^:dynamic *js-files* nil)

(def ^:dynamic *css-files* nil)
(def ^:dynamic *js-css-files* nil)

(def ^:dynamic *main-nav*
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


(defn include-js-css [files]
  (for [f files]
    (if (.endsWith f "js")
      (include-js f)
      (include-css f))))


(def form-view-files (.split (slurp "resources/public/html/form-view.files") "\n"))
(def form-new-files (.split (slurp "resources/public/html/new-form.files") "\n"))
(def form-edit-files (.split (slurp "resources/public/html/form-edit.files") "\n"))
(def forms-files (.split (slurp "resources/public/html/forms.files") "\n"))

(def ^:dynamic *sub-nav*
  [:div.subnav
   [:a.newform {:href "http://www.tsaskforms.ca/backend.php/form/new" :title "new form"} "New Form"]
   [:a.newuser {:href "http://www.tsaskforms.ca/backend.php/user/new" :title "new user"} "New User"]])

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
       [:a {:href "#" :title "technical safety authority interface design"}
        [:img {:src "/images/icon_logo.jpg" :alt "technical safety authority interface design"}]]]
      [:div.icon_exit
       [:a {:href "#!" :title "log out"} "log out"]]]
     [:div.container
      *main-nav*
      (include-js "/js/highlight-active-tab.js")
      [:div.mainbox
       *sub-nav*
       page]]]]))

(defn form-design-pages [& [form]]
  (binding [*js-css-files* form-edit-files]
  (pages
   [:form {:method "post" :action (.replace (str "/form/" (:id form) "/create") "//" "/")}
    [:input {:type "hidden" :name "sf_method" :value "put"}]
    [:dl.txtcont
     [:dt
      [:div.ltit [:strong "Forms Builder"]]
      [:div.rbtns
       [:a.bsave {:href "#!" :title "save" :onclick "saveForm()"} "SAVE"]
       [:a.bcancle {:href "/forms" :title "cancel"} "CANCLE"]]
      [:div.clear]]
     [:dd
      [:div.forms_cont
       [:div.fc_tit
        [:table.fct_tab {:width "100%" :border "0" :cellspacing "0" :cellpadding "0"}
         [:tr
          [:th {:width "190"} "Form Tools"]
          [:th "Form Design Area"]]]]
       [:div.fc_con.formbuil_box
        [:div.fbnav
         [:div.fbn_list
          (let [elements ["Heading" "TextBox" "TextArea" "DropDown" "RadioButton" "CheckBox" "TOS" "FileUpload"
                          "SubmitButton" "ResetButton" "FullName" "ClientEmail" "Email" "Address" "Phone"
                          "BirthDatePicker" "Number" "DateTime" "UniqueId" "Payment"]
                classes ["icon_heading" "icon_textb" "icon_texta" "icon_dropd" "icon_radio" "icon_check" "icon_check"
                         "icon_file" "icon_submit" "icon_reset" "icon_fullname" "icon_email" "icon_email"
                         "icon_addr" "icon_phone" "icon_bdp" "icon_numb" "icon_datet" "icon_uniid" "icon_pay"]] 
            (for [i (range (count elements))]
              (let [element (get elements i)
                    class (get classes i)]
                [:li {:onclick (str "javascript:add" element "();")}
                 [:img {:src "/images/blank.gif" :class class}]
                 (clojure.string/replace element #"(?<=[a-z])(?=[A-Z])" " ")])))]]
        [:div.fb_cont
         [:ul.fbc_head
          [:li.form_name
           [:div.fbc_bar [:div.bar_tit "Form Name"]]
           [:div.fbc_txt
            [:input#form_name.fbc_txt {:name "form_name" :value (:form_name form)}]]]]
         [:ul.fbc_list (:form_content form)]]
        [:div.clear]]]]]
    [:input#form_content {:type "hidden" :name "form_content" :value ""}]
    [:input#form_published {:type "hidden" :name "form_published" :value ""}]])))

(def home-pg (pages nil))

(def error-page
  (pages
   [:dvi
    [:h1 "Ooooops..."]
    [:h3 "Server is busying..."]]))
