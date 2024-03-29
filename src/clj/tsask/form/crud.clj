(ns tsask.form.crud
  (:use tsask.env
        tsask.util
        hiccup.page
        hiccup.core)
  (:require [tsask.csv.crud :as csv]
            [tsask.file.crud :as file]
            [tsask.pages.template-pg :as template]
            [postal.core :as postal]
            [com.reasonr.scriptjure :as sj]
            [clojure.java.jdbc :as j]
            [tsask.order.crud :as order]
            [clojure.java.jdbc.sql :as sql]
            [clojure.java.io :as io]
            [clj-http.client :as client]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(defn index [sort sort-type]
  (let [forms (j/query SQLDB
                       (sql/select [:id :form_name :created_at :updated_at] :sa_forms
                                   (if sort (sql/order-by {(keyword sort) (keyword sort-type)}))))
        opposite-sort-type {"desc" "asc", "asc" "desc", nil "asc"}]
    (binding [template/*js-css-files* template/forms-files] ;like let, but deeper into function context
      (template/pages
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
               [:td.sf_admin_text.sf_admin_list_td_created_at (strftime "%Y/%m/%d" (:created_at form))]
               [:td.sf_admin_text.sf_admin_list_td_updated_at (strftime "%Y/%m/%d" (:updated_at form))]
               [:td [:ul.sf_admin_td_actions
                     [:li.sf_admin_action_view [:a {:href (str "/form/" (:id form) "/view")} "View"]]
                     [:li.sf_admin_action_delete [:a {:href (str "/form/" (:id form) "/delete")
                                                      :onclick (sj/js (return (confirm "are you sure")))} "Delete"]]
                     [:li.sf_admin_action_edit [:a {:href (str "/form/" (:id form) "/edit")} "Edit"]]
                     [:li.sf_admin_action_copy [:a {:href (str "/form/" (:id form) "/copy")} "Copy"]]]]])]]]]
        [:script {:type "text/javascript"}
         (sj/js (fn checkAll []
               (var checkboxes (.getElementsByName document "ids[]"))
               (doseq [i checkboxes]
                 (set! (.. (aget checkboxes i) checked)
                       (.. (.getElementById document "sf_admin_list_batch_checkbox") checked)))))]]))))

(defn delete [id]
  (j/delete! SQLDB :sa_forms (sql/where {:id id}))
  (j/delete! SQLDB :calevent (sql/where {:form_id id}))
  (redirect "/forms"))

(defn edit [id]
  (binding [template/*js-css-files* template/form-edit-files]
    (let [form (first (j/query SQLDB
                               (sql/select [:id :form_name :form_content] :sa_forms
                                           (sql/where {:id id}))))
          calevt (first (j/query SQLDB
                                 (sql/select [:id :title :description :form_id :start :end] :calevent
                                            (sql/where {:form_id id}))))]
      (template/form-design-pages form calevt))))

(defn copy [id]
  (let [form (first (j/query SQLDB
                             (sql/select [:form_name :form_content :form_published] :sa_forms
                                         (sql/where {:id id}))))]
    (j/insert! SQLDB :sa_forms {:form_name        (:form_name form)
                                :form_content     (:form_content form)
                                :form_published   (:form_published form)
                                :created_at       (.getTime (java.util.Date.))
                                :updated_at       (.getTime (java.util.Date.))}))
    (redirect "/forms"))


(defn view [id]
  (let [form (first (j/query SQLDB
                             (sql/select [:form_name :form_published] :sa_forms
                                         (sql/where {:id id}))))]
    (binding [template/*js-css-files* template/form-view-files]
      (template/view-pages
       [:form {:method "post" :action "/form/commit" :enctype "multipart/form-data" :id "form_user" :onsubmit "return validateForm()"}
        [:dl.txtcont.requtxt
         ;; title
         [:dt [:div.ltit [:strong (:form_name form)]] [:input#form_name {:type "hidden" :value (:form_name form) :name "form_name"}] [:input#order_content {:type "hidden" :name "order_content"}] [:div.clear]]
         ;; main content
         [:dd (:form_published form)]
         ]]))))

(defn new []
  (binding [template/*js-css-files* template/form-new-files]
    (template/form-design-pages)))

(defn create [params]
  (let [form_record (j/insert! SQLDB :sa_forms
             {:form_name 	(:form_name params)
              :form_content	(:form_content params)
              :form_published 	(:form_published params)
              :created_at	(.getTime (java.util.Date.))
              :updated_at	(.getTime (java.util.Date.))})] 
    (j/insert! SQLDB :calevent 
               {:title (:form_name params)
                :description (:form_name params)
                :form_id (get_last_id form_record)
                :start (:start params)
                :end (:end params)})
  (redirect "/forms")))

(defn update [params]
  (j/update! SQLDB :sa_forms
             {:form_name 	(:form_name params)
              :form_content	(:form_content params)
              :form_published 	(:form_published params)
              :updated_at	(.getTime (java.util.Date.))} 
             (sql/where {:id (:id params)})) 
  (j/update! SQLDB :calevent 
             {:title (:form_name params)
              :description (:form_name params)
              :start (:start params)
              :end (:end params)} 
             (sql/where {:form_id (:id params)}))
  (redirect "/forms"))

(defn add-cart [params session]
  (let [orders (not-empty (:orders session)) 
        order {:order_content (:order_content params) 
               :form_name (:form_name params) 
               :app_name    (:ordName    params) 
               :invoice_no  (:InvoiceNumber params) 
               :address     (str (:ordAddress1 params) \space (:ordAddress2 params)) 
               :phone       (:ordPhoneNumber       params) 
               :email       (:ordEmailAddress       params) 
               :file_no     (:file_no     params) 
               :reg_class   (:reg_class   params) 
               :app_type    (:form_name   params) 
               :app_detail  (:app_detail  params) 
               :invoice_id  (not-empty (:invoice_id  params)) 
               :paid_by     (:trnCardOwner     params) 
               :card_type   (:trnCardType   params) 
               :payment_amt (if (empty? (:trnAmount params)) 0 (:trnAmount params))}]
    {:status 302
     :session (assoc session :orders (cons order orders))
     :headers {"Location" "/carts"}}))

(defn order_action [params]
    (let [order_record (order/create {:order_content (:order_content params) 
                                      :form_name (:form_name params)}) 
          payment-info {:app_name    (:ordName    params) 
                        :invoice_no  (:InvoiceNumber params)
                        :address     (str (:ordAddress1 params) \space (:ordAddress2 params)) 
                        :phone       (:ordPhoneNumber       params) 
                        :email       (:ordEmailAddress       params) 
                        :file_no     (:file_no     params)
                        :reg_class   (:reg_class   params)
                        :app_type    (:form_name   params) 
                        :app_detail  (:app_detail  params) 
                        :invoice_id  (not-empty (:invoice_id  params)) 
                        :paid_by     (:trnCardOwner     params) 
                        :card_type   (:trnCardType   params) 
                        :payment_amt (not-empty (:trnAmount params))
                        :o_id (get_last_id order_record)}
          file (:real_input params)
          upload_file  (if (not-empty (:filename file)) (io/copy (io/file (:tempfile file)) (io/file (str "resources/public/files/Invoice-" (:last_insert_rowid() order_record) (if (= "image/jpeg" (:content-type file)) ".jpg" (if (= "application/pdf" (:content-type file)) ".pdf"))))))
          csv_record   (csv/create payment-info)
          query-params {:ordName         (:ordName params) 
                        :ordPhoneNumber  (:ordPhoneNumber params)
                        :ordAddress1     (:ordAddress1 params)
                        :ordAddress2     (:ordAddress2 params)
                        :ordCity         (:ordCity params)
                        :ordProvince     (:ordProvince params)
                        :ordPostalCode   (:ordPostalCode params)
                        :ordCountry      (:ordCountry params)
                        :ordEmailAddress (:ordEmailAddress params)
                        :trnOrderNumber  (:trnOrderNumber params)
                        :trnAmount       (:trnAmount params)
                        :trnCardOwner    (:trnCardOwner params)
                        :trnCardType     (:trnCardType params)
                        :trnCardNumber   (:trnCardNumber params)
                        :trnExpMonth     (:trnExpMonth params)
                        :trnExpYear      (:trnExpYear params)}
          response (client/get "https://www.beanstream.com/scripts/process_transaction.asp" {:query-params (assoc query-params :requestType "BACKEND" :merchant_id "257900000")})]
      response))

(defn submit [params]
  (binding [template/*js-css-files* template/form-view-files] 
    (let [response (order_action params)
          email (postal/send-message 
                  (assoc MAIL_TEMPLATE 
                         :to "chao@melcher.com" 
                         :subject "subject" 
                         :body "body"))]
            (if (.contains (:body response) "trnApproved=0")
              (template/commit-page [:div (str "Payment attempt failed due to reason: " (:body response) "<br>Please contact us and we will help you to submit your payment.")]) 
              (template/commit-page [:div "Thank you, your payment is successful! Tsask will process your request shortly. If you have not received confirmation in a few days please contact us."])))))

(defn commit [params session]
  (cond (= (:user_action params) "cart") (add-cart params session)
        (= (:user_action params) "submit") (submit params)))
