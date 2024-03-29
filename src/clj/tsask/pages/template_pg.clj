(ns tsask.pages.template-pg
  (:use hiccup.core
        hiccup.page
        hiccup.util))

;these are like atoms that can be changed
(def ^:dynamic *js-files* nil)
(def ^:dynamic *css-files* nil)
(def ^:dynamic *js-css-files* nil)
(def ^:dynamic *main-nav*
  (let [nav-list [{:id "forms" :content "Form" :title "forms" :href "/forms"}
                  {:id "orders" :content "Order" :title "orders" :href "/orders"}
                  {:id "users" :content "User" :title "users" :href "/users"}
                  {:id "payment-report" :content "Payment Report" :title "payment report" :href "/csv/payment-report"}
                  {:id "carts" :content "Cart" :title "carts" :href "/carts"}]]
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
   [:a.newuser {:href "/user/new" :title "new user"} "New User"]])

(def home-files (.split (slurp "resources/public/html/home.files") "\n"))
(def form-view-files (.split (slurp "resources/public/html/form-view.files") "\n"))
(def form-new-files (.split (slurp "resources/public/html/new-form.files") "\n"))
(def form-edit-files (.split (slurp "resources/public/html/form-edit.files") "\n"))
(def forms-files (.split (slurp "resources/public/html/forms.files") "\n"))
(def orders-files (.split (slurp "resources/public/html/orders.files") "\n"))
(def order-view-files (.split (slurp "resources/public/html/form-view.files") "\n"))
(def user-files (.split (slurp "resources/public/html/user.files") "\n"))

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
        [:img {:src "/images/icon_logo.jpg" :alt "technical safety authority interface design"}]]]
      [:div.icon_exit
       [:a {:href "/logout" :title "log out"} "log out"]]]
     [:div.container
      *main-nav*
      (include-js "/js/highlight-active-tab.js")
      [:div.mainbox
       *sub-nav*
       page]]]]))

(defn view-pages
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
        [:img {:src "/images/icon_logo.jpg" :alt "technical safety authority interface design"}]]]]]
     [:div.container
      (include-js "/js/highlight-active-tab.js")
      [:div.mainbox
       page]]]))


(defn user-design-pages [& [user]]
  (binding [*js-css-files* user-files]
  (pages
    [:dl.txtcont
      [:form#user_form {:method "post" :action (.replace (str "/user/" (:id user) "/create") "//" "/") :onsubmit "return validateForm()"}
        [:dt
          [:div.ltit [:strong "User Builder"]]
          [:div.rbtns
          [:ul.sf_admin_actions
            [:li.sf_admin_action_save [:input {:type "submit" :value "SAVE"}]]
            [:li.sf_admin_action_list [:a {:href "/"} "CANCEL"]]]]
          [:div.clear]]
          [:div.forms_cont
            [:div.fc_tit
              [:table.fct_tab {:width "100%" :border "0" :cellspacing "0" :cellpadding "0"}
                [:tr [:th "User information"]]]]
            [:div.fc_con.userbuil_box
              [:div.sf_admin_form
                [:fieldset#sf_fieldset_user
                  [:div.fc_con.userbuil_box
                    [:table.ub_tab {:width "100%" :border "0" :cellspacing "0" :cellpadding "0"}
                      [:tr
                        [:th {:width "20px"}]
                        [:td
                          [:table
                            [:tr
                              [:td [:input#sf_guard_user_first_name {:type "text" :name "first_name" :value (:first_name user)}] [:br]
                              [:span.fonti [:label {:for "sf_guard_user_first_name"} "First Name"]]]
                              [:div.sf_admin_form_row.sf_admin_text.sf_admin_form_field_first_name]]]]]
                      [:tr
                        [:th {:width "20px"}]
                        [:td
                          [:table
                            [:tr
                              [:td [:input#sf_guard_user_last_name {:type "text" :name "last_name" :value (:last_name user)}] [:br]
                              [:span.fonti [:label {:for "sf_guard_user_last_name"} "Last Name"]]]
                              [:div.sf_admin_form_row.sf_admin_text.sf_admin_form_field_last_name]]]]]
                      [:tr
                        [:th {:width "20px"}]
                        [:td
                          [:table
                            [:tr
                              [:td [:input#sf_guard_user_email_address {:type "text" :name "email_address" :value (:email_address user)}] [:br]
                              [:span.fonti [:label {:for "sf_guard_user_email_address"} "Email Address"]]]
                              [:div.sf_admin_form_row.sf_admin_text.sf_admin_form_field_email_address]]]]]
                      [:tr
                        [:th {:width "20px"}]
                        [:td
                          [:table
                            [:tr
                              [:td [:input#sf_guard_user_username {:type "text" :name "username" :value (:username user)}] [:br]
                              [:span.fonti [:label {:for "sf_guard_user_username"} "Username"]]]
                              [:div.sf_admin_form_row.sf_admin_text.sf_admin_form_field_username]]]]]
                      [:tr
                        [:th {:width "20px"}]
                        [:td
                          [:table
                            [:tr
                              [:td [:input#sf_guard_user_password {:type "password" :name "password"}] [:br]
                              [:span.fonti [:label {:for "sf_guard_user_password"} "Password"]]]
                              [:div.sf_admin_form_row.sf_admin_text.sf_admin_form_field_password]]]]]
                      [:tr
                        [:th {:width "20px"}]
                        [:td
                          [:table
                            [:tr
                              [:td [:input#sf_guard_user_password_again {:type "password" :name "password_again"}] [:br]
                              [:span.fonti [:label {:for "sf_guard_user_password_again"} "Password (again)"]]]
                              [:div.sf_admin_form_row.sf_admin_text.sf_admin_form_field_password_again]]]]]]]]]]]]])))

(defn form-design-pages [& [form calevt]]
  (binding [*js-css-files* form-edit-files]
  (pages
   [:form {:method "post" :action 
           (if (:id form) (str "/form/" (:id form) "/update")
             (str "/form/create"))}
    [:input {:type "hidden" :name "sf_method" :value "put"}]
    [:dl.txtcont
     [:dt
      [:div.ltit [:strong "Forms Builder"]]
      [:div.rbtns
       [:label "Begin date: "]
       [:input#start {:type "text" :name "start" :value (:start calevt)}]
       [:label "End date: "]
       [:input#end {:type "text" :name "end" :value (:end calevt)}]
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
    [:input#form_published {:type "hidden" :name "form_published" :value ""}]
    (include-js "//code.jquery.com/jquery-1.9.1.js" "//code.jquery.com/ui/1.10.3/jquery-ui.js" "/js/form_save.js")])))

(def home-pg 
  (binding [*js-css-files* home-files]
    (pages nil)))

(def error-page
  (pages
   [:dvi
    [:h1 "Ooooops..."]
    [:h3 "Server is busying..."]]))
