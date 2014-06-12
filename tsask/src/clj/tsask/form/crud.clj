(ns tsask.form.crud
  (:use tsask.env
        tsask.util
        tsask.pages.template-pg
        com.reasonr.scriptjure
        postal.core
        ring.util.codec
        hiccup.page
        hiccup.core)
  (:require [tsask.csv.crud :as csv]
            [tsask.file.crud :as file]
            [clojure.java.jdbc :as j]
            [tsask.order.crud :as order]
            [clojure.java.jdbc.sql :as sql]
            [clojure.java.io :as io]
            [clj-http.client :as client]
            [net.cgrand.enlive-html :as html]
            [me.raynes.laser :as l]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(defn- query-to-map [query]
  (->> (clojure.string/split query #"&")
       (map #(clojure.string/split % #"="))
       (map (fn [[k v]] [(keyword k) v]))
       (into {})))

(defn index [sort sort-type]
  (let [forms (j/query SQLDB
                       (sql/select [:id :form_name :created_at :updated_at] :sa_forms
                                   (if sort (sql/order-by {(keyword sort) (keyword sort-type)}))))
        opposite-sort-type {"desc" "asc", "asc" "desc", nil "asc"}]
    (binding [*js-css-files* forms-files]
      (pages
       [:dl.txtcont
        [:dt [:div.ltit [:strong "Forms List"]] [:div.clear]]
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
                     [:li.sf_admin_action_edit [:a {:href (str "/form/" (:id form) "/edit")} "Edit"]]
                     [:li.sf_admin_action_copy [:a {:href (str "/form/" (:id form) "/copy")} "Copy"]]]]])]]]]
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

(defn copy [id]
  (let [form (first (j/query SQLDB
                             (sql/select [:form_name :form_content :form_published] :sa_forms
                                         (sql/where {:id id}))))]
    (j/insert! SQLDB :sa_forms {:form_name        (:form_name form)
                                :form_content     (:form_content form)
                                :form_published   (:form_published form)
                                :created_at       (java.util.Date.)
                                :updated_at       (java.util.Date.)}))
    (redirect "/forms"))

(defn view [id]
  (let [form (first (j/query SQLDB
                             (sql/select [:form_name :form_published] :sa_forms
                                         (sql/where {:id id}))))]
    (binding [*js-css-files* form-view-files]
      (view-pages
       [:form {:method "post" :action "/form/commit" :enctype "multipart/form-data" :id "form_user" :onsubmit "return validateForm()"}
        [:dl.txtcont.requtxt
         ;; title
         [:dt [:div.ltit [:strong (:form_name form)]] [:input#form_name {:type "hidden" :value (:form_name form) :name "form_name"}] [:input#order_content {:type "hidden" :name "order_content"}] [:div.clear]]
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

(defn success [params]
  (let [form-params {:funded (:funded params)
                     :IDEBIT_NOTFUNDEDURL (if (nil? (:IDEBIT_NOTFUNDEDURL params)) "https://www.tsaskforms.ca/fail?funded=0" (:IDEBIT_NOTFUNDEDURL params))
                     :IDEBIT_TRACK2 (:IDEBIT_TRACK2 params)
                     :IDEBIT_ISSLANG (:IDEBIT_ISSLANG params)
                     :IDEBIT_VERSION (:IDEBIT_VERSION params)
                     :IDEBIT_ISSCONF (:IDEBIT_ISSCONF params)
                     :IDEBIT_MERCHDATA (:IDEBIT_MERCHDATA params)
                     :IDEBIT_AMOUNT 10
                     :IDEBIT_INVOICE (:IDEBIT_INVOICE params)
                     :IDEBIT_FUNDEDURL (if (nil? (:IDEBIT_FUNDEDURL params)) "https://www.tsaskforms.ca/success?funded=1" (:IDEBIT_FUNDEDURL params))}
        response (query-to-map (:body (client/post "https://www.beanstream.com/scripts/process_transaction_auth.asp" {:form-params (assoc form-params :requestType "BACKEND" :merchant_id "257900000") :debug true :debug-body true :save-request? true})))]
        (spit "resources/params.txt" (str params))
        (spit "resources/form-params.txt" (str form-params))
        (spit "resources/response.txt" (str response))
        (if (= (:trnApproved response) 1)
          (commit-page [:div
                          [:table {:width "100%" :height "100%" :border "0"}
                            [:tr
                              [:td {:valign "center" :align "center"}
                                [:font {:face "Arial" :size "4"} "Your transaction is successful"]]]]
                          [:table {:border "0" :align "center"}
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Transaction ID:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:trnId response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Order Number:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:trnOrderNumber response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Transaction Amount:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (url-decode (:trnAmount response))]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "IO Institution Name:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:ioInstName response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "IO Confirmation Code:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:ioConfCode response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Authorization Code:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:authCode response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Message ID:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:messageId response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Message Text:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:messageText response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Transaction Date:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (url-decode (:trnDate response))]]]]])
          (commit-page [:div
                          [:table {:width "100%" :height "100%" :border "0"}
                            [:tr
                              [:td {:valign "center" :align "center"}
                                [:font {:face "Arial" :size "4"} "Your transaction is declined"]]]]
                          [:table {:border "0" :align "center"}
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Transaction ID:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:trnId response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Order Number:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:trnOrderNumber response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Transaction Amount:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (url-decode (:trnAmount response))]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "IO Institution Name:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:ioInstName response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Authorization Code:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:authCode response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Message ID:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:messageId response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Message Text:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (:messageText response)]]]
                            [:tr
                              [:td
                                [:font {:face "Arial" :size "2"} "Transaction Date:"]]
                              [:td
                                [:font {:face "Arial" :size "2"} (url-decode (:trnDate response))]]]]]))))

(defn fail [params]
  (let [form-params {:funded (:funded params)
                     :IDEBIT_NOTFUNDEDURL (if (nil? (:IDEBIT_NOTFUNDEDURL params)) "" (:IDEBIT_NOTFUNDEDURL params))
                     :IDEBIT_TRACK2 (if (nil? (:IDEBIT_TRACK2 params)) "" (:IDEBIT_TRACK2 params))
                     :IDEBIT_ISSLANG (if (nil? (:IDEBIT_ISSLANG params)) "" (:IDEBIT_ISSLANG params))
                     :IDEBIT_VERSION (if (nil? (:IDEBIT_VERSION params)) "" (:IDEBIT_VERSION params))
                     :IDEBIT_ISSCONF (if (nil? (:IDEBIT_ISSCONF params)) "" (:IDEBIT_ISSCONF params))
                     :IDEBIT_MERCHDATA (if (nil? (:IDEBIT_MERCHDATA params)) "" (:IDEBIT_MERCHDATA params))
                     :IDEBIT_AMOUNT (if (nil? (:IDEBIT_AMOUNT params)) "" (:IDEBIT_AMOUNT params))
                     :IDEBIT_INVOICE (if (nil? (:IDEBIT_INVOICE params)) "" (:IDEBIT_INVOICE params))
                     :IDEBIT_FUNDEDURL (if (nil? (:IDEBIT_FUNDEDURL params)) "" (:IDEBIT_FUNDEDURL params))}
        response (query-to-map (:body (client/post "https://www.beanstream.com/scripts/process_transaction_auth.asp" {:form-params (assoc form-params :requestType "BACKEND" :merchant_id "257900000")})))]
        (spit "resources/params.txt" (str params))
        (spit "resources/response.txt" (str form-params))
        (commit-page [:div
                        [:table {:width "100%" :height "100%" :border "0"}
                          [:tr
                            [:td {:valign "center" :align "center"}
                              [:font {:face "Arial" :size "4"} "Your transaction is declined"]]]]
                        [:table {:border "0" :align "center"}
                          [:tr
                            [:td
                              [:font {:face "Arial" :size "2"} "Transaction ID:"]]
                            [:td
                              [:font {:face "Arial" :size "2"} (:trnId response)]]]
                          [:tr
                            [:td
                              [:font {:face "Arial" :size "2"} "Order Number:"]]
                            [:td
                              [:font {:face "Arial" :size "2"} (:trnOrderNumber response)]]]
                          [:tr
                            [:td
                              [:font {:face "Arial" :size "2"} "Transaction Amount:"]]
                            [:td
                              [:font {:face "Arial" :size "2"} (url-decode (:trnAmount response))]]]
                          [:tr
                            [:td
                              [:font {:face "Arial" :size "2"} "IO Institution Name:"]]
                            [:td
                              [:font {:face "Arial" :size "2"} (:ioInstName response)]]]
                          [:tr
                            [:td
                              [:font {:face "Arial" :size "2"} "Authorization Code:"]]
                            [:td
                              [:font {:face "Arial" :size "2"} (:authCode response)]]]
                          [:tr
                            [:td
                              [:font {:face "Arial" :size "2"} "Message ID:"]]
                            [:td
                              [:font {:face "Arial" :size "2"} (:messageId response)]]]
                          [:tr
                            [:td
                              [:font {:face "Arial" :size "2"} "Message Text:"]]
                            [:td
                              [:font {:face "Arial" :size "2"} (:messageText response)]]]
                          [:tr
                            [:td
                              [:font {:face "Arial" :size "2"} "Transaction Date:"]]
                            [:td
                              [:font {:face "Arial" :size "2"} (url-decode (:trnDate response))]]]]])))

(defn commit [params]
  (binding [*js-css-files* form-view-files] 
    (if (not= (:paymentMethod params) "IO")
      (let [payment-info {:app_name    (:ordName    params) 
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
                          :payment_amt (not-empty (:trnAmount params))}
            csv_record   (csv/create payment-info)
            order_record (order/create {:order_content (:order_content params) 
                                      :form_name (:form_name params)}) 
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
            response (client/get "https://www.beanstream.com/scripts/process_transaction.asp" {:query-params (assoc query-params :requestType "BACKEND" :merchant_id "257900000")})
            file (:real_input params)
            info (first (j/query SQLDB ["show table status like 'sa_orders'"]))
            upload_file  (if (not-empty (:filename file)) (io/copy (io/file (:tempfile file)) (io/file (str "resources/public/files/Invoice-" (:auto_increment info) (if (= "image/jpeg" (:content-type file)) ".jpg" (if (= "application/pdf" (:content-type file)) ".pdf"))))))

            email (send-message 
                    (assoc MAIL_TEMPLATE 
                         :to "chao@melcher.com" 
                         :subject "subject" 
                         :body "body"))]
            (if (.contains (:body response) "trnApproved=0")
              (commit-page [:div (str "Payment attempt failed due to reason: " (:messageText response) "<br>Please contact us and we will help you to submit your payment.")]) 
              (commit-page [:div "Thank you, your payment is successful! Tsask will process your request shortly. If you have not received confirmation in a few days please contact us."])))
      (let [payment-info {:app_name    (:ordName    params) 
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
                          :payment_amt (not-empty (:trnAmount params))}
            csv_record   (csv/create payment-info)
            order_record (order/create {:order_content (:order_content params) 
                                      :form_name (:form_name params)}) 
            form-params {:ordName         (:ordName params) 
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
                          :paymentMethod   (:paymentMethod params)}
            response (query-to-map (:body (client/post "https://www.beanstream.com/scripts/process_transaction.asp" {:form-params (assoc form-params :requestType "BACKEND" :merchant_id "257900000")})))
            file (:real_input params)
            info (first (j/query SQLDB ["show table status like 'sa_orders'"]))
            upload_file  (if (not-empty (:filename file)) (io/copy (io/file (:tempfile file)) (io/file (str "resources/public/files/Invoice-" (:auto_increment info) (if (= "image/jpeg" (:content-type file)) ".jpg" (if (= "application/pdf" (:content-type file)) ".pdf"))))))]
            (spit "resources/test.txt" (url-decode (:pageContents response)))
            (interac-page (url-decode (:pageContents response)))))))
