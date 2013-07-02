function controlPayment(str_payment_name,str_payment_text, str_form_payment_amount) {
	if (typeof str_payment_name == 'undefined')str_payment_name='';
	if (typeof str_payment_text == 'undefined')str_payment_text='Click to edit this text...';	
	if (typeof str_form_payment_amount == 'undefined')str_form_payment_amount='';

	var control_payment='    <li class="control control_payment">'+
	'    <div class="fbc_bar">'+
	'    <div class="bar_tit">Payment</div>'+
	'    <div class="bar_btns"><span>'+
	'    <a href="#!" title="ARROW DOWN" class="btn_down" ></a>'+
	'    <a href="#!" title="ARROW UP" class="btn_up"></a>'+
	'    <a href="#!" title="ARROW BOTTOM" class="btn_bottom"></a>'+
	'    <a href="#!" title="ARROW TOP" class="btn_top"></a>'+
	'    <a href="#!" title="DELETE" class="btn_del"></a>'+
	'    </span></div>'+
	'    </div>'+
	'    <div class="fbc_txt">'+
	'    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">'+
	
	'  <tr>'+
	'    <td width="118">control name:</td>'+
	'    <td align="left"><input class="intxt" id="payment_name" value="'+str_payment_name+'" style="width:235px;"/></td>'+
	'  </tr>'+	
	
	'  <tr>'+
	'    <td width="118" valign="top"><input class="intxt" id="payment_text" value="'+str_payment_text+'" onfocus="if(value ==\'Click to edit this text...\'){value =\'\'}" onblur="if (value ==\'\'){value=\'Click to edit this text...\'}" style="width:100px;"/></td>'+
	'    <td align="left" valign="top"><input class="intxt" id="form_payment_amount" value="'+str_form_payment_amount+'"  style="width:235px;"/><br /><span class="fonti">Amount</span></td>'+
	'  </tr>'+
	'</table>'+
	'    </div>'+
	'    </li>';
	return control_payment;
}
function addPayment(obj, sm) {
	var c=$('.fbc_list').append(controlPayment()).find("li.control:last");
	bindAction(c);
};

function savePayment(obj, sm) {
	var payment_name=obj.find("#payment_name").val();
	var payment_text=obj.find("#payment_text").val();
	var form_payment_amount=obj.find("#form_payment_amount").val();		
	var control='';
	control=controlPayment(payment_name,payment_text,form_payment_amount);
	return control;
}

