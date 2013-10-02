(ns tsask.form.crud
  (:use tsask.env
        tsask.util
        tsask.pages.template-pg
        com.reasonr.scriptjure
        postal.core

        hiccup.page
        hiccup.core)
  (:require [tsask.csv.crud :as csv]
            [clojure.java.jdbc :as j]
            [tsask.order.crud :as order]
            [clojure.java.jdbc.sql :as sql]
            [clj-http.client :as client]
            [net.cgrand.enlive-html :as html]
            [me.raynes.laser :as l]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(defn index [sort sort-type]
  (let [forms (j/query SQLDB
                       (sql/select [:id :form_name :created_at :updated_at] :sa_forms
                                   (if sort (sql/order-by {(keyword sort) (keyword sort-type)}))))
        opposite-sort-type {"desc" "asc", "asc" "desc", nil "asc"}]
    (binding [*js-css-files* forms-files]
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
  (binding [*js-css-files* form-edit-files]
    (let [form (first (j/query SQLDB
                               (sql/select [:id :form_name :form_content] :sa_forms
                                           (sql/where {:id id}))))]
      (form-design-pages form))))


(defn view [id]
  (let [form (first (j/query SQLDB
                             (sql/select [:form_name :form_published] :sa_forms
                                         (sql/where {:id id}))))]
    (binding [*js-css-files* form-view-files]
      (pages
       [:form {:method "post" :action "/form/commit" :id "form_user"}
        [:dl.txtcont.requtxt
         ;; title
         [:dt [:div.ltit [:strong (:form_name form)]] [:div.clear]]
         ;; main content
         [:dd (:form_published form)]
         (include-js "/js/jquery.min.js" "/js/layout.js" "/js/form_commit.js")]]))))


(defn new []
  (binding [*js-css-files* form-new-files]
    (form-design-pages)))

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

(defmacro apply-macro [macro coll]
  (cons macro coll))

(defn commit [params]
  (binding [*main-nav* [:div.mainnav] *sub-nav* nil 
            *css-files* ["/css/common.css" "/css/main.css"]]
    (let [record {:app_name    (:ordName    params) 
                  :address     (str (:ordAddress1 params) \space (:ordAddress2 params)) 
                  :phone       (:ordPhoneNumber       params) 
                  :email       (:ordEmailAddress       params) 
                  :app_type    (:form_name   params) 
                  :app_detail  (:app_detail  params) 
                  :invoice_id  (not-empty (:invoice_id  params)) 
                  :paid_by     (:trnCardOwner     params) 
                  :card_type   (:trnCardType   params) 
                  :payment_amt (not-empty (:trnAmount params))}]
      (csv/create record)
      (order/create {:order_content (:order_content params)
                     :form_name (:form_name params)})
      ;; just use for log, so I comment it
      #_(if (every? #(not-empty (key %)) record)
          ;; need payment
        (->
         (client/get "https://www.beanstream.com/scripts/process_transaction.asp" (:query-params params))
         (clojure.string/replace "&" "<br/>")
         (str "requestType=BACKEND&merchant_id=257900000&"
              (clojure.string/join "&" (for [[k v] params]
                                         (str (name k) "=" v)))))
        ;; need no payment
        "commit success")
      ;; mail to client, I'm sorry, It's need you to finish
      (send-message
       (assoc MAIL_TEMPLATE
         :to "clientEmail"
         :subject "subject"
         :body "body")) 
      (pages [:div "the order's information sent to you inbox already, please check it later!"]))))
