(ns tsask.csv.crud
  (:use compojure.core
        tsask.env
        tsask.wrap
        tsask.pages.template-pg)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.jdbc.sql :as sql]
            [me.raynes.laser :as l]
            [clojure.java.io :as io]))

(defn export []
  (let [reports 
        (j/query SQLDB (sql/select * :CSV_report))
        csv_rows (for [r reports] (str (:id r) "," (:app_name r) "," (:address r) "," 
                                       (:phone r) "," (:email r) "," (:app_type r) "," 
                                       (:app_detail r) "," (:invoice_id r) "," (:paid_by r) "," 
                                       (:card_type r) "," (:payment_amt r) ",\n"))
        csv_str (reduce 
                  (fn [r1 r2] 
                    (str r1 r2)) 
                  (str "id," "applicant name," "address," 
                       "phone number," "email address," "application type," 
                       "application detail," "invoice id," "paid by," 
                       "card type," "payment amount," "\n") 
                  csv_rows)]
    (do
      (spit "resources/public/export.csv" csv_str :append false)
      (l/document
        (l/parse (io/file "resources/templates/csv_export.html"))
        (l/class= "content")
        (l/content "The database of order has been exported:")))))


(defn create [params]
  (j/insert! SQLDB :CSVt_report 
             {:app_name    (:app_name    params) 
              :address     (:address     params) 
              :phone       (:phone       params) 
              :email       (:email       params) 
              :app_type    (:app_type    params) 
              :app_detail  (:app_detail  params) 
              :invoice_id  (empty-to-nil (:invoice_id  params)) 
              :paid_by     (:paid_by     params) 
              :card_type   (:card_type   params) 
              :payment_amt (empty-to-nil (:payment_amt params))}))


(defn new []
  (pages
   [:div
    [:form {:action "/csvs/create" :method "post"}
     [:table {}
      (map (fn [lable input] [:tr [:td [:lable lable]] [:td [:input {:type "text" :name (name input)}]]])
           (map #(str (name %) ": ")
                [:app_name :address :phone :email :app_type :app_detail :invoice_id :paid_by :card_type :payment_amt])
           [:app_name :address :phone :email :app_type :app_detail :invoice_id :paid_by :card_type :payment_amt])
      [:tr
       [:td {:colspan "2" :align ""}
        [:input {:type "submit" :value "submit"}]]]]]]
   "/css/form.css"))


(defn payment-report []
  (pages
   [:dl.txtcont
    [:dt [:div.ltit [:strong "Export"]] [:div.clear]]
    [:div.fc_con {:style "margin-top: 15px;"} [:dd [:a {:href "/csv/export"} [:img {:src "/images/export.png"}]]]]]))