function makePayment(obj, sm) {
	var payment_name=obj.find("#payment_name").val();
	var payment_text=obj.find("#payment_text").val();
	var form_payment_amount=obj.find("#form_payment_amount").val();	
	
	var control='';
control=control+'<table class="form_control form_payment" border=0 cellpadding=0 cellspacing=0 width="100%">';
control=control+'	<tr>';
control=control+'		<td align=center valign=middle><BR>';
control=control+'	<span id="CA_cityLabel" STYLE="display:none;">City</span><span id="CA_provinceLabel" STYLE="display:none;">Province</span><span id="CA_postalLabel" STYLE="display:none;">Postal Code</span><span id="CA_displayProvince" STYLE="display:none;">True</span><span id="US_cityLabel" STYLE="display:none;">City</span><span id="US_provinceLabel" STYLE="display:none;">State</span><span id="US_postalLabel" STYLE="display:none;">Zip</span><span id="US_displayProvince" STYLE="display:none;">True</span><span id="GB_cityLabel" STYLE="display:none;">Town/City</span><span id="GB_provinceLabel" STYLE="display:none;"></span><span id="GB_postalLabel" STYLE="display:none;">Postcode</span><span id="GB_displayProvince" STYLE="display:none;">False</span><span id="ZW_cityLabel" STYLE="display:none;">City</span><span id="ZW_provinceLabel" STYLE="display:none;">Province/State</span><span id="ZW_postalLabel" STYLE="display:none;">Postal/Zip</span><span id="ZW_displayProvince" STYLE="display:none;">False</span><span id="default_cityLabel" STYLE="display:none;">City</span><span id="default_provinceLabel" STYLE="display:none;">Province/State</span><span id="default_postalLabel" STYLE="display:none;">Postal/Zip</span><span id="default_displayProvince" STYLE="display:none;">False</span>';
control=control+'	<input type="hidden" name="needPayment" value="true">	';
control=control+'	<input type="hidden" name="paymentMethod" value="CC">	';
control=control+'   	<table border=0 cellpadding=0 cellspacing=1>';
control=control+'   		<tr><td colspan="3" height="30px" valign="top"><font face=Arial size=3 color=000066><b>Address Information</b></font></td></tr>';
control=control+'		<tr><td rowspan=9>&nbsp;</td><td><font face=Arial size=2 color=000000>Name:</font></td><td><input type="text" id="ordName" name="ordName" value="" size=30 maxlength=64 autocomplete="off" ></td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Phone Number:</font></td><td><input type="text" id="ordPhoneNumber" name="ordPhoneNumber" value="" size=30 maxlength=32 autocomplete="off" ></td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Address Line 1:</font></td><td><input type="text" id="ordAddress1" name="ordAddress1" value="" size=30 maxlength=64 autocomplete="off" ></td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Address Line 2:</font></td><td><input type="text" id="ordAddress2" name="ordAddress2" value="" size=30 maxlength=64 autocomplete="off" ></td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000><span id="ordCountry_cityLabel">City</span>:</font></td><td><input type="text" id="ordCity" name="ordCity" value="" size=30 maxlength=32 autocomplete="off" ></td></tr>';
control=control+'		<tr id="ordCountry_displayProvince">';
control=control+'   			<td><font face=Arial size=2 color=000000><span id="ordCountry_provinceLabel">Province/State</span>:</font></td>';
control=control+'   			<td>';
control=control+'				<select id="ordProvince" name="ordProvince" id="ordProvince" style="width:203" width="203">';
control=control+'					<option value=""  disabled="disabled">Select your state/province: </option>';
control=control+'					<option value="--" >Outside U.S./Canada</option>';
control=control+'						<optgroup id="ordProvince_country_CA" label="Canada">';
control=control+'						<option value="AB" >Alberta</option>';
control=control+'						<option value="BC" >British Columbia</option>';
control=control+'						<option value="MB" >Manitoba</option>';
control=control+'						<option value="NB" >New Brunswick</option>';
control=control+'						<option value="NL" >Newfoundland and Labrador</option>';
control=control+'						<option value="NT" >Northwest Territories</option>';
control=control+'						<option value="NS" >Nova Scotia</option>';
control=control+'						<option value="NU" >Nunavut</option>';
control=control+'						<option value="ON" >Ontario</option>';
control=control+'						<option value="PE" >Prince Edward Island</option>';
control=control+'						<option value="QC" >Quebec</option>';
control=control+'						<option value="SK"  selected="selected" >Saskatchewan</option>';
control=control+'						<option value="YT" >Yukon</option>';
control=control+'						</optgroup>';
control=control+'						<optgroup id="ordProvince_country_US" label="United States">';
control=control+'						<option value="AL" >Alabama</option>';
control=control+'						<option value="AK" >Alaska</option>';
control=control+'						<option value="AS" >American Samoa</option>';
control=control+'						<option value="AZ" >Arizona</option>';
control=control+'						<option value="AR" >Arkansas</option>';
control=control+'						<option value="AA" >Armed Forces Americas</option>';
control=control+'						<option value="AE" >Armed Forces Europe</option>';
control=control+'						<option value="AP" >Armed Forces Pacific</option>';
control=control+'						<option value="CA" >California</option>';
control=control+'						<option value="CO" >Colorado</option>';
control=control+'						<option value="CT" >Connecticut</option>';
control=control+'						<option value="DE" >Delaware</option>';
control=control+'						<option value="DC" >District of Columbia</option>';
control=control+'						<option value="FL" >Florida</option>';
control=control+'						<option value="GA" >Georgia</option>';
control=control+'						<option value="GU" >Guam</option>';
control=control+'						<option value="HI" >Hawaii</option>';
control=control+'						<option value="ID" >Idaho</option>';
control=control+'						<option value="IL" >Illinois</option>';
control=control+'						<option value="IN" >Indiana</option>';
control=control+'						<option value="IA" >Iowa</option>';
control=control+'						<option value="KS" >Kansas</option>';
control=control+'						<option value="KY" >Kentucky</option>';
control=control+'						<option value="LA" >Louisiana</option>';
control=control+'						<option value="ME" >Maine</option>';
control=control+'						<option value="MD" >Maryland</option>';
control=control+'						<option value="MA" >Massachusetts</option>';
control=control+'						<option value="MI" >Michigan</option>';
control=control+'						<option value="MN" >Minnesota</option>';
control=control+'						<option value="MS" >Mississippi</option>';
control=control+'						<option value="MO" >Missouri</option>';
control=control+'						<option value="MT" >Montana</option>';
control=control+'						<option value="NE" >Nebraska</option>';
control=control+'						<option value="NV" >Nevada</option>';
control=control+'						<option value="NH" >New Hampshire</option>';
control=control+'						<option value="NJ" >New Jersey</option>';
control=control+'						<option value="NM" >New Mexico</option>';
control=control+'						<option value="NY" >New York</option>';
control=control+'						<option value="NC" >North Carolina</option>';
control=control+'						<option value="ND" >North Dakota</option>';
control=control+'						<option value="MP" >Northern Marianas</option>';
control=control+'						<option value="OH" >Ohio</option>';
control=control+'						<option value="OK" >Oklahoma</option>';
control=control+'						<option value="OR" >Oregon</option>';
control=control+'						<option value="PW" >Palau</option>';
control=control+'						<option value="PA" >Pennsylvania</option>';
control=control+'						<option value="PR" >Puerto Rico</option>';
control=control+'						<option value="RI" >Rhode Island</option>';
control=control+'						<option value="SC" >South Carolina</option>';
control=control+'						<option value="SD" >South Dakota</option>';
control=control+'						<option value="TN" >Tennessee</option>';
control=control+'						<option value="TX" >Texas</option>';
control=control+'						<option value="UT" >Utah</option>';
control=control+'						<option value="VT" >Vermont</option>';
control=control+'						<option value="VI" >Virgin Islands</option>';
control=control+'						<option value="VA" >Virginia</option>';
control=control+'						<option value="WA" >Washington</option>';
control=control+'						<option value="WV" >West Virginia</option>';
control=control+'						<option value="WI" >Wisconsin</option>';
control=control+'						<option value="WY" >Wyoming</option>';
control=control+'						</optgroup>';
control=control+'					</select>'; 
control=control+'			</td>';
control=control+'   		</tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000><span id="ordCountry_postalLabel">Postal/Zip Code</span>:</font></td><td><input type="text" id="ordPostalCode" name="ordPostalCode" value="" size=30 maxlength=32 autocomplete="off" > </td></tr>';
control=control+'		<tr>';
control=control+'   			<td><font face=Arial size=2 color=000000>Country:</font></td>';
control=control+'   			<td>';
control=control+'				<select id="ordCountry" name="ordCountry" style="width:203" onChange="CountryUpdate(this, document.getElementById(\'frmPayment\').ordProvince);">   					';
control=control+'					<option value="AF">Afghanistan</option>';
control=control+'					<option value="AX">?land Islands</option>';
control=control+'					<option value="AL">Albania</option>';
control=control+'					<option value="DZ">Algeria</option>';
control=control+'					<option value="AS">American Samoa</option>';
control=control+'					<option value="AD">Andorra</option>';
control=control+'					<option value="AO">Angola</option>';
control=control+'					<option value="AI">Anguilla</option>';
control=control+'					<option value="AQ">Antarctica</option>';
control=control+'					<option value="AG">Antigua and Barbuda</option>';
control=control+'					<option value="AR">Argentina</option>';
control=control+'					<option value="AM">Armenia</option>';
control=control+'					<option value="AW">Aruba</option>';
control=control+'					<option value="AU">Australia</option>';
control=control+'					<option value="AT">Austria</option>';
control=control+'					<option value="AZ">Azerbaijan</option>';
control=control+'					<option value="BS">Bahamas</option>';
control=control+'					<option value="BH">Bahrain</option>';
control=control+'					<option value="BD">Bangladesh</option>';
control=control+'					<option value="BB">Barbados</option>';
control=control+'					<option value="BY">Belarus</option>';
control=control+'					<option value="BE">Belgium</option>';
control=control+'					<option value="BZ">Belize</option>';
control=control+'					<option value="BJ">Benin</option>';
control=control+'					<option value="BM">Bermuda</option>';
control=control+'					<option value="BT">Bhutan</option>';
control=control+'					<option value="BO">Bolivia</option>';
control=control+'					<option value="BA">Bosnia and Herzegovina</option>';
control=control+'					<option value="BW">Botswana</option>';
control=control+'					<option value="BV">Bouvet Island</option>';
control=control+'					<option value="BR">Brazil</option>';
control=control+'					<option value="IO">British Indian Ocean Territory</option>';
control=control+'					<option value="BN">Brunei Darussalam</option>';
control=control+'					<option value="BG">Bulgaria</option>';
control=control+'					<option value="BF">Burkina Faso</option>';
control=control+'					<option value="BI">Burundi</option>';
control=control+'					<option value="KH">Cambodia</option>';
control=control+'					<option value="CM">Cameroon</option>';
control=control+'					<option value="CA"selected>Canada</option>';
control=control+'					<option value="CV">Cape Verde</option>';
control=control+'					<option value="KY">Cayman Islands</option>';
control=control+'					<option value="CF">Central African Republic</option>';
control=control+'					<option value="TD">Chad</option>';
control=control+'					<option value="CL">Chile</option>';
control=control+'					<option value="CN">China</option>';
control=control+'					<option value="CX">Christmas Island</option>';
control=control+'					<option value="CC">Cocos (Keeling) Islands</option>';
control=control+'					<option value="CO">Colombia</option>';
control=control+'					<option value="KM">Comoros</option>';
control=control+'					<option value="CG">Congo</option>';
control=control+'					<option value="CD">Congo, The Democratic Republic of the</option>';
control=control+'					<option value="CK">Cook Islands</option>';
control=control+'					<option value="CR">Costa Rica</option>';
control=control+'					<option value="CI">C?te D鈥檌voire</option>';
control=control+'					<option value="HR">Croatia</option>';
control=control+'					<option value="CU">Cuba</option>';
control=control+'					<option value="CY">Cyprus</option>';
control=control+'					<option value="CZ">Czech Republic</option>';
control=control+'					<option value="DK">Denmark</option>';
control=control+'					<option value="DJ">Djibouti</option>';
control=control+'					<option value="DM">Dominica</option>';
control=control+'					<option value="DO">Dominican Republic</option>';
control=control+'					<option value="EC">Ecuador</option>';
control=control+'					<option value="EG">Egypt</option>';
control=control+'					<option value="SV">El Salvador</option>';
control=control+'					<option value="GQ">Equatorial Guinea</option>';
control=control+'					<option value="ER">Eritrea</option>';
control=control+'					<option value="EE">Estonia</option>';
control=control+'					<option value="ET">Ethiopia</option>';
control=control+'					<option value="FK">Falkland Islands (Malvinas)</option>';
control=control+'					<option value="FO">Faroe Islands</option>';
control=control+'					<option value="FJ">Fiji</option>';
control=control+'					<option value="FI">Finland</option>';
control=control+'					<option value="FR">France</option>';
control=control+'					<option value="GF">French Guiana</option>';
control=control+'					<option value="PF">French Polynesia</option>';
control=control+'					<option value="TF">French Southern Territories</option>';
control=control+'					<option value="GA">Gabon</option>';
control=control+'					<option value="GM">Gambia</option>';
control=control+'					<option value="GE">Georgia</option>';
control=control+'					<option value="DE">Germany</option>';
control=control+'					<option value="GH">Ghana</option>';
control=control+'					<option value="GI">Gibraltar</option>';
control=control+'					<option value="GR">Greece</option>';
control=control+'					<option value="GL">Greenland</option>';
control=control+'					<option value="GD">Grenada</option>';
control=control+'					<option value="GP">Guadeloupe</option>';
control=control+'					<option value="GU">Guam</option>';
control=control+'					<option value="GT">Guatemala</option>';
control=control+'					<option value="GG">Guernsey</option>';
control=control+'					<option value="GN">Guinea</option>';
control=control+'					<option value="GW">Guinea-Bissau</option>';
control=control+'					<option value="GY">Guyana</option>';
control=control+'					<option value="HT">Haiti</option>';
control=control+'					<option value="HM">Heard Island and McDonald Islands</option>';
control=control+'					<option value="VA">Holy See (Vatican City state)</option>';
control=control+'					<option value="HN">Honduras</option>';
control=control+'					<option value="HK">Hong Kong</option>';
control=control+'					<option value="HU">Hungary</option>';
control=control+'					<option value="IS">Iceland</option>';
control=control+'					<option value="IN">India</option>';
control=control+'					<option value="ID">Indonesia</option>';
control=control+'					<option value="IR">Iran, Islamic Republic of</option>';
control=control+'					<option value="IQ">Iraq</option>';
control=control+'					<option value="IE">Ireland</option>';
control=control+'					<option value="IM">Isle Of Man</option>';
control=control+'					<option value="IL">Israel</option>';
control=control+'					<option value="IT">Italy</option>';
control=control+'					<option value="JM">Jamaica</option>';
control=control+'					<option value="JP">Japan</option>';
control=control+'					<option value="JE">Jersey</option>';
control=control+'					<option value="JO">Jordan</option>';
control=control+'					<option value="KZ">Kazakhstan</option>';
control=control+'					<option value="KE">Kenya</option>';
control=control+'					<option value="KI">Kiribati</option>';
control=control+'					<option value="KP">Korea, Democratic People鈥檚 Republic Of</option>';
control=control+'					<option value="KR">Korea, Republic of</option>';
control=control+'					<option value="KW">Kuwait</option>';
control=control+'					<option value="KG">Kyrgyzstan</option>';
control=control+'					<option value="LA">Lao People鈥檚 Democratic Republic</option>';
control=control+'					<option value="LV">Latvia</option>';
control=control+'					<option value="LB">Lebanon</option>';
control=control+'					<option value="LS">Lesotho</option>';
control=control+'					<option value="LR">Liberia</option>';
control=control+'					<option value="LY">Libyan Arab Jamahiriya</option>';
control=control+'					<option value="LI">Liechtenstein</option>';
control=control+'					<option value="LT">Lithuania</option>';
control=control+'					<option value="LU">Luxembourg</option>';
control=control+'					<option value="MO">Macao</option>';
control=control+'					<option value="MK">Macedonia, The Former Yugoslav Republic of</option>';
control=control+'					<option value="MG">Madagascar</option>';
control=control+'					<option value="MW">Malawi</option>';
control=control+'					<option value="MY">Malaysia</option>';
control=control+'					<option value="MV">Maldives</option>';
control=control+'					<option value="ML">Mali</option>';
control=control+'					<option value="MT">Malta</option>';
control=control+'					<option value="MH">Marshall Islands</option>';
control=control+'					<option value="MQ">Martinique</option>';
control=control+'					<option value="MR">Mauritania</option>';
control=control+'					<option value="MU">Mauritius</option>';
control=control+'					<option value="YT">Mayotte</option>';
control=control+'					<option value="MX">Mexico</option>';
control=control+'					<option value="FM">Micronesia, Federated States of</option>';
control=control+'					<option value="MD">Moldova, Republic of</option>';
control=control+'					<option value="MC">Monaco</option>';
control=control+'					<option value="MN">Mongolia</option>';
control=control+'					<option value="ME">Montenegro</option>';
control=control+'					<option value="MS">Montserrat</option>';
control=control+'					<option value="MA">Morocco</option>';
control=control+'					<option value="MZ">Mozambique</option>';
control=control+'					<option value="MM">Myanmar</option>';
control=control+'					<option value="NA">Namibia</option>';
control=control+'					<option value="NR">Nauru</option>';
control=control+'					<option value="NP">Nepal</option>';
control=control+'					<option value="NL">Netherlands</option>';
control=control+'					<option value="AN">Netherlands Antilles</option>';
control=control+'					<option value="NC">New Caledonia</option>';
control=control+'					<option value="NZ">New Zealand</option>';
control=control+'					<option value="NI">Nicaragua</option>';
control=control+'					<option value="NE">Niger</option>';
control=control+'					<option value="NG">Nigeria</option>';
control=control+'					<option value="NU">Niue</option>';
control=control+'					<option value="NF">Norfolk Island</option>';
control=control+'					<option value="MP">Northern Mariana Islands</option>';
control=control+'					<option value="NO">Norway</option>';
control=control+'					<option value="OM">Oman</option>';
control=control+'					<option value="PK">Pakistan</option>';
control=control+'					<option value="PW">Palau</option>';
control=control+'					<option value="PS">Palestinian Territory, Occupied</option>';
control=control+'					<option value="PA">Panama</option>';
control=control+'					<option value="PG">Papua New Guinea</option>';
control=control+'					<option value="PY">Paraguay</option>';
control=control+'					<option value="PE">Peru</option>';
control=control+'					<option value="PH">Philippines</option>';
control=control+'					<option value="PN">Pitcairn</option>';
control=control+'					<option value="PL">Poland</option>';
control=control+'					<option value="PT">Portugal</option>';
control=control+'					<option value="PR">Puerto Rico</option>';
control=control+'					<option value="QA">Qatar</option>';
control=control+'					<option value="RE">Reunion</option>';
control=control+'					<option value="RO">Romania</option>';
control=control+'					<option value="RU">Russian Federation</option>';
control=control+'					<option value="RW">Rwanda</option>';
control=control+'					<option value="BL">Saint Barth茅lemy</option>';
control=control+'					<option value="SH">Saint Helena</option>';
control=control+'					<option value="KN">Saint Kitts and Nevis</option>';
control=control+'					<option value="LC">Saint Lucia</option>';
control=control+'					<option value="MF">Saint Martin</option>';
control=control+'					<option value="PM">Saint Pierre and Miquelon</option>';
control=control+'					<option value="VC">Saint Vincent and the Grenadines</option>';
control=control+'					<option value="WS">Samoa</option>';
control=control+'					<option value="SM">San Marino</option>';
control=control+'					<option value="ST">Sao Tome and Principe</option>';
control=control+'					<option value="SA">Saudi Arabia</option>';
control=control+'					<option value="SN">Senegal</option>';
control=control+'					<option value="RS">Serbia</option>';
control=control+'					<option value="CS">Serbia and Montenegro</option>';
control=control+'					<option value="SC">Seychelles</option>';
control=control+'					<option value="SL">Sierra Leone</option>';
control=control+'					<option value="SG">Singapore</option>';
control=control+'					<option value="SK">Slovakia</option>';
control=control+'					<option value="SI">Slovenia</option>';
control=control+'					<option value="SB">Solomon Islands</option>';
control=control+'					<option value="SO">Somalia</option>';
control=control+'					<option value="ZA">South Africa</option>';
control=control+'					<option value="GS">South Georgia and the South Sandwich Islands</option>';
control=control+'					<option value="ES">Spain</option>';
control=control+'					<option value="LK">Sri Lanka</option>';
control=control+'					<option value="SD">Sudan</option>';
control=control+'					<option value="SR">Suriname</option>';
control=control+'					<option value="SJ">Svalbard and Jan Mayen</option>';
control=control+'					<option value="SZ">Swaziland</option>';
control=control+'					<option value="SE">Sweden</option>';
control=control+'					<option value="CH">Switzerland</option>';
control=control+'					<option value="SY">Syrian Arab Republic</option>';
control=control+'					<option value="TW">Taiwan, Province of China</option>';
control=control+'					<option value="TJ">Tajikistan</option>';
control=control+'					<option value="TZ">Tanzania, United Republic of</option>';
control=control+'					<option value="TH">Thailand</option>';
control=control+'					<option value="TL">Timor-Leste</option>';
control=control+'					<option value="TG">Togo</option>';
control=control+'					<option value="TK">Tokelau</option>';
control=control+'					<option value="TO">Tonga</option>';
control=control+'					<option value="TT">Trinidad and Tobago</option>';
control=control+'					<option value="TN">Tunisia</option>';
control=control+'					<option value="TR">Turkey</option>';
control=control+'					<option value="TM">Turkmenistan</option>';
control=control+'					<option value="TC">Turks and Caicos Islands</option>';
control=control+'					<option value="TV">Tuvalu</option>';
control=control+'					<option value="UG">Uganda</option>';
control=control+'					<option value="UA">Ukraine</option>';
control=control+'					<option value="AE">United Arab Emirates</option>';
control=control+'					<option value="GB">United Kingdom</option>';
control=control+'					<option value="US">United States</option>';
control=control+'					<option value="UM">United States Minor Outlying Islands</option>';
control=control+'					<option value="UY">Uruguay</option>';
control=control+'					<option value="UZ">Uzbekistan</option>';
control=control+'					<option value="VU">Vanuatu</option>';
control=control+'					<option value="VE">Venezuela</option>';
control=control+'					<option value="VN">Viet Nam</option>';
control=control+'					<option value="VG">Virgin Islands, British</option>';
control=control+'					<option value="VI">Virgin Islands, U.S.</option>';
control=control+'					<option value="WF">Wallis and Futuna</option>';
control=control+'					<option value="EH">Western Sahara</option>';
control=control+'					<option value="YE">Yemen</option>';
control=control+'					<option value="ZM">Zambia</option>';
control=control+'					<option value="ZW">Zimbabwe</option>';
control=control+'				</select>';
control=control+'   			</td>';
control=control+'   		</tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Email:</font></td><td><input type="text" id="ordEmailAddress" name="ordEmailAddress" value="" size=30 maxlength=64 autocomplete="off" ></td>   		</tr>';
control=control+'		<tr><td colspan=3 height=0><input type="hidden" name="shipName" value=""><input type="hidden" name="shipEmailAddress" value=""><input type="hidden" name="shipPhoneNumber" value=""><input type="hidden" name="shipAddress1" value=""><input type="hidden" name="shipAddress2" value=""><input type="hidden" name="shipCity" value=""><input type="hidden" name="shipProvince" value=""><input type="hidden" name="shipPostalCode" value=""><input type="hidden" name="shipCountry" value=""></td></tr><tr><td colspan=3><br></td></tr>';
control=control+'   		<tr><td colspan="3" height="45px" valign="top"><div style="float:left;"><font face=Arial size=3 color=000066><b>Payment Information</b></font></div></td></tr>';
control=control+'   		<tr><td rowspan=7>&nbsp;</td><td><font face=Arial size=2 color=000000>Invoice/Order Number:</font></td><td><input type="text" id="trnOrderNumber" name="trnOrderNumber" value="" size=30 maxlength=30 autocomplete="off" ></td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Amount (CAD):</font></td><td><input type="text" id="trnAmount" name="trnAmount" value="'+form_payment_amount+'" readonly size=30 maxlength=32 onBlur="if (PositiveInteger(this) == true) {FormatAmount(this);}" autocomplete="off" ></td></tr>';
control=control+'   		';
control=control+'   		';
control=control+'		';
control=control+'   		<tr><td><font face=Arial size=2 color=000000>Name on card:</font></td><td><input type="text" id="trnCardOwner" name="trnCardOwner" value="" size=30 maxlength=64 autocomplete="off" ></td></tr>	';
control=control+'   		<tr><td><font face=Arial size=2 color=000000>Credit Card Type:</font></td><td><select id="trnCardType" name="trnCardType" onChange="CheckPaymentType()"><option value="VI">VISA<option value="MC">MasterCard</select></td></tr>';
control=control+'   		<tr><td><font face=Arial size=2 color=000000>Credit Card Number:</font></td><td><input type="text" id="trnCardNumber" name="trnCardNumber" value="" size=30 maxlength=20 onchange="ValidateCardNumber();" autocomplete="off" ></td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Expiration Date:</font></td>';
control=control+'   			<td>';
control=control+'   				<select id="trnExpMonth" name="trnExpMonth">';
control=control+'   					<option value="01">01';
control=control+'					<option value="02">02';
control=control+'					<option value="03">03';
control=control+'					<option value="04">04';
control=control+'					<option value="05">05';
control=control+'					<option value="06">06';
control=control+'					<option value="07">07';
control=control+'					<option value="08">08';
control=control+'					<option value="09">09';
control=control+'					<option value="10">10';
control=control+'					<option value="11">11';
control=control+'					<option value="12">12';
control=control+'   				</select>';
control=control+'   				/';
control=control+'   				<select id="trnExpYear" name="trnExpYear">';
control=control+'					<option value="13">2013';
control=control+'					<option value="14">2014';
control=control+'					<option value="15">2015';
control=control+'					<option value="16">2016';
control=control+'					<option value="17">2017';
control=control+'					<option value="18">2018';
control=control+'					<option value="19">2019';
control=control+'					<option value="20">2020';
control=control+'					<option value="21">2021';
control=control+'					<option value="22">2022';
control=control+'					<option value="23">2023';
control=control+'					<option value="24">2024';
control=control+'					<option value="25">2025';
control=control+'   				</select>';
control=control+'   			</td>';
control=control+'   		</tr>';
control=control+'		<tr><td colspan=3><br></td></tr>';
control=control+'		<tr><td colspan="3" height="30px" valign="top"><font face=Arial size=3 color=000066><b>Comments</b></font></td></tr>';
control=control+'		<tr><td colspan=3><textarea id="trnComments" name="trnComments" rows=4 cols=40></textarea></td></tr>';
control=control+'	</table>';
control=control+'	';
control=control+'	<br><table border=0><tr><td></td></tr></table>';

control=control+'	<P>';
control=control+'	<script language="JavaScript" type="text/javascript">addLoadEvent(CountryUpdatePayment);</script>';
control=control+'		</td>';
control=control+'	</tr>';
control=control+'</table>';
	return control;
}