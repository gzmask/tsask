(ns tsask.user.crud
  (:use tsask.env
        tsask.util
        tsask.pages.template-pg
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

(defn index [sort sort-type]
  (let [users (j/query SQLDB
                       (sql/select [:id :username :email_address :first_name :last_name] :user
                                   (if sort (sql/order-by {(keyword sort) (keyword sort-type)}))))
        opposite-sort-type {"desc" "asc", "asc" "desc", nil "asc"}]
    (binding [*js-css-files* user-files]
      (pages
       [:dl.txtcont
        [:dt [:div.ltit [:strong "Userss List"]] [:div.clear]]
        [:form {:method "post" :action "/forms/batch/action"}
         [:dd
          [:div.fc_con
           [:table.fcc_tab {:width "100%" :cellspacing "0" :cesspadding "0" :border "0"}
            [:tr
             [:th#sf_admin_list_batch_actions [:input#sf_admin_list_batch_checkbox {:type "checkbox" :onclick "checkAll()"}]]
             [:th.sf_admin_text.sf_admin_list_th
              [:a {:href (str "/users?sort=username&sort_type=" (opposite-sort-type sort-type))} "Username"]
              (if (= sort "username") [:img {:src (str "/images/" sort-type ".png")}])]
             [:th.sf_admin_text.sf_admin_list_th
              [:a {:href (str "/users?sort=email_address&sort_type=" (opposite-sort-type sort-type))} "Email Address"]
              (if (= sort "email_address") [:img {:src (str "/images/" sort-type ".png")}])]
             [:th.sf_admin_text.sf_admin_list_th
              [:a {:href (str "/users?sort=last_name&sort_type=" (opposite-sort-type sort-type))} "Last Name"]
              (if (= sort "first_name") [:img {:src (str "/images/" sort-type ".png")}])]
             [:th.sf_admin_text.sf_admin_list_th
              [:a {:href (str "/users?sort=first_name&sort_type=" (opposite-sort-type sort-type))} "First Name"]
              (if (= sort "last_name") [:img {:src (str "/images/" sort-type ".png")}])]
             [:th.sf_admin_list_th "Actions"]]
            (for [user users]
              [:tr
               [:td [:input.sf_admin_batch_checkbox {:type "checkbox" :value (:id user) :name "ids[]"}]]
               [:td.sf_admin_text.sf_admin_list_td_username [:a {:href (str "/user/" (:id user) "/edit")} (:username user)]]
               [:td.sf_admin_text.sf_admin_list_td_email_address (:email_address user)]
               [:td.sf_admin_text.sf_admin_list_td_first_name (:first_name user)]
               [:td.sf_admin_text.sf_admin_list_td_last_name (:last_name user)]
               [:td [:ul.sf_admin_td_actions
                     [:li.sf_admin_action_edit [:a {:href (str "/user/" (:id user) "/edit")} "Edit"]]
                     [:li.sf_admin_action_delete [:a {:href (str "/user/" (:id user) "/delete")
                                                      :onclick (js (return (confirm "are you sure")))} "Delete"]]]]])]]]
        [:script {:type "text/javascript"}
         (js (fn checkAll []
               (var checkboxes (.getElementsByName document "ids[]"))
               (doseq [i checkboxes]
                 (set! (.. (aget checkboxes i) checked)
                       (.. (.getElementById document "sf_admin_list_batch_checkbox") checked)))))]]]))))


(defn delete [id]
  (j/delete! SQLDB :user (sql/where {:id id}))
  (redirect "/users"))


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
                :role_id 1}) 
    (redirect "/users")))

(defn create [params]
  (j/insert! SQLDB :user
             {:username 	(:username params)
              :email_address	(:email_address params)
              :first_name 	(:first_name params)
              :last_name        (:last_name params)
              :password         (digest/sha-256 (:password params))
              :created_at	(.getTime (java.util.Date.))
              :updated_at	(.getTime (java.util.Date.))})
  (redirect "/users"))

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

(defmacro apply-macro [macro coll]
  (cons macro coll))
