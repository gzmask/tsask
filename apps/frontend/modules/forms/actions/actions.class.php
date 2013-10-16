<?php

/**
 * forms actions.
 *
 * @package    authority
 * @subpackage forms
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class formsActions extends sfActions
{
  /*public function executeIndex(sfWebRequest $request)
  {
    $this->sa_formss = Doctrine_Core::getTable('saForms')
      ->createQuery('a')
      ->execute();
  }*/

  public function executeShow(sfWebRequest $request)
  {
    $this->sa_forms = Doctrine_Core::getTable('saForms')->find(array($request->getParameter('id')));
    $this->forward404Unless($this->sa_forms);
	//$this->logMessage('$this->form_content  '.$this->sa_forms['form_content']);
	//$this->logMessage('$this->form_published  '.$this->sa_forms['form_published']);		
    $this->form_name=$this->sa_forms['form_name'];
    $this->form_published=$this->sa_forms['form_published'];
  }
  
  public function executeCommit(sfWebRequest $request)
  {
    //$this->sa_forms = Doctrine_Core::getTable('saForms')->find(array($request->getParameter('id')));
    //$this->forward404Unless($this->sa_forms);

    /*

          $countryId[0]="AF";
          $countryId[1]="AR";
          $countryId[2]="AX";
          $countryId[3]="AL";
          $countryId[4]="DZ";
          $countryId[5]="AS";
          $countryId[6]="AD";
          $countryId[7]="AO";
          $countryId[8]="AI";
          $countryId[9]="AQ";
          $countryId[10]="AG";
          $countryId[11]="AM";
          $countryId[12]="AW";
          $countryId[13]="AU";
          $countryId[14]="AT";
          $countryId[15]="AZ";
          $countryId[16]="BS";
          $countryId[17]="BH";
          $countryId[18]="BD";
          $countryId[19]="BB";
          $countryId[20]="BY";
          $countryId[21]="BE";
          $countryId[22]="BZ";
          $countryId[23]="BJ";
          $countryId[24]="BM";
          $countryId[25]="BT";
          $countryId[26]="BO";
          $countryId[27]="BA";
          $countryId[28]="BW";
          $countryId[29]="BV";
          $countryId[30]="BR";
          $countryId[31]="IO";
          $countryId[32]="BN";
          $countryId[33]="BG";
          $countryId[34]="BF";
          $countryId[35]="BI";
          $countryId[36]="KH";
          $countryId[37]="CM";
          $countryId[38]="CA";
          $countryId[39]="CV";
          $countryId[40]="KY";
          $countryId[41]="CF";
          $countryId[42]="TD";
          $countryId[43]="CL";
          $countryId[44]="CN";
          $countryId[45]="CX";
          $countryId[46]="CC";
          $countryId[47]="CO";
          $countryId[48]="KM";
          $countryId[49]="CG";
          $countryId[50]="CD";
          $countryId[51]="CK";
          $countryId[52]="CR";
          $countryId[53]="CI";
          $countryId[54]="HR";
          $countryId[55]="CU";
          $countryId[56]="CY";
          $countryId[57]="CZ";
          $countryId[58]="DK";
          $countryId[59]="DJ";
          $countryId[60]="DM";
          $countryId[61]="DO";
          $countryId[62]="TL";
          $countryId[63]="EC";
          $countryId[64]="EG";
          $countryId[65]="SV";
          $countryId[66]="GQ";
          $countryId[67]="ER";
          $countryId[68]="EE";
          $countryId[69]="ET";
          $countryId[70]="FK";
          $countryId[71]="FO";
          $countryId[72]="FJ";
          $countryId[73]="FI";
          $countryId[74]="FR";
          $countryId[75]="GF";
          $countryId[76]="PF";
          $countryId[77]="TF";
          $countryId[78]="GA";
          $countryId[79]="GM";
          $countryId[80]="GE";
          $countryId[81]="DE";
          $countryId[82]="GH";
          $countryId[83]="GI";
          $countryId[84]="GB";
          $countryId[85]="GR";
          $countryId[86]="GL";
          $countryId[87]="GD";
          $countryId[88]="GP";
          $countryId[89]="GU";
          $countryId[90]="GT";
          $countryId[91]="GN";
          $countryId[92]="GW";
          $countryId[93]="GY";
          $countryId[94]="HT";
          $countryId[95]="HM";
          $countryId[96]="HN";
          $countryId[97]="HK";
          $countryId[98]="HU";
          $countryId[99]="IS";
          $countryId[100]="IN";
          $countryId[101]="ID";
          $countryId[102]="IR";
          $countryId[103]="IQ";
          $countryId[104]="IE";
          $countryId[105]="IL";
          $countryId[106]="IT";
          $countryId[107]="JM";
          $countryId[108]="JP";
          $countryId[109]="JO";
          $countryId[110]="KZ";
          $countryId[111]="KE";
          $countryId[112]="KI";
          $countryId[113]="KP";
          $countryId[114]="KR";
          $countryId[115]="KW";
          $countryId[116]="KG";
          $countryId[117]="LA";
          $countryId[118]="LV";
          $countryId[119]="LB";
          $countryId[120]="LI";
          $countryId[121]="LS";
          $countryId[122]="LR";
          $countryId[123]="LY";
          $countryId[124]="LT";
          $countryId[125]="LU";
          $countryId[126]="MO";
          $countryId[127]="MK";
          $countryId[128]="MG";
          $countryId[129]="MW";
          $countryId[130]="MY";
          $countryId[131]="MV";
          $countryId[132]="ML";
          $countryId[133]="MT";
          $countryId[134]="MH";
          $countryId[135]="MQ";
          $countryId[136]="MR";
          $countryId[137]="MU";
          $countryId[138]="YT";
          $countryId[139]="MX";
          $countryId[140]="FM";
          $countryId[141]="MD";
          $countryId[142]="MC";
          $countryId[143]="MN";
          $countryId[144]="MS";
          $countryId[145]="MA";
          $countryId[146]="MZ";
          $countryId[147]="MM";
          $countryId[148]="NA";
          $countryId[149]="NR";
          $countryId[150]="NP";
          $countryId[151]="NL";
          $countryId[152]="AN";
          $countryId[153]="NC";
          $countryId[154]="NZ";
          $countryId[155]="NI";
          $countryId[156]="NE";
          $countryId[157]="NG";
          $countryId[158]="NU";
          $countryId[159]="NF";
          $countryId[160]="MP";
          $countryId[161]="NO";
          $countryId[162]="OM";
          $countryId[163]="PK";
          $countryId[164]="PW";
          $countryId[165]="PS";
          $countryId[166]="PA";
          $countryId[167]="PG";
          $countryId[168]="PY";
          $countryId[169]="PE";
          $countryId[170]="PH";
          $countryId[171]="PN";
          $countryId[172]="PL";
          $countryId[173]="PT";
          $countryId[174]="PR";
          $countryId[175]="QA";
          $countryId[176]="RE";
          $countryId[177]="RO";
          $countryId[178]="RU";
          $countryId[179]="RW";
          $countryId[180]="KN";
          $countryId[181]="LC";
          $countryId[182]="VC";
          $countryId[183]="WS";
          $countryId[184]="SM";
          $countryId[185]="ST";
          $countryId[186]="SA";
          $countryId[187]="SN";
          $countryId[188]="CS";
          $countryId[189]="SC";
          $countryId[190]="SL";
          $countryId[191]="SG";
          $countryId[192]="SK";
          $countryId[193]="SI";
          $countryId[194]="SB";
          $countryId[195]="SO";
          $countryId[196]="ZA";
          $countryId[197]="GS";
          $countryId[198]="ES";
          $countryId[199]="LK";
          $countryId[200]="SH";
          $countryId[201]="PM";
          $countryId[202]="SD";
          $countryId[203]="SR";
          $countryId[204]="SJ";
          $countryId[205]="SZ";
          $countryId[206]="SE";
          $countryId[207]="CH";
          $countryId[208]="SY";
          $countryId[209]="TW";
          $countryId[210]="TJ";
          $countryId[211]="TZ";
          $countryId[212]="TH";
          $countryId[213]="TG";
          $countryId[214]="TK";
          $countryId[215]="TO";
          $countryId[216]="TT";
          $countryId[217]="TN";
          $countryId[218]="TR";
          $countryId[219]="TM";
          $countryId[220]="TC";
          $countryId[221]="TV";
          $countryId[222]="UG";
          $countryId[223]="UA";
          $countryId[224]="AE";
          $countryId[225]="US";
          $countryId[226]="UM";
          $countryId[227]="UY";
          $countryId[228]="UZ";
          $countryId[229]="VU";
          $countryId[230]="VA";
          $countryId[231]="VE";
          $countryId[232]="VN";
          $countryId[233]="VG";
          $countryId[234]="VI";
          $countryId[235]="WF";
          $countryId[236]="EH";
          $countryId[237]="YE";
          $countryId[238]="ZM";
          $countryId[239]="ZW";	
          
          $countryName[0]="Afghanistan";
          $countryName[1]="Argentina";
          $countryName[2]="?land Islands";
          $countryName[3]="Albania";
          $countryName[4]="Algeria";
          $countryName[5]="American Samoa";
          $countryName[6]="Andorra";
          $countryName[7]="Angola";
          $countryName[8]="Anguilla";
          $countryName[9]="Antarctica";
          $countryName[10]="Antigua and Barbuda";
          $countryName[11]="Armenia";
          $countryName[12]="Aruba";
          $countryName[13]="Australia";
          $countryName[14]="Austria";
          $countryName[15]="Azerbaijan";
          $countryName[16]="Bahamas";
          $countryName[17]="Bahrain";
          $countryName[18]="Bangladesh";
          $countryName[19]="Barbados";
          $countryName[20]="Belarus";
          $countryName[21]="Belgium";
          $countryName[22]="Belize";
          $countryName[23]="Benin";
          $countryName[24]="Bermuda";
          $countryName[25]="Bhutan";
          $countryName[26]="Bolivia";
          $countryName[27]="Bosnia and Herzegovina";
          $countryName[28]="Botswana";
          $countryName[29]="Bouvet Island";
          $countryName[30]="Brazil";
          $countryName[31]="British Indian Ocean Territory";
          $countryName[32]="Brunei Darussalam";
          $countryName[33]="Bulgaria";
          $countryName[34]="Burkina Faso";
          $countryName[35]="Burundi";
          $countryName[36]="Cambodia";
          $countryName[37]="Cameroon";
          $countryName[38]="Canada";
          $countryName[39]="Cape Verde";
          $countryName[40]="Cayman Islands";
          $countryName[41]="Central African Republic";
          $countryName[42]="Chad";
          $countryName[43]="Chile";
          $countryName[44]="China";
          $countryName[45]="Christmas Island";
          $countryName[46]="Cocos (Keeling) Islands";
          $countryName[47]="Columbia";
          $countryName[48]="Comoros";
          $countryName[49]="Congo";
          $countryName[50]="Congo, The Democratic Republic of the";
          $countryName[51]="Cook Islands";
          $countryName[52]="Costa Rica";
          $countryName[53]="Cote d\'Ivoire ¨C Really Ivory Coast";
          $countryName[54]="Croatia";
          $countryName[55]="Cuba";
          $countryName[56]="Cyprus";
          $countryName[57]="Czech Republic";
          $countryName[58]="Denmark";
          $countryName[59]="Djibouti";
          $countryName[60]="Dominica";
          $countryName[61]="Dominican Republic";
          $countryName[62]="East Timor";
          $countryName[63]="Ecuador";
          $countryName[64]="Egypt";
          $countryName[65]="El Salvador";
          $countryName[66]="Equatorial Guinea";
          $countryName[67]="Eritrea";
          $countryName[68]="Estonia";
          $countryName[69]="Ethiopia";
          $countryName[70]="Falkland Islands (Malvinas)";
          $countryName[71]="Faroe Islands";
          $countryName[72]="Fiji";
          $countryName[73]="Finland";
          $countryName[74]="France";
          $countryName[75]="French Guiana";
          $countryName[76]="French Polynesia";
          $countryName[77]="French Southern Territories";
          $countryName[78]="Gabon";
          $countryName[79]="Gambia";
          $countryName[80]="Georgia";
          $countryName[81]="Germany";
          $countryName[82]="Ghana";
          $countryName[83]="Gibraltar";
          $countryName[84]="Great Britain";
          $countryName[85]="Greece";
          $countryName[86]="Greenland";
          $countryName[87]="Grenada";
          $countryName[88]="Guadeloupe";
          $countryName[89]="Guam";
          $countryName[90]="Guatemala";
          $countryName[91]="Guinea";
          $countryName[92]="Guinea Bissau";
          $countryName[93]="Guyana";
          $countryName[94]="Haiti";
          $countryName[95]="Heard and McDonald Islands";
          $countryName[96]="Honduras";
          $countryName[97]="Hong Kong";
          $countryName[98]="Hungary";
          $countryName[99]="Iceland";
          $countryName[100]="India";
          $countryName[101]="Indonesia";
          $countryName[102]="Iran, Islamic Republic of";
          $countryName[103]="Iraq";
          $countryName[104]="Ireland";
          $countryName[105]="Israel";
          $countryName[106]="Italy";
          $countryName[107]="Jamaica";
          $countryName[108]="Japan";
          $countryName[109]="Jordan";
          $countryName[110]="Kazakhstan";
          $countryName[111]="Kenya";
          $countryName[112]="Kiribati";
          $countryName[113]="Korea, Democratic People\'s Republic";
          $countryName[114]="Korea, Republic of";
          $countryName[115]="Kuwait";
          $countryName[116]="Kyrgyzstan";
          $countryName[117]="Lao People\'s Democratic Republic";
          $countryName[118]="Latvia";
          $countryName[119]="Lebanon";
          $countryName[120]="Liechtenstein";
          $countryName[121]="Lesotho";
          $countryName[122]="Liberia";
          $countryName[123]="Libyan Arab Jamahiriya";
          $countryName[124]="Lithuania";
          $countryName[125]="Luxembourg";
          $countryName[126]="Macau";
          $countryName[127]="Macedonia, Former Yugoslav Republic of";
          $countryName[128]="Madagascar";
          $countryName[129]="Malawi";
          $countryName[130]="Malaysia";
          $countryName[131]="Maldives";
          $countryName[132]="Mali";
          $countryName[133]="Malta";
          $countryName[134]="Marshall Islands";
          $countryName[135]="Martinique";
          $countryName[136]="Mauritania";
          $countryName[137]="Mauritius";
          $countryName[138]="Mayotte";
          $countryName[139]="Mexico";
          $countryName[140]="Micronesia, Federated States of";
          $countryName[141]="Moldova, Republic of";
          $countryName[142]="Monaco";
          $countryName[143]="Mongolia";
          $countryName[144]="Montserrat";
          $countryName[145]="Morocco";
          $countryName[146]="Mozambique";
          $countryName[147]="Myanmar";
          $countryName[148]="Namibia";
          $countryName[149]="Nauru";
          $countryName[150]="Nepal";
          $countryName[151]="Netherlands";
          $countryName[152]="Netherlands Antilles";
          $countryName[153]="New Caledonia";
          $countryName[154]="New Zealand";
          $countryName[155]="Nicaragua";
          $countryName[156]="Niger";
          $countryName[157]="Nigeria";
          $countryName[158]="Niue";
          $countryName[159]="Norfolk Island";
          $countryName[160]="Northern Mariana Islands";
          $countryName[161]="Norway";
          $countryName[162]="Oman";
          $countryName[163]="Pakistan";
          $countryName[164]="Palau";
          $countryName[165]="Palestinian Territory, Occupied";
          $countryName[166]="Panama";
          $countryName[167]="Papua New Guinea";
          $countryName[168]="Paraguay";
          $countryName[169]="Peru";
          $countryName[170]="Philippines";
          $countryName[171]="Pitcairn";
          $countryName[172]="Poland";
          $countryName[173]="Portugal";
          $countryName[174]="Puerto Rico";
          $countryName[175]="Qatar";
          $countryName[176]="Reunion";
          $countryName[177]="Romania";
          $countryName[178]="Russian Federation";
          $countryName[179]="Rwanda";
          $countryName[180]="Saint Kitts and Nevis";
          $countryName[181]="Saint Lucia";
          $countryName[182]="Saint Vincent and the Grenadines";
          $countryName[183]="Samoa";
          $countryName[184]="San Marino";
          $countryName[185]="Sao Tome and Principe";
          $countryName[186]="Saudi Arabia";
          $countryName[187]="Senegal";
          $countryName[188]="Serbia and Montenegro";
          $countryName[189]="Seychelles";
          $countryName[190]="Sierra Leone";
          $countryName[191]="Singapore";
          $countryName[192]="Slovakia";
          $countryName[193]="Slovenia";
          $countryName[194]="Solomon Islands";
          $countryName[195]="Somalia";
          $countryName[196]="South Africa";
          $countryName[197]="South Georgia ¨C South Sandwich Islands";
          $countryName[198]="Spain";
          $countryName[199]="Sri Lanka";
          $countryName[200]="St. Helena";
          $countryName[201]="St. Pierre and Miquelon";
          $countryName[202]="Sudan";
          $countryName[203]="Suriname";
          $countryName[204]="Svalbard and Jan Mayen";
          $countryName[205]="Swaziland";
          $countryName[206]="Sweden";
          $countryName[207]="Switzerland";
          $countryName[208]="Syrian Arab Republic";
          $countryName[209]="Taiwan";
          $countryName[210]="Tajikistan";
          $countryName[211]="Tanzania, United Republic of";
          $countryName[212]="Thailand";
          $countryName[213]="Togo";
          $countryName[214]="Tokelau";
          $countryName[215]="Tonga";
          $countryName[216]="Trinidad and Tobago";
          $countryName[217]="Tunisia";
          $countryName[218]="Turkey";
          $countryName[219]="Turkmenistan";
          $countryName[220]="Turks and Caicos Islands";
          $countryName[221]="Tuvalu";
          $countryName[222]="Uganda";
          $countryName[223]="Ukraine";
          $countryName[224]="United Arab Emirates";
          $countryName[225]="United States";
          $countryName[226]="United States Minor Outlying Islands";
          $countryName[227]="Uruguay";
          $countryName[228]="Uzbekistan";
          $countryName[229]="Vanuatu";
          $countryName[230]="Vatican City state";
          $countryName[231]="Venezuela";
          $countryName[232]="Viet Nam";
          $countryName[233]="Virgin Islands (British)";
          $countryName[234]="Virgin Islands (US)";
          $countryName[235]="Wallis and Futuna";
          $countryName[236]="Western Sahara";
          $countryName[237]="Yemen";
          $countryName[238]="Zambia";
          $countryName[239]="Zimbabwe";
          */

          $needPayment=true;
          
          $this->reqClientEmail=$this->getRequestParameter('form_client_email_value');
          
          $this->reqOrdName=$this->getRequestParameter('ordName');
          $this->reqOrdPhoneNumber=$this->getRequestParameter('ordPhoneNumber');	
          $this->reqOrdAddress1=$this->getRequestParameter('ordAddress1');	
          $this->reqOrdAddress2=$this->getRequestParameter('ordAddress2');	
          $this->reqOrdCity=$this->getRequestParameter('ordCity');	
          $this->reqOrdProvince=$this->getRequestParameter('ordProvince');
          $this->reqOrdPostalCode=$this->getRequestParameter('ordPostalCode');		
          $this->reqOrdCountry=$this->getRequestParameter('ordCountry');		
          $this->reqOrdEmailAddress=$this->getRequestParameter('ordEmailAddress');	
          
          $this->reqTrnOrderNumber=$this->getRequestParameter('trnOrderNumber');	
          $this->reqTrnAmount=$this->getRequestParameter('trnAmount');		
          $this->reqTrnCardOwner=$this->getRequestParameter('trnCardOwner');
          $this->reqTrnCardType=$this->getRequestParameter('trnCardType');
          $this->reqTrnCardNumber=$this->getRequestParameter('trnCardNumber');	
          $this->reqTrnExpMonth=$this->getRequestParameter('trnExpMonth');
          $this->reqTrnExpYear=$this->getRequestParameter('trnExpYear');

          if($this->reqOrdName=='' or $this->reqOrdName==null)$needPayment=false;
          /*
          if($this->reqOrdPhoneNumber=='' or $this->reqOrdPhoneNumber==null)$needPayment=false;
          if($this->reqOrdAddress1=='' or $this->reqOrdAddress1==null)$needPayment=false;
          if($this->reqOrdCity=='' or $this->reqOrdCity==null)$needPayment=false;
          if($this->reqOrdProvince=='' or $this->reqOrdProvince==null)$needPayment=false;
          if($this->reqOrdPostalCode=='' or $this->reqOrdPostalCode==null)$needPayment=false;
          if($this->reqOrdCountry=='' or $this->reqOrdCountry==null)$needPayment=false;
          if($this->reqOrdEmailAddress=='' or $this->reqOrdEmailAddress==null)$needPayment=false;
          
          if($this->reqTrnOrderNumber=='' or $this->reqTrnOrderNumber==null)$needPayment=false;
          if($this->reqTrnAmount=='' or $this->reqTrnAmount==null)$needPayment=false;	
          if($this->reqTrnCardOwner=='' or $this->reqTrnCardOwner==null)$needPayment=false;
          if($this->reqTrnCardType=='' or $this->reqTrnCardType==null)$needPayment=false;	
          if($this->reqTrnCardNumber=='' or $this->reqTrnCardNumber==null)$needPayment=false;
          if($this->reqTrnExpMonth=='' or $this->reqTrnExpMonth==null)$needPayment=false;
          if($this->reqTrnExpYear=='' or $this->reqTrnExpYear==null)$needPayment=false;
           */

          //store csv exporter data
          $csv_app_name =  $this->reqOrdName;
          $csv_address = $this->reqOrdAddress1.' '.$this->reqOrdAddress2;
          $csv_phone = $this->reqOrdPhoneNumber;
          $csv_email = $this->reqOrdEmailAddress;
          $csv_app_type = $this->getRequestParameter('form_name');
          $csv_app_detail = $this->getRequestParameter('app_detail');
          $csv_paid_by = $this->reqTrnCardOwner;
          $csv_card_type = $this->reqTrnCardType;
          $csv_payment_amt = $this->getRequestParameter('trnAmount');
          $csv_ch = curl_init();
          $curlConfig = array(
            CURLOPT_URL            => "localhost:3000/csv/create",
            CURLOPT_POST           => true,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_POSTFIELDS     => array(
            'app_name' => $csv_app_name,
            'address' => $csv_address,
            'phone' => $csv_phone,
            'email' => $csv_email,
            'app_type' => $csv_app_type,
            'app_detail' => $csv_app_detail,
            'paid_by' => $csv_paid_by,
            'card_type' => $csv_card_type,
            'payment_amt' => $csv_payment_amt));
          curl_setopt_array($csv_ch, $curlConfig);
          $result = curl_exec($csv_ch);
          curl_close($csv_ch);

          if($needPayment){
          $request_string='';

          $requestType='requestType=BACKEND&';
          $merchant_id='merchant_id=257900000&';

          $ordName='ordName='.$this->reqOrdName.'&';
          $ordPhoneNumber='ordPhoneNumber='.$this->reqOrdPhoneNumber.'&';
          $ordAddress1='ordAddress1='.$this->reqOrdAddress1.'&';
          $ordAddress2='ordAddress2='.$this->reqOrdAddress2.'&';
          $ordCity='ordCity='.$this->reqOrdCity.'&';
          $ordProvince='ordProvince='.$this->reqOrdProvince.'&';
          $ordPostalCode='ordPostalCode='.$this->reqOrdPostalCode.'&';
          $ordCountry='ordCountry='.$this->reqOrdCountry.'&';
          $ordEmailAddress='ordEmailAddress='.$this->reqOrdEmailAddress.'&';

          $trnOrderNumber='trnOrderNumber='.$this->reqTrnOrderNumber.'&';
          $trnAmount='trnAmount='.$this->reqTrnAmount.'&';		
          $trnCardOwner='trnCardOwner='.$this->reqTrnCardOwner.'&';
          $trnCardNumber='trnCardNumber='.$this->reqTrnCardNumber.'&';
          $trnCardType='trnCardType='.$this->reqTrnCardType.'&';		
          $trnExpMonth='trnExpMonth='.$this->reqTrnExpMonth.'&';
          $trnExpYear='trnExpYear='.$this->reqTrnExpYear.'&';


          $request_string.=$requestType;
          $request_string.=$merchant_id;
          
          $request_string.=$ordName;
          $request_string.=$ordPhoneNumber;
          $request_string.=$ordAddress1;
          $request_string.=$ordAddress2;
          $request_string.=$ordCity;
          $request_string.=$ordProvince;
          $request_string.=$ordPostalCode;
          $request_string.=$ordCountry;
          $request_string.=$ordEmailAddress;

          $request_string.=$trnOrderNumber;
          $request_string.=$trnAmount;		
          $request_string.=$trnCardOwner;
          $request_string.=$trnCardNumber;
          $request_string.=$trnCardType;
          $request_string.=$trnExpMonth;
          $request_string.=$trnExpYear;




          
          // Initialize curl
          $ch = curl_init();
          // Get curl to POST
          curl_setopt( $ch, CURLOPT_POST, 1 );
          curl_setopt( $ch, CURLOPT_SSL_VERIFYHOST,0);
          curl_setopt( $ch, CURLOPT_SSL_VERIFYPEER, 0);
          // Instruct curl to suppress the output from Beanstream, and to directly
          // return the transfer instead. (Output will be stored in $txResult.)
          curl_setopt( $ch, CURLOPT_RETURNTRANSFER, 1 );
          // This is the location of the Beanstream payment gateway
          curl_setopt( $ch, CURLOPT_URL, "https://www.beanstream.com/scripts/process_transaction.asp" );
          // These are the transaction parameters that we will POST	
          curl_setopt( $ch, CURLOPT_POSTFIELDS,  $request_string);
          // Now POST the transaction. $txResult will contain Beanstream's response
          //$this->txResult = urldecode(curl_exec( $ch ));
          //$this->txResult.= $request_string;
          parse_str(urldecode(curl_exec( $ch )), $txResult);
          if ($txResult['trnApproved'] == '0') 
            $this->txResult='Payment attempt failed due to reason: '.$txResult['messageText'].' Please contact us and we will help you to submit your payment.';
          else
            $this->txResult='"Thank you, your payment is successful! Tsask will process your request shortly.  If you have not received confirmation in a few days please contact us.';
          $this->order_content=$this->getRequestParameter('order_content');
          $this->form_name=$this->getRequestParameter('form_name');
          $my_order=new saOrders();
          $my_order['order_content']=$this->order_content;	
          $my_order['form_name']=$this->form_name;
          $my_order->save();		
          curl_close( $ch );  
	}else{
          $this->order_content=$this->getRequestParameter('order_content');
          $this->form_name=$this->getRequestParameter('form_name');
          $my_order=new saOrders();
          $my_order['order_content']=$this->order_content;	
          $my_order['form_name']=$this->form_name;
          $my_order->save();
          $this->txResult ='Form Committed without payment!';
	}

	$this->logMessage('$this->order_content  '.$this->sa_forms['order_content']);
        $this->getRequest()->setParameter('txResult', $this->txResult);	
	$this->logMessage('txResult  '.$this->txResult);
	//start send info email
	$mydate=getdate(date("U"));
	$now="$mydate[year]-$mydate[month]-$mydate[mday]  $mydate[hours]:$mydate[minutes]:$mydate[seconds]";
	
	$mailTo=array('2020481441@qq.com'=> '2020481441@qq.com');
	
	if($this->reqClientEmail!='' and $this->reqClientEmail!=null){
		$mailTo[]=$this->reqClientEmail;
	};
	
    
	$message = $this->getMailer()->compose(
      array('tsask0713@163.com' => 'tsaskAdmin'),
      $mailTo,
      'Tsask information',
      <<<EOF
	  form number {$my_order->getId()} at {$now}.
EOF
	);
 
    $this->getMailer()->send($message);

	$this->forward('forms','commitFinished');
  }  

  public function executeCommitFinished(sfWebRequest $request)
  {
	$this->txResult=$this->getRequestParameter('txResult');
  }
  
  /*public function executeNew(sfWebRequest $request)
  {
    $this->form = new saFormsForm();
  }

  public function executeCreate(sfWebRequest $request)
  {
    $this->forward404Unless($request->isMethod(sfRequest::POST));

    $this->form = new saFormsForm();

    $this->processForm($request, $this->form);

    $this->setTemplate('new');
  }

  public function executeEdit(sfWebRequest $request)
  {
    $this->forward404Unless($sa_forms = Doctrine_Core::getTable('saForms')->find(array($request->getParameter('id'))), sprintf('Object sa_forms does not exist (%s).', $request->getParameter('id')));
    $this->form = new saFormsForm($sa_forms);
  }

  public function executeUpdate(sfWebRequest $request)
  {
    $this->forward404Unless($request->isMethod(sfRequest::POST) || $request->isMethod(sfRequest::PUT));
    $this->forward404Unless($sa_forms = Doctrine_Core::getTable('saForms')->find(array($request->getParameter('id'))), sprintf('Object sa_forms does not exist (%s).', $request->getParameter('id')));
    $this->form = new saFormsForm($sa_forms);

    $this->processForm($request, $this->form);

    $this->setTemplate('edit');
  }

  public function executeDelete(sfWebRequest $request)
  {
    $request->checkCSRFProtection();

    $this->forward404Unless($sa_forms = Doctrine_Core::getTable('saForms')->find(array($request->getParameter('id'))), sprintf('Object sa_forms does not exist (%s).', $request->getParameter('id')));
    $sa_forms->delete();

    $this->redirect('forms/index');
  }

  protected function processForm(sfWebRequest $request, sfForm $form)
  {
    $form->bind($request->getParameter($form->getName()), $request->getFiles($form->getName()));
    if ($form->isValid())
    {
      $sa_forms = $form->save();

      $this->redirect('forms/edit?id='.$sa_forms->getId());
    }
  }}*/
}
