(ns tsask.frontend.user.crud
  (:use tsask.env
        tsask.util
        tsask.frontend.pages.template-pg
        com.reasonr.scriptjure
        digest
        hiccup.page
        hiccup.core)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as sql]
            [clj-http.client :as client]
            [net.cgrand.enlive-html :as html]
            [me.raynes.laser :as l]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(defn user-design-pages [& [user]]
  (binding [*js-css-files* user-files
            *sub-nav* nil]
  (pages
    [:dl.txtcont
      [:form#user_form {:method "post" :action (if user (str "/fuser/" (:id user) "/update")
                                                 (str "/fuser/create")) 
                        :onsubmit "return validateForm()"}
        [:dt
          [:div.ltit [:strong "Sign up as new User"]]
          [:div.rbtns
          [:ul.sf_admin_actions
            [:li.sf_admin_action_save [:input {:type "submit" :value "Sign up"}]]
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

(defn edit [id]
  (binding [*js-css-files* user-files]
    (let [user (first (j/query SQLDB
                               (sql/select [:id :username :email_address :first_name :last_name] :user
                                           (sql/where {:id id}))))]
      (user-design-pages user))))

(defn new []
  (binding [*js-css-files* user-files]
    (user-design-pages)))

(defn create [params]
  (let [user (j/insert! SQLDB :user 
                        {:username 	(:username params) 
                         :email_address	(:email_address params)
                         :first_name 	(:first_name params) 
                         :last_name        (:last_name params) 
                         :password         (digest/sha-256 (:password params)) 
                         :created_at	(.getTime (java.util.Date.)) 
                         :updated_at	(.getTime (java.util.Date.))})] 
    (j/insert! SQLDB :hasrole 
               {:user_id (get_last_id user) 
                :role_id 3}) 
    (redirect "/users")))

(defn update [params]
  (if (empty? (:password params))
    (j/update! SQLDB :user
               {:username 	(:username params)
                :email_address	(:email_address params)
                :first_name 	(:first_name params)
                :last_name      (:last_name params)
                :updated_at	(.getTime (java.util.Date.))}
              (sql/where {:id (:id params)}))
    (j/update! SQLDB :user
               {:username 	(:username params)
                :email_address	(:email_address params)
                :first_name 	(:first_name params)
                :last_name      (:last_name params)
                :password       (digest/sha-256 (:password params))
                :updated_at	(.getTime (java.util.Date.))}
              (sql/where {:id (:id params)})))
  (redirect "/users"))

