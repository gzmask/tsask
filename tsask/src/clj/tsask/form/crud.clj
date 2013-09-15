(ns tsask.form.crud
  (:use tsask.env
        tsask.pages.template-pg)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as sql]))

(defn index [sort sort-type]
  (let [forms (j/query SQLDB
                       (sql/select [:id :form_name :created_at :updated_at] :sa_forms
                                   (if sort (sql/order-by {(keyword sort) (keyword sort-type)}))))
        opposite-sort-type {"desc" "asc", "asc" "desc", nil "asc"}]
    (pages
     [:dl.txtcont
      [:dt [:div.ltit [:strong "Froms List"]] [:div.clear]]
      [:form {:method "post" :action "/forms/batch/action"}
       [:dd
        [:div.fc_con
         [:table.fcc_tab {:width "100%" :cellspacing "0" :cesspadding "0" :border "0"}
          [:tr
           [:th#sf_admin_list_batch_actions [:input#sf_admin_list_batch_checkbox {:type "checkbox"}]]
           [:th.sf_admin_text.sf_admin_list_th
            [:a {:href (str "/forms?sort=id&sort_type=" (opposite-sort-type sort-type))} "Id"]
            (if (= sort "id") [:img {:src (str "/images/" sort-type ".png")}])]
           [:th.sf_admin_text.sf_admin_list_th
            [:a {:href (str "/forms?sort=form_name&sort_type=" (opposite-sort-type sort-type))} "Form Name"]
            (if (= sort "form_name") [:img {:src (str "/images/" sort-type ".png")}])]
           [:th.sf_admin_text.sf_admin_list_th
            [:a {:href (str "/forms?sort=created_at&sort_type=" (opposite-sort-type sort-type))} "Created At"]
            (if (= sort "created_at") [:img {:src (str "/images/" sort-type ".png")}])]
           [:th.sf_admin_text.sf_admin_list_th
            [:a {:href (str "/forms?sort=updated_at&sort_type=" (opposite-sort-type sort-type))} "Updated At"]
            (if (= sort "updated_at") [:img {:src (str "/images/" sort-type ".png")}])]
           [:th.sf_admin_list_th "Actions"]]
          (for [form forms]
            [:tr
             [:td [:input.sf_admin_batch_checkbox {:type "checkbox" :value (:id form) :name "ids[]"}]]
             [:td.sf_admin_text.sf_admin_list_td_id [:a {:href (str "/forms/" (:id form) "/edit")} (:id form)]]
             [:td.sf_admin_text.sf_admin_list_td_form_name (:form_name form)]
             [:td.sf_admin_text.sf_admin_list_td_created_at (:created_at form)]
             [:td.sf_admin_text.sf_admin_list_td_updated_at (:updated_at form)]
             [:td [:ul.sf_admin_td_actions
                   [:li.sf_admin_action_view [:a {:href (str "/forms/" (:id form) "/preview")} "View"]]
                   [:li.sf_admin_action_delete [:a {:href (str "/forms/" (:id form))} "Delete"]]
                   [:li.sf_admin_action_edit [:a {:href (str "/forms/" (:id form) "/edit")} "Edit"]]]]])]]]]])))
