(ns tsask.form.crud
  (:use tsask.env
        tsask.pages.template-pg
        com.reasonr.scriptjure)
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
                   [:li.sf_admin_action_delete [:a {:href (str "/form/" (:id form) "/delete")
                                                    :onclick (js (return (confirm "are you sure")))} "Delete"]]
                   [:li.sf_admin_action_edit [:a {:href (str "/forms/" (:id form) "/edit")} "Edit"]]]]])]]]]])))


(defn delete [id]
  (j/delete! SQLDB :sa_forms (sql/where {:id id}))
  {:status 302
   :headers {"Location" "/forms"}
   :body ""})


(defn edit [id]
  (let [form (first (j/query SQLDB
                             (sql/select [:form_name :form_content] :sa_forms
                                         (sql/where {:id id}))))]
    (println form)
    (pages
     [:form {:method "post" :action (str "/form/" id)}
      [:input {:type "hidden" :name "sf_method" :value "put"}]
      [:dl.txtcont
       [:dt
        [:div.ltit [:strong "Forms Builder"]]
        [:div.rbtns
         [:a.bsave {:href "#!" :title "save" :onclick "saveForm"} "SAVE"]
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
            (let [elements ["Heading" "TextBox" "TextArea" "DropDown" "RadioButton" "CheckBox" "FileUpload"
                            "SubmitButton" "ResetButton" "FullName" "ClientEmail" "Email" "Address" "Phone"
                            "BirthDatePicker" "Number" "DataTime" "UniqueId" "Payment"]
                  classes ["icon_heading" "icon_textb" "icon_texta" "icon_dropd" "icon_radio" "icon_check"
                           "icon_file" "icon_submit" "icon_reset" "icon_fullname" "icon_email" "icon_email"
                           "icon_addr" "icon_phone" "icon_bdp" "icon_numb" "icon_datet" "icon_uniid" "icon_pay"]] 
              (for [i (range (count elements))]
                (let [element (get elements i)
                      class (get classes i)]
                  [:li {:onclick (str "javascript:add" (clojure.string/capitalize element) "();")}
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
      [:input {:type "hidden" :name "form_content" :value ""}]
      [:input {:type "hidden" :name "form_published" :value "11"}]])))
