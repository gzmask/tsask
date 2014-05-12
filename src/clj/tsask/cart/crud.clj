(ns tsask.cart.crud
  (:use tsask.env
        tsask.util
        tsask.pages.template-pg)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.io :as io]
            [com.reasonr.scriptjure :as sj]
            [clojure.java.jdbc.sql :as sql]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(defn index [session]
  (let [orders (not-empty (:orders session))]
    (binding [*js-css-files* orders-files 
              *main-nav* [:div.mainnav 
                          [:ul.navlist 
                           [:li [:a.nav1
                                 {:href "/carts"} 
                                 [:span "Shopping Cart"]]]] 
                          [:div.clear]]
              *sub-nav* nil]
      (pages
        [:dl.txtcont
         [:dt [:div.ltit [:strong "Orders in Cart"]] [:div.clear]]
         [:form {:method "post" :action "/cart/delete-selected"}
          [:input {:value " Delete Selected " :type "submit" :onclick "updateCheckedIds()"}]
          [:input {:value "0" :type "hidden" :id "selected_ids" :name "ids"}]
          [:dd
           [:div.fc_con
            [:table.fcc_tab {:width "100%" :cellspacing "0" :cellpadding "0" :border "0"}
             [:tr
              [:th#sf_admin_list_batch_actions [:input#sf_admin_list_batch_checkbox {:type "checkbox" :onclick "checkAll()"}]]
              [:th.sf_admin_text.sf_admin_list_th
               "Form Name"]
              [:th.sf_admin_list_th "pirce"]
              [:th.sf_admin_list_th "Actions"]]
             (for [order orders]
              [:tr
               [:td [:input.sf_admin_batch_checkbox {:type "checkbox" :value (:id order) :name "ids[]"}]]
               [:td.sf_admin_text.sf_admin_list_td_form_name (:form_name order)]
               [:td.sf_admin_text.sf_admin_list_td_form_name (:payment_amt order)]
               [:td [:ul.sf_admin_td_actions
                     [:li.sf_admin_action_view [:a "View"]]
                     [:li.sf_admin_action_delete [:a "Delete"]]]]])
             ]]]] 
         [:script {:type "text/javascript"}
           (sj/js (fn checkAll []
                 (var checkboxes (.getElementsByName document "ids[]"))
                 (doseq [i checkboxes]
                   (set! (.. (aget checkboxes i) checked)
                         (.. (.getElementById document "sf_admin_list_batch_checkbox") checked))))
               (fn updateCheckedIds []
                 (var ids_ary [])
                 (var j 0)
                 (var checkboxes (.getElementsByName document "ids[]"))
                 (var hidden (.getElementById document "selected_ids"))
                 (doseq [i checkboxes]
                   (if (.. (aget checkboxes i) checked)
                     (.push ids_ary (.. (aget checkboxes i) value))))
                 (var ids (.join ids_ary))
                 (set! (.. hidden value) ids)))]]))))

(defn create [params]
  (j/insert! SQLDB :sa_orders
             {:order_content (:order_content params)
              :form_name (:form_name params)
              :created_at (java.util.Date.)
              :updated_at (java.util.Date.)}))

(defn delete [id]
  (if (.exists (io/file (str "resources/public/files/Invoice-" id ".jpg")))
    (io/delete-file (str "resources/public/files/Invoice-" id ".jpg")))
    (if (.exists (io/file (str "resources/public/files/Invoice-" id ".pdf")))
      (io/delete-file (str "resources/public/files/Invoice-" id ".pdf")))
  (j/delete! SQLDB :sa_orders (sql/where {:id id}))
  (j/delete! SQLDB :CSV_report (sql/where {:o_id id}))
  (redirect "/orders"))

(defn view [id]
  (let [order (first (j/query SQLDB
                              (sql/select [:form_name :order_content] :sa_orders
                                          (sql/where {:id id}))))]
    (binding [*js-css-files* order-view-files]
      (pages
        [:div
          [:h2 (:form_name order)]
          [:div.requf_tit.form_control.form_head (:order_content order)]]))))

(defn delete-selected [params]
  (if (empty? (:ids params)) (redirect "/orders")
    (let [ids_ary (clojure.string/split (:ids params) #",")]
      (do
        (doseq [id ids_ary]
          (do 
            (if (.exists (io/file (str "resources/public/files/Invoice-" id ".jpg")))
              (io/delete-file (str "resources/public/files/Invoice-" id ".jpg")))
              (if (.exists (io/file (str "resources/public/files/Invoice-" id ".pdf")))
                (io/delete-file (str "resources/public/files/Invoice-" id ".pdf")))
            (j/delete! SQLDB :sa_orders (sql/where {:id id}))
            (j/delete! SQLDB :CSV_report (sql/where {:o_id id}))))
        (redirect "/orders")))))
