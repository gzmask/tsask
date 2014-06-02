(ns tsask.cart.crud
  (:use tsask.env
        tsask.util)
  (:require [clojure.java.jdbc :as j]
            [clojure.java.io :as io]
            [tsask.order.crud :as order]
            [tsask.form.crud :as form]
            [tsask.pages.template-pg :as template]
            [com.reasonr.scriptjure :as sj]
            [clojure.java.jdbc.sql :as sql]))

(defn- redirect [url]
  {:status 302
   :headers {"Location" url}
   :body ""})

(def payment_tb
   [:table
    {:cellspacing "1", :cellpadding "0", :border "0"}
    "   \t\t"
    [:tbody
     [:tr
      [:td
       {:valign "top", :height "30px", :colspan "3"}
       [:font
        {:color "000066", :size "3", :face "Arial"}
        [:b "Address Information"]]]]
     "\t\t"
     [:tr
      [:td {:rowspan "9"} " "]
      [:td [:font {:color "000000", :size "2", :face "Arial"} "Name:"]]
      [:td
       [:input#ordName
        {:autocomplete "off",
         :maxlength "64",
         :size "30",
         :value "",
         :name "ordName",
         :type "text"}]]]
     "\t\t"
     [:tr
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        "Phone Number:"]]
      [:td
       [:input#ordPhoneNumber
        {:autocomplete "off",
         :maxlength "32",
         :size "30",
         :value "",
         :name "ordPhoneNumber",
         :type "text"}]]]
     "\t\t"
     [:tr
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        "Address Line 1:"]]
      [:td
       [:input#ordAddress1
        {:autocomplete "off",
         :maxlength "64",
         :size "30",
         :value "",
         :name "ordAddress1",
         :type "text"}]]]
     "\t\t"
     [:tr
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        "Address Line 2:"]]
      [:td
       [:input#ordAddress2
        {:autocomplete "off",
         :maxlength "64",
         :size "30",
         :value "",
         :name "ordAddress2",
         :type "text"}]]]
     "\t\t"
     [:tr
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        [:span#ordCountry_cityLabel "City"]
        ":"]]
      [:td
       [:input#ordCity
        {:autocomplete "off",
         :maxlength "32",
         :size "30",
         :value "",
         :name "ordCity",
         :type "text"}]]]
     "\t\t"
     [:tr#ordCountry_displayProvince
      "   \t\t\t"
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        [:span#ordCountry_provinceLabel "Province/State"]
        ":"]]
      "   \t\t\t"
      [:td
       "\t\t\t\t"
       [:select#ordProvince
        {:width "203", :style "width:203", :name "ordProvince"}
        "\t\t\t\t\t"
        [:option
         {:disabled "disabled", :value ""}
         "Select your state/province: "]
        "\t\t\t\t\t"
        [:option {:value "--"} "Outside U.S./Canada"]
        "\t\t\t\t\t\t"
        [:optgroup#ordProvince_country_CA
         {:label "Canada"}
         "\t\t\t\t\t\t"
         [:option {:value "AB"} "Alberta"]
         "\t\t\t\t\t\t"
         [:option {:value "BC"} "British Columbia"]
         "\t\t\t\t\t\t"
         [:option {:value "MB"} "Manitoba"]
         "\t\t\t\t\t\t"
         [:option {:value "NB"} "New Brunswick"]
         "\t\t\t\t\t\t"
         [:option {:value "NL"} "Newfoundland and Labrador"]
         "\t\t\t\t\t\t"
         [:option {:value "NT"} "Northwest Territories"]
         "\t\t\t\t\t\t"
         [:option {:value "NS"} "Nova Scotia"]
         "\t\t\t\t\t\t"
         [:option {:value "NU"} "Nunavut"]
         "\t\t\t\t\t\t"
         [:option {:value "ON"} "Ontario"]
         "\t\t\t\t\t\t"
         [:option {:value "PE"} "Prince Edward Island"]
         "\t\t\t\t\t\t"
         [:option {:value "QC"} "Quebec"]
         "\t\t\t\t\t\t"
         [:option {:selected "selected", :value "SK"} "Saskatchewan"]
         "\t\t\t\t\t\t"
         [:option {:value "YT"} "Yukon"]
         "\t\t\t\t\t\t"]
        "\t\t\t\t\t\t"
        [:optgroup#ordProvince_country_US
         {:label "United States"}
         "\t\t\t\t\t\t"
         [:option {:value "AL"} "Alabama"]
         "\t\t\t\t\t\t"
         [:option {:value "AK"} "Alaska"]
         "\t\t\t\t\t\t"
         [:option {:value "AS"} "American Samoa"]
         "\t\t\t\t\t\t"
         [:option {:value "AZ"} "Arizona"]
         "\t\t\t\t\t\t"
         [:option {:value "AR"} "Arkansas"]
         "\t\t\t\t\t\t"
         [:option {:value "AA"} "Armed Forces Americas"]
         "\t\t\t\t\t\t"
         [:option {:value "AE"} "Armed Forces Europe"]
         "\t\t\t\t\t\t"
         [:option {:value "AP"} "Armed Forces Pacific"]
         "\t\t\t\t\t\t"
         [:option {:value "CA"} "California"]
         "\t\t\t\t\t\t"
         [:option {:value "CO"} "Colorado"]
         "\t\t\t\t\t\t"
         [:option {:value "CT"} "Connecticut"]
         "\t\t\t\t\t\t"
         [:option {:value "DE"} "Delaware"]
         "\t\t\t\t\t\t"
         [:option {:value "DC"} "District of Columbia"]
         "\t\t\t\t\t\t"
         [:option {:value "FL"} "Florida"]
         "\t\t\t\t\t\t"
         [:option {:value "GA"} "Georgia"]
         "\t\t\t\t\t\t"
         [:option {:value "GU"} "Guam"]
         "\t\t\t\t\t\t"
         [:option {:value "HI"} "Hawaii"]
         "\t\t\t\t\t\t"
         [:option {:value "ID"} "Idaho"]
         "\t\t\t\t\t\t"
         [:option {:value "IL"} "Illinois"]
         "\t\t\t\t\t\t"
         [:option {:value "IN"} "Indiana"]
         "\t\t\t\t\t\t"
         [:option {:value "IA"} "Iowa"]
         "\t\t\t\t\t\t"
         [:option {:value "KS"} "Kansas"]
         "\t\t\t\t\t\t"
         [:option {:value "KY"} "Kentucky"]
         "\t\t\t\t\t\t"
         [:option {:value "LA"} "Louisiana"]
         "\t\t\t\t\t\t"
         [:option {:value "ME"} "Maine"]
         "\t\t\t\t\t\t"
         [:option {:value "MD"} "Maryland"]
         "\t\t\t\t\t\t"
         [:option {:value "MA"} "Massachusetts"]
         "\t\t\t\t\t\t"
         [:option {:value "MI"} "Michigan"]
         "\t\t\t\t\t\t"
         [:option {:value "MN"} "Minnesota"]
         "\t\t\t\t\t\t"
         [:option {:value "MS"} "Mississippi"]
         "\t\t\t\t\t\t"
         [:option {:value "MO"} "Missouri"]
         "\t\t\t\t\t\t"
         [:option {:value "MT"} "Montana"]
         "\t\t\t\t\t\t"
         [:option {:value "NE"} "Nebraska"]
         "\t\t\t\t\t\t"
         [:option {:value "NV"} "Nevada"]
         "\t\t\t\t\t\t"
         [:option {:value "NH"} "New Hampshire"]
         "\t\t\t\t\t\t"
         [:option {:value "NJ"} "New Jersey"]
         "\t\t\t\t\t\t"
         [:option {:value "NM"} "New Mexico"]
         "\t\t\t\t\t\t"
         [:option {:value "NY"} "New York"]
         "\t\t\t\t\t\t"
         [:option {:value "NC"} "North Carolina"]
         "\t\t\t\t\t\t"
         [:option {:value "ND"} "North Dakota"]
         "\t\t\t\t\t\t"
         [:option {:value "MP"} "Northern Marianas"]
         "\t\t\t\t\t\t"
         [:option {:value "OH"} "Ohio"]
         "\t\t\t\t\t\t"
         [:option {:value "OK"} "Oklahoma"]
         "\t\t\t\t\t\t"
         [:option {:value "OR"} "Oregon"]
         "\t\t\t\t\t\t"
         [:option {:value "PW"} "Palau"]
         "\t\t\t\t\t\t"
         [:option {:value "PA"} "Pennsylvania"]
         "\t\t\t\t\t\t"
         [:option {:value "PR"} "Puerto Rico"]
         "\t\t\t\t\t\t"
         [:option {:value "RI"} "Rhode Island"]
         "\t\t\t\t\t\t"
         [:option {:value "SC"} "South Carolina"]
         "\t\t\t\t\t\t"
         [:option {:value "SD"} "South Dakota"]
         "\t\t\t\t\t\t"
         [:option {:value "TN"} "Tennessee"]
         "\t\t\t\t\t\t"
         [:option {:value "TX"} "Texas"]
         "\t\t\t\t\t\t"
         [:option {:value "UT"} "Utah"]
         "\t\t\t\t\t\t"
         [:option {:value "VT"} "Vermont"]
         "\t\t\t\t\t\t"
         [:option {:value "VI"} "Virgin Islands"]
         "\t\t\t\t\t\t"
         [:option {:value "VA"} "Virginia"]
         "\t\t\t\t\t\t"
         [:option {:value "WA"} "Washington"]
         "\t\t\t\t\t\t"
         [:option {:value "WV"} "West Virginia"]
         "\t\t\t\t\t\t"
         [:option {:value "WI"} "Wisconsin"]
         "\t\t\t\t\t\t"
         [:option {:value "WY"} "Wyoming"]
         "\t\t\t\t\t\t"]
        "\t\t\t\t\t"]
       "\t\t\t"]
      "   \t\t"]
     "\t\t"
     [:tr
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        [:span#ordCountry_postalLabel "Postal/Zip Code"]
        ":"]]
      [:td
       [:input#ordPostalCode
        {:autocomplete "off",
         :maxlength "32",
         :size "30",
         :value "",
         :name "ordPostalCode",
         :type "text"}]
       " "]]
     "\t\t"
     [:tr
      "   \t\t\t"
      [:td
       [:font {:color "000000", :size "2", :face "Arial"} "Country:"]]
      "   \t\t\t"
      [:td
       "\t\t\t\t"
       [:select#ordCountry
        {:onchange
         "CountryUpdate(this, document.getElementById('frmPayment').ordProvince);",
         :style "width:203",
         :name "ordCountry"}
        "   \t\t\t\t\t\t\t\t\t\t"
        [:option {:value "AF"} "Afghanistan"]
        "\t\t\t\t\t"
        [:option {:value "AX"} "?land Islands"]
        "\t\t\t\t\t"
        [:option {:value "AL"} "Albania"]
        "\t\t\t\t\t"
        [:option {:value "DZ"} "Algeria"]
        "\t\t\t\t\t"
        [:option {:value "AS"} "American Samoa"]
        "\t\t\t\t\t"
        [:option {:value "AD"} "Andorra"]
        "\t\t\t\t\t"
        [:option {:value "AO"} "Angola"]
        "\t\t\t\t\t"
        [:option {:value "AI"} "Anguilla"]
        "\t\t\t\t\t"
        [:option {:value "AQ"} "Antarctica"]
        "\t\t\t\t\t"
        [:option {:value "AG"} "Antigua and Barbuda"]
        "\t\t\t\t\t"
        [:option {:value "AR"} "Argentina"]
        "\t\t\t\t\t"
        [:option {:value "AM"} "Armenia"]
        "\t\t\t\t\t"
        [:option {:value "AW"} "Aruba"]
        "\t\t\t\t\t"
        [:option {:value "AU"} "Australia"]
        "\t\t\t\t\t"
        [:option {:value "AT"} "Austria"]
        "\t\t\t\t\t"
        [:option {:value "AZ"} "Azerbaijan"]
        "\t\t\t\t\t"
        [:option {:value "BS"} "Bahamas"]
        "\t\t\t\t\t"
        [:option {:value "BH"} "Bahrain"]
        "\t\t\t\t\t"
        [:option {:value "BD"} "Bangladesh"]
        "\t\t\t\t\t"
        [:option {:value "BB"} "Barbados"]
        "\t\t\t\t\t"
        [:option {:value "BY"} "Belarus"]
        "\t\t\t\t\t"
        [:option {:value "BE"} "Belgium"]
        "\t\t\t\t\t"
        [:option {:value "BZ"} "Belize"]
        "\t\t\t\t\t"
        [:option {:value "BJ"} "Benin"]
        "\t\t\t\t\t"
        [:option {:value "BM"} "Bermuda"]
        "\t\t\t\t\t"
        [:option {:value "BT"} "Bhutan"]
        "\t\t\t\t\t"
        [:option {:value "BO"} "Bolivia"]
        "\t\t\t\t\t"
        [:option {:value "BA"} "Bosnia and Herzegovina"]
        "\t\t\t\t\t"
        [:option {:value "BW"} "Botswana"]
        "\t\t\t\t\t"
        [:option {:value "BV"} "Bouvet Island"]
        "\t\t\t\t\t"
        [:option {:value "BR"} "Brazil"]
        "\t\t\t\t\t"
        [:option {:value "IO"} "British Indian Ocean Territory"]
        "\t\t\t\t\t"
        [:option {:value "BN"} "Brunei Darussalam"]
        "\t\t\t\t\t"
        [:option {:value "BG"} "Bulgaria"]
        "\t\t\t\t\t"
        [:option {:value "BF"} "Burkina Faso"]
        "\t\t\t\t\t"
        [:option {:value "BI"} "Burundi"]
        "\t\t\t\t\t"
        [:option {:value "KH"} "Cambodia"]
        "\t\t\t\t\t"
        [:option {:value "CM"} "Cameroon"]
        "\t\t\t\t\t"
        [:option {:selected "", :value "CA"} "Canada"]
        "\t\t\t\t\t"
        [:option {:value "CV"} "Cape Verde"]
        "\t\t\t\t\t"
        [:option {:value "KY"} "Cayman Islands"]
        "\t\t\t\t\t"
        [:option {:value "CF"} "Central African Republic"]
        "\t\t\t\t\t"
        [:option {:value "TD"} "Chad"]
        "\t\t\t\t\t"
        [:option {:value "CL"} "Chile"]
        "\t\t\t\t\t"
        [:option {:value "CN"} "China"]
        "\t\t\t\t\t"
        [:option {:value "CX"} "Christmas Island"]
        "\t\t\t\t\t"
        [:option {:value "CC"} "Cocos (Keeling) Islands"]
        "\t\t\t\t\t"
        [:option {:value "CO"} "Colombia"]
        "\t\t\t\t\t"
        [:option {:value "KM"} "Comoros"]
        "\t\t\t\t\t"
        [:option {:value "CG"} "Congo"]
        "\t\t\t\t\t"
        [:option {:value "CD"} "Congo, The Democratic Republic of the"]
        "\t\t\t\t\t"
        [:option {:value "CK"} "Cook Islands"]
        "\t\t\t\t\t"
        [:option {:value "CR"} "Costa Rica"]
        "\t\t\t\t\t"
        [:option {:value "CI"} "C?te D鈥檌voire"]
        "\t\t\t\t\t"
        [:option {:value "HR"} "Croatia"]
        "\t\t\t\t\t"
        [:option {:value "CU"} "Cuba"]
        "\t\t\t\t\t"
        [:option {:value "CY"} "Cyprus"]
        "\t\t\t\t\t"
        [:option {:value "CZ"} "Czech Republic"]
        "\t\t\t\t\t"
        [:option {:value "DK"} "Denmark"]
        "\t\t\t\t\t"
        [:option {:value "DJ"} "Djibouti"]
        "\t\t\t\t\t"
        [:option {:value "DM"} "Dominica"]
        "\t\t\t\t\t"
        [:option {:value "DO"} "Dominican Republic"]
        "\t\t\t\t\t"
        [:option {:value "EC"} "Ecuador"]
        "\t\t\t\t\t"
        [:option {:value "EG"} "Egypt"]
        "\t\t\t\t\t"
        [:option {:value "SV"} "El Salvador"]
        "\t\t\t\t\t"
        [:option {:value "GQ"} "Equatorial Guinea"]
        "\t\t\t\t\t"
        [:option {:value "ER"} "Eritrea"]
        "\t\t\t\t\t"
        [:option {:value "EE"} "Estonia"]
        "\t\t\t\t\t"
        [:option {:value "ET"} "Ethiopia"]
        "\t\t\t\t\t"
        [:option {:value "FK"} "Falkland Islands (Malvinas)"]
        "\t\t\t\t\t"
        [:option {:value "FO"} "Faroe Islands"]
        "\t\t\t\t\t"
        [:option {:value "FJ"} "Fiji"]
        "\t\t\t\t\t"
        [:option {:value "FI"} "Finland"]
        "\t\t\t\t\t"
        [:option {:value "FR"} "France"]
        "\t\t\t\t\t"
        [:option {:value "GF"} "French Guiana"]
        "\t\t\t\t\t"
        [:option {:value "PF"} "French Polynesia"]
        "\t\t\t\t\t"
        [:option {:value "TF"} "French Southern Territories"]
        "\t\t\t\t\t"
        [:option {:value "GA"} "Gabon"]
        "\t\t\t\t\t"
        [:option {:value "GM"} "Gambia"]
        "\t\t\t\t\t"
        [:option {:value "GE"} "Georgia"]
        "\t\t\t\t\t"
        [:option {:value "DE"} "Germany"]
        "\t\t\t\t\t"
        [:option {:value "GH"} "Ghana"]
        "\t\t\t\t\t"
        [:option {:value "GI"} "Gibraltar"]
        "\t\t\t\t\t"
        [:option {:value "GR"} "Greece"]
        "\t\t\t\t\t"
        [:option {:value "GL"} "Greenland"]
        "\t\t\t\t\t"
        [:option {:value "GD"} "Grenada"]
        "\t\t\t\t\t"
        [:option {:value "GP"} "Guadeloupe"]
        "\t\t\t\t\t"
        [:option {:value "GU"} "Guam"]
        "\t\t\t\t\t"
        [:option {:value "GT"} "Guatemala"]
        "\t\t\t\t\t"
        [:option {:value "GG"} "Guernsey"]
        "\t\t\t\t\t"
        [:option {:value "GN"} "Guinea"]
        "\t\t\t\t\t"
        [:option {:value "GW"} "Guinea-Bissau"]
        "\t\t\t\t\t"
        [:option {:value "GY"} "Guyana"]
        "\t\t\t\t\t"
        [:option {:value "HT"} "Haiti"]
        "\t\t\t\t\t"
        [:option {:value "HM"} "Heard Island and McDonald Islands"]
        "\t\t\t\t\t"
        [:option {:value "VA"} "Holy See (Vatican City state)"]
        "\t\t\t\t\t"
        [:option {:value "HN"} "Honduras"]
        "\t\t\t\t\t"
        [:option {:value "HK"} "Hong Kong"]
        "\t\t\t\t\t"
        [:option {:value "HU"} "Hungary"]
        "\t\t\t\t\t"
        [:option {:value "IS"} "Iceland"]
        "\t\t\t\t\t"
        [:option {:value "IN"} "India"]
        "\t\t\t\t\t"
        [:option {:value "ID"} "Indonesia"]
        "\t\t\t\t\t"
        [:option {:value "IR"} "Iran, Islamic Republic of"]
        "\t\t\t\t\t"
        [:option {:value "IQ"} "Iraq"]
        "\t\t\t\t\t"
        [:option {:value "IE"} "Ireland"]
        "\t\t\t\t\t"
        [:option {:value "IM"} "Isle Of Man"]
        "\t\t\t\t\t"
        [:option {:value "IL"} "Israel"]
        "\t\t\t\t\t"
        [:option {:value "IT"} "Italy"]
        "\t\t\t\t\t"
        [:option {:value "JM"} "Jamaica"]
        "\t\t\t\t\t"
        [:option {:value "JP"} "Japan"]
        "\t\t\t\t\t"
        [:option {:value "JE"} "Jersey"]
        "\t\t\t\t\t"
        [:option {:value "JO"} "Jordan"]
        "\t\t\t\t\t"
        [:option {:value "KZ"} "Kazakhstan"]
        "\t\t\t\t\t"
        [:option {:value "KE"} "Kenya"]
        "\t\t\t\t\t"
        [:option {:value "KI"} "Kiribati"]
        "\t\t\t\t\t"
        [:option
         {:value "KP"}
         "Korea, Democratic People鈥檚 Republic Of"]
        "\t\t\t\t\t"
        [:option {:value "KR"} "Korea, Republic of"]
        "\t\t\t\t\t"
        [:option {:value "KW"} "Kuwait"]
        "\t\t\t\t\t"
        [:option {:value "KG"} "Kyrgyzstan"]
        "\t\t\t\t\t"
        [:option {:value "LA"} "Lao People鈥檚 Democratic Republic"]
        "\t\t\t\t\t"
        [:option {:value "LV"} "Latvia"]
        "\t\t\t\t\t"
        [:option {:value "LB"} "Lebanon"]
        "\t\t\t\t\t"
        [:option {:value "LS"} "Lesotho"]
        "\t\t\t\t\t"
        [:option {:value "LR"} "Liberia"]
        "\t\t\t\t\t"
        [:option {:value "LY"} "Libyan Arab Jamahiriya"]
        "\t\t\t\t\t"
        [:option {:value "LI"} "Liechtenstein"]
        "\t\t\t\t\t"
        [:option {:value "LT"} "Lithuania"]
        "\t\t\t\t\t"
        [:option {:value "LU"} "Luxembourg"]
        "\t\t\t\t\t"
        [:option {:value "MO"} "Macao"]
        "\t\t\t\t\t"
        [:option
         {:value "MK"}
         "Macedonia, The Former Yugoslav Republic of"]
        "\t\t\t\t\t"
        [:option {:value "MG"} "Madagascar"]
        "\t\t\t\t\t"
        [:option {:value "MW"} "Malawi"]
        "\t\t\t\t\t"
        [:option {:value "MY"} "Malaysia"]
        "\t\t\t\t\t"
        [:option {:value "MV"} "Maldives"]
        "\t\t\t\t\t"
        [:option {:value "ML"} "Mali"]
        "\t\t\t\t\t"
        [:option {:value "MT"} "Malta"]
        "\t\t\t\t\t"
        [:option {:value "MH"} "Marshall Islands"]
        "\t\t\t\t\t"
        [:option {:value "MQ"} "Martinique"]
        "\t\t\t\t\t"
        [:option {:value "MR"} "Mauritania"]
        "\t\t\t\t\t"
        [:option {:value "MU"} "Mauritius"]
        "\t\t\t\t\t"
        [:option {:value "YT"} "Mayotte"]
        "\t\t\t\t\t"
        [:option {:value "MX"} "Mexico"]
        "\t\t\t\t\t"
        [:option {:value "FM"} "Micronesia, Federated States of"]
        "\t\t\t\t\t"
        [:option {:value "MD"} "Moldova, Republic of"]
        "\t\t\t\t\t"
        [:option {:value "MC"} "Monaco"]
        "\t\t\t\t\t"
        [:option {:value "MN"} "Mongolia"]
        "\t\t\t\t\t"
        [:option {:value "ME"} "Montenegro"]
        "\t\t\t\t\t"
        [:option {:value "MS"} "Montserrat"]
        "\t\t\t\t\t"
        [:option {:value "MA"} "Morocco"]
        "\t\t\t\t\t"
        [:option {:value "MZ"} "Mozambique"]
        "\t\t\t\t\t"
        [:option {:value "MM"} "Myanmar"]
        "\t\t\t\t\t"
        [:option {:value "NA"} "Namibia"]
        "\t\t\t\t\t"
        [:option {:value "NR"} "Nauru"]
        "\t\t\t\t\t"
        [:option {:value "NP"} "Nepal"]
        "\t\t\t\t\t"
        [:option {:value "NL"} "Netherlands"]
        "\t\t\t\t\t"
        [:option {:value "AN"} "Netherlands Antilles"]
        "\t\t\t\t\t"
        [:option {:value "NC"} "New Caledonia"]
        "\t\t\t\t\t"
        [:option {:value "NZ"} "New Zealand"]
        "\t\t\t\t\t"
        [:option {:value "NI"} "Nicaragua"]
        "\t\t\t\t\t"
        [:option {:value "NE"} "Niger"]
        "\t\t\t\t\t"
        [:option {:value "NG"} "Nigeria"]
        "\t\t\t\t\t"
        [:option {:value "NU"} "Niue"]
        "\t\t\t\t\t"
        [:option {:value "NF"} "Norfolk Island"]
        "\t\t\t\t\t"
        [:option {:value "MP"} "Northern Mariana Islands"]
        "\t\t\t\t\t"
        [:option {:value "NO"} "Norway"]
        "\t\t\t\t\t"
        [:option {:value "OM"} "Oman"]
        "\t\t\t\t\t"
        [:option {:value "PK"} "Pakistan"]
        "\t\t\t\t\t"
        [:option {:value "PW"} "Palau"]
        "\t\t\t\t\t"
        [:option {:value "PS"} "Palestinian Territory, Occupied"]
        "\t\t\t\t\t"
        [:option {:value "PA"} "Panama"]
        "\t\t\t\t\t"
        [:option {:value "PG"} "Papua New Guinea"]
        "\t\t\t\t\t"
        [:option {:value "PY"} "Paraguay"]
        "\t\t\t\t\t"
        [:option {:value "PE"} "Peru"]
        "\t\t\t\t\t"
        [:option {:value "PH"} "Philippines"]
        "\t\t\t\t\t"
        [:option {:value "PN"} "Pitcairn"]
        "\t\t\t\t\t"
        [:option {:value "PL"} "Poland"]
        "\t\t\t\t\t"
        [:option {:value "PT"} "Portugal"]
        "\t\t\t\t\t"
        [:option {:value "PR"} "Puerto Rico"]
        "\t\t\t\t\t"
        [:option {:value "QA"} "Qatar"]
        "\t\t\t\t\t"
        [:option {:value "RE"} "Reunion"]
        "\t\t\t\t\t"
        [:option {:value "RO"} "Romania"]
        "\t\t\t\t\t"
        [:option {:value "RU"} "Russian Federation"]
        "\t\t\t\t\t"
        [:option {:value "RW"} "Rwanda"]
        "\t\t\t\t\t"
        [:option {:value "BL"} "Saint Barth茅lemy"]
        "\t\t\t\t\t"
        [:option {:value "SH"} "Saint Helena"]
        "\t\t\t\t\t"
        [:option {:value "KN"} "Saint Kitts and Nevis"]
        "\t\t\t\t\t"
        [:option {:value "LC"} "Saint Lucia"]
        "\t\t\t\t\t"
        [:option {:value "MF"} "Saint Martin"]
        "\t\t\t\t\t"
        [:option {:value "PM"} "Saint Pierre and Miquelon"]
        "\t\t\t\t\t"
        [:option {:value "VC"} "Saint Vincent and the Grenadines"]
        "\t\t\t\t\t"
        [:option {:value "WS"} "Samoa"]
        "\t\t\t\t\t"
        [:option {:value "SM"} "San Marino"]
        "\t\t\t\t\t"
        [:option {:value "ST"} "Sao Tome and Principe"]
        "\t\t\t\t\t"
        [:option {:value "SA"} "Saudi Arabia"]
        "\t\t\t\t\t"
        [:option {:value "SN"} "Senegal"]
        "\t\t\t\t\t"
        [:option {:value "RS"} "Serbia"]
        "\t\t\t\t\t"
        [:option {:value "CS"} "Serbia and Montenegro"]
        "\t\t\t\t\t"
        [:option {:value "SC"} "Seychelles"]
        "\t\t\t\t\t"
        [:option {:value "SL"} "Sierra Leone"]
        "\t\t\t\t\t"
        [:option {:value "SG"} "Singapore"]
        "\t\t\t\t\t"
        [:option {:value "SK"} "Slovakia"]
        "\t\t\t\t\t"
        [:option {:value "SI"} "Slovenia"]
        "\t\t\t\t\t"
        [:option {:value "SB"} "Solomon Islands"]
        "\t\t\t\t\t"
        [:option {:value "SO"} "Somalia"]
        "\t\t\t\t\t"
        [:option {:value "ZA"} "South Africa"]
        "\t\t\t\t\t"
        [:option
         {:value "GS"}
         "South Georgia and the South Sandwich Islands"]
        "\t\t\t\t\t"
        [:option {:value "ES"} "Spain"]
        "\t\t\t\t\t"
        [:option {:value "LK"} "Sri Lanka"]
        "\t\t\t\t\t"
        [:option {:value "SD"} "Sudan"]
        "\t\t\t\t\t"
        [:option {:value "SR"} "Suriname"]
        "\t\t\t\t\t"
        [:option {:value "SJ"} "Svalbard and Jan Mayen"]
        "\t\t\t\t\t"
        [:option {:value "SZ"} "Swaziland"]
        "\t\t\t\t\t"
        [:option {:value "SE"} "Sweden"]
        "\t\t\t\t\t"
        [:option {:value "CH"} "Switzerland"]
        "\t\t\t\t\t"
        [:option {:value "SY"} "Syrian Arab Republic"]
        "\t\t\t\t\t"
        [:option {:value "TW"} "Taiwan, Province of China"]
        "\t\t\t\t\t"
        [:option {:value "TJ"} "Tajikistan"]
        "\t\t\t\t\t"
        [:option {:value "TZ"} "Tanzania, United Republic of"]
        "\t\t\t\t\t"
        [:option {:value "TH"} "Thailand"]
        "\t\t\t\t\t"
        [:option {:value "TL"} "Timor-Leste"]
        "\t\t\t\t\t"
        [:option {:value "TG"} "Togo"]
        "\t\t\t\t\t"
        [:option {:value "TK"} "Tokelau"]
        "\t\t\t\t\t"
        [:option {:value "TO"} "Tonga"]
        "\t\t\t\t\t"
        [:option {:value "TT"} "Trinidad and Tobago"]
        "\t\t\t\t\t"
        [:option {:value "TN"} "Tunisia"]
        "\t\t\t\t\t"
        [:option {:value "TR"} "Turkey"]
        "\t\t\t\t\t"
        [:option {:value "TM"} "Turkmenistan"]
        "\t\t\t\t\t"
        [:option {:value "TC"} "Turks and Caicos Islands"]
        "\t\t\t\t\t"
        [:option {:value "TV"} "Tuvalu"]
        "\t\t\t\t\t"
        [:option {:value "UG"} "Uganda"]
        "\t\t\t\t\t"
        [:option {:value "UA"} "Ukraine"]
        "\t\t\t\t\t"
        [:option {:value "AE"} "United Arab Emirates"]
        "\t\t\t\t\t"
        [:option {:value "GB"} "United Kingdom"]
        "\t\t\t\t\t"
        [:option {:value "US"} "United States"]
        "\t\t\t\t\t"
        [:option {:value "UM"} "United States Minor Outlying Islands"]
        "\t\t\t\t\t"
        [:option {:value "UY"} "Uruguay"]
        "\t\t\t\t\t"
        [:option {:value "UZ"} "Uzbekistan"]
        "\t\t\t\t\t"
        [:option {:value "VU"} "Vanuatu"]
        "\t\t\t\t\t"
        [:option {:value "VE"} "Venezuela"]
        "\t\t\t\t\t"
        [:option {:value "VN"} "Viet Nam"]
        "\t\t\t\t\t"
        [:option {:value "VG"} "Virgin Islands, British"]
        "\t\t\t\t\t"
        [:option {:value "VI"} "Virgin Islands, U.S."]
        "\t\t\t\t\t"
        [:option {:value "WF"} "Wallis and Futuna"]
        "\t\t\t\t\t"
        [:option {:value "EH"} "Western Sahara"]
        "\t\t\t\t\t"
        [:option {:value "YE"} "Yemen"]
        "\t\t\t\t\t"
        [:option {:value "ZM"} "Zambia"]
        "\t\t\t\t\t"
        [:option {:value "ZW"} "Zimbabwe"]
        "\t\t\t\t"]
       "   \t\t\t"]
      "   \t\t"]
     "\t\t"
     [:tr
      [:td
       [:font {:color "000000", :size "2", :face "Arial"} "Email:"]]
      [:td
       [:input#ordEmailAddress
        {:autocomplete "off",
         :maxlength "64",
         :size "30",
         :value "",
         :name "ordEmailAddress",
         :type "text"}]]
      "   \t\t"]
     "\t\t"
     [:tr
      [:td
       {:height "0", :colspan "3"}
       [:input {:value "", :name "shipName", :type "hidden"}]
       [:input {:value "", :name "shipEmailAddress", :type "hidden"}]
       [:input {:value "", :name "shipPhoneNumber", :type "hidden"}]
       [:input {:value "", :name "shipAddress1", :type "hidden"}]
       [:input {:value "", :name "shipAddress2", :type "hidden"}]
       [:input {:value "", :name "shipCity", :type "hidden"}]
       [:input {:value "", :name "shipProvince", :type "hidden"}]
       [:input {:value "", :name "shipPostalCode", :type "hidden"}]
       [:input {:value "", :name "shipCountry", :type "hidden"}]]]
     [:tr [:td {:colspan "3"} [:br]]]
     "   \t\t"
     [:tr
      [:td
       {:valign "top", :height "45px", :colspan "3"}
       [:div
        {:style "float:left;"}
        [:font
         {:color "000066", :size "3", :face "Arial"}
         [:b "Payment Information"]]]]]
     "   \t\t"
     [:tr
      [:td {:rowspan "7"} " "]
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        "Invoice/Order Number:"]]
      [:td
       [:input#trnOrderNumber
        {:autocomplete "off",
         :maxlength "30",
         :size "30",
         :value "",
         :name "trnOrderNumber",
         :type "text"}]]]
     "\t\t"
     [:tr
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        "Name on card:"]]
      [:td
       [:input#trnCardOwner
        {:autocomplete "off",
         :maxlength "64",
         :size "30",
         :value "",
         :name "trnCardOwner",
         :type "text"}]]]
     "\t   \t\t"
     [:tr
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        "Credit Card Type:"]]
      [:td
       [:select#trnCardType
        {:onchange "CheckPaymentType()", :name "trnCardType"}
        [:option {:value "VI"} "VISA"]
        [:option {:value "MC"} "MasterCard"]]]]
     "   \t\t"
     [:tr
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        "Credit Card Number:"]]
      [:td
       [:input#trnCardNumber
        {:autocomplete "off",
         :onchange "ValidateCardNumber();",
         :maxlength "20",
         :size "30",
         :value "",
         :name "trnCardNumber",
         :type "text"}]]]
     "\t\t"
     [:tr
      [:td
       [:font
        {:color "000000", :size "2", :face "Arial"}
        "Expiration Date:"]]
      "   \t\t\t"
      [:td
       "   \t\t\t\t"
       [:select#trnExpMonth
        {:name "trnExpMonth"}
        "   \t\t\t\t\t"
        [:option {:value "01"} "01\t\t\t\t\t"]
        [:option {:value "02"} "02\t\t\t\t\t"]
        [:option {:value "03"} "03\t\t\t\t\t"]
        [:option {:value "04"} "04\t\t\t\t\t"]
        [:option {:value "05"} "05\t\t\t\t\t"]
        [:option {:value "06"} "06\t\t\t\t\t"]
        [:option {:value "07"} "07\t\t\t\t\t"]
        [:option {:value "08"} "08\t\t\t\t\t"]
        [:option {:value "09"} "09\t\t\t\t\t"]
        [:option {:value "10"} "10\t\t\t\t\t"]
        [:option {:value "11"} "11\t\t\t\t\t"]
        [:option {:value "12"} "12   \t\t\t\t"]]
       "   \t\t\t\t/   \t\t\t\t"
       [:select#trnExpYear
        {:name "trnExpYear"}
        "\t\t\t\t\t"
        [:option {:value "13"} "2013\t\t\t\t\t"]
        [:option {:value "14"} "2014\t\t\t\t\t"]
        [:option {:value "15"} "2015\t\t\t\t\t"]
        [:option {:value "16"} "2016\t\t\t\t\t"]
        [:option {:value "17"} "2017\t\t\t\t\t"]
        [:option {:value "18"} "2018\t\t\t\t\t"]
        [:option {:value "19"} "2019\t\t\t\t\t"]
        [:option {:value "20"} "2020\t\t\t\t\t"]
        [:option {:value "21"} "2021\t\t\t\t\t"]
        [:option {:value "22"} "2022\t\t\t\t\t"]
        [:option {:value "23"} "2023\t\t\t\t\t"]
        [:option {:value "24"} "2024\t\t\t\t\t"]
        [:option {:value "25"} "2025   \t\t\t\t"]]
       "   \t\t\t"]
      "   \t\t"]
     "\t\t"
     [:tr [:td {:colspan "3"} [:br]]]
     "\t\t"
     [:tr
      [:td
       {:valign "top", :height "30px", :colspan "3"}
       [:font
        {:color "000066", :size "3", :face "Arial"}
        [:b "Comments"]]]]
     "\t\t"
     [:tr
      [:td
       {:colspan "3"}
       [:textarea#trnComments
        {:cols "40", :rows "4", :name "trnComments"}]]]
     "\t"]])

(defn index [session]
  (let [orders (not-empty (:orders session))
        sum (reduce + (for [order orders] (read-string (:payment_amt order))))]
    (binding [template/*js-css-files* template/orders-files 
              template/*sub-nav* nil]
      (template/pages
        [:dl.txtcont
         [:dt [:div.ltit [:strong "Orders in Cart"]] [:div.clear]]
         [:form {:method "post" :action "/cart/pay"}
          [:dd
           [:div.fc_con
            [:table.fcc_tab {:width "100%" :cellspacing "0" :cellpadding "0" :border "0"}
             [:tr
              [:th.sf_admin_text.sf_admin_list_th
               "Form Name"]
              [:th.sf_admin_list_th "pirce"]
              [:th.sf_admin_list_th "Actions"]]
             (for [order orders]
              [:tr
               [:td.sf_admin_text.sf_admin_list_td_form_name (:form_name order)]
               [:td.sf_admin_text.sf_admin_list_td_form_name (:payment_amt order)]
               [:td [:ul.sf_admin_td_actions
                     [:li.sf_admin_action_delete [:a "Remove"]]]]])
             ]]] 
          [:span "Total Amount: "]
          [:b (str "$" sum)] 
          [:br] [:br] [:br]
          payment_tb
          [:input {:value "Pay" :type "submit"}]]]))))

(defn pay [params session]
  (let [orders (not-empty (:orders session))
        responses (map 
                    (fn [order] (merge order (form/order_action (merge order params))))
                    orders)
        errors (filter 
                  (fn [response] (if (.contains (:body response) "trnApproved=0")
                                   true
                                   false))
                  responses)]
    (binding [template/*js-css-files* template/form-view-files]
      (if (empty? errors)
        (template/commit-page [:div "Thank you, your payment is successful! Tsask will process your request shortly. If you have not received confirmation in a few days please contact us."]) 
        (template/commit-page 
          (for [error errors] 
            [:div (str "Payment attempt failed for order:" 
                       (:form_name error)
                       " due to reason: " 
                       (:body error) 
                       "<br>Please contact us and we will help you to submit your payment.")]))))))

(defn create [params] 
  (let [order_record (order/create {:order_content (:order_content params) 
                                    :form_name (:form_name params)})]))

(defn delete [id]
  (if (.exists (io/file (str "resources/public/files/Invoice-" id ".jpg")))
    (io/delete-file (str "resources/public/files/Invoice-" id ".jpg")))
    (if (.exists (io/file (str "resources/public/files/Invoice-" id ".pdf")))
      (io/delete-file (str "resources/public/files/Invoice-" id ".pdf")))
  (j/delete! SQLDB :sa_orders (sql/where {:id id}))
  (j/delete! SQLDB :CSV_report (sql/where {:o_id id}))
  (redirect "/orders"))
