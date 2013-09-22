(ns tsask.form.crud
  (:use tsask.env
        tsask.pages.template-pg
        com.reasonr.scriptjure
        hiccup.page)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as sql]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(defn index [sort sort-type]
  (let [forms (j/query SQLDB
                       (sql/select [:id :form_name :created_at :updated_at] :sa_forms
                                   (if sort (sql/order-by {(keyword sort) (keyword sort-type)}))))
        opposite-sort-type {"desc" "asc", "asc" "desc", nil "asc"}]
    (binding [*js-files* (.split (slurp "form-design-js-files") "\n")]
      (pages
       [:dl.txtcont
        [:dt [:div.ltit [:strong "Froms List"]] [:div.clear]]
        [:form {:method "post" :action "/forms/batch/action"}
         [:dd
          [:div.fc_con
           [:table.fcc_tab {:width "100%" :cellspacing "0" :cesspadding "0" :border "0"}
            [:tr
             [:th#sf_admin_list_batch_actions [:input#sf_admin_list_batch_checkbox {:type "checkbox" :onclick "checkAll()"}]]
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
               [:td.sf_admin_text.sf_admin_list_td_id [:a {:href (str "/form/" (:id form) "/edit")} (:id form)]]
               [:td.sf_admin_text.sf_admin_list_td_form_name (:form_name form)]
               [:td.sf_admin_text.sf_admin_list_td_created_at (:created_at form)]
               [:td.sf_admin_text.sf_admin_list_td_updated_at (:updated_at form)]
               [:td [:ul.sf_admin_td_actions
                     [:li.sf_admin_action_view [:a {:href (str "/form/" (:id form) "/view")} "View"]]
                     [:li.sf_admin_action_delete [:a {:href (str "/form/" (:id form) "/delete")
                                                      :onclick (js (return (confirm "are you sure")))} "Delete"]]
                     [:li.sf_admin_action_edit [:a {:href (str "/form/" (:id form) "/edit")} "Edit"]]]]])]]]]
        [:script {:type "text/javascript"}
         (js (fn checkAll []
               (var checkboxes (.getElementsByName document "ids[]"))
               (doseq [i checkboxes]
                 (set! (.. (aget checkboxes i) checked)
                       (.. (.getElementById document "sf_admin_list_batch_checkbox") checked)))))]]))))


(defn delete [id]
  (j/delete! SQLDB :sa_forms (sql/where {:id id}))
  (redirect "/forms"))


(defn edit [id]
  (let [form (first (j/query SQLDB
                             (sql/select [:id :form_name :form_content] :sa_forms
                                         (sql/where {:id id}))))]
    (form-design-pages form)))


(defn view [id]
  (let [form (first (j/query SQLDB
                             (sql/select [:form_name :form_published] :sa_forms
                                         (sql/where {:id id}))))]
    (binding [*main-nav* [:div.mainnav] *sub-nav* nil
              *css-files* ["/css/common.css" "/css/main.css"]
              *js-files* (.split (slurp "form-view-js-files") "\n")]
      (pages
       [:form {:method "post" :action "/form/commit" :id "form_user"}
        [:dl.txtcont.requtxt
         ;; title
         [:dt [:div.ltit [:strong (:form_name form)]] [:div.clear]]
         ;; main content
         [:dd (:form_published form)]
         (include-js "/js/jquery.min.js" "/js/layout.js" "/js/form_commit.js")]]))))

(defn new []
  (form-design-pages))

(defn create [params]
  (j/insert! SQLDB :sa_forms
             {:form_name 	(:form_name params)
              :form_content	(:form_content params)
              :form_published 	(:form_published params)
              :created_at	(java.util.Date.)
              :updated_at	(java.util.Date.)})
  (redirect "/forms"))

(defn update [params]
  (j/update! SQLDB :sa_forms
             {:form_name 	(:form_name params)
              :form_content	(:form_content params)
              :form_published 	(:form_published params)
              :updated_at	(java.util.Date.)}
             (sql/where {:id (:id params)}))
  (redirect "/forms"))
