function validateFormPayment(obj){
    var phone_regex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
    if ($('#ordName').val().length < 1) {
         alert("You need to enter your name in the payment form.");
         return false;
    } else if (!$('#ordPhoneNumber').val().match(phone_regex) ) {
         alert("Please enter an valid phone number.");
         return false;
    } else if ($('#ordAddress1').val().length < 1) {
         alert("Please enter an valid Billing address.");
         return false;
    } else if ($('#ordCity').val().length < 1) {
         alert("Please enter your city.");
         return false;
    } else if ($('#ordPostalCode').val().length < 1) {
         alert("Please enter your postal code.");
         return false;
    } else if ($('#ordEmailAddress').val().length < 1) {
         alert("Please enter your email address.");
         return false;
    } else if ($('#trnCardOwner').val().length < 1) {
         alert("Please enter your Name on card.");
         return false;
    } else if ($('#trnCardNumber').val().length < 1) {
         alert("Please enter your Credit card number.");
         return false;
    } else {
         return true;
    }
}

function formPayment(obj, sm) {
	var ordName=obj.find("#ordName").val();
	var ordPhoneNumber=obj.find("#ordPhoneNumber").val();	
	var ordAddress1=obj.find("#ordAddress1").val();
	var ordAddress2=obj.find("#ordAddress2").val();	
	var ordCity=obj.find("#ordCity").val();		
	var ordProvince=obj.find("#ordProvince option:selected").text();
	var ordPostalCode=obj.find("#ordPostalCode").val();	
	var ordCountry=obj.find("#ordCountry option:selected").text();	
	var ordEmailAddress=obj.find("#ordEmailAddress").val();

	var trnOrderNumber=obj.find("#trnOrderNumber").val();	
	var trnAmount=obj.find("#trnAmount").val();	
	var trnCardType=obj.find("#trnCardType option:selected").text();	
	var trnCardOwner=obj.find("#trnCardOwner").val();	
	var trnCardNumber=obj.find("#trnCardNumber").val();	
	var trnExpMonth=obj.find("#trnExpMonth option:selected").text();	
	var trnExpYear=obj.find("#trnExpYear option:selected").text();	
	var trnComments=obj.find("#trnComments").val();	
	
	var control='';
control=control+'<table class="form_control form_payment" border=0 cellpadding=0 cellspacing=0 width="100%">';
control=control+'	<tr>';
control=control+'		<td align=center valign=middle><BR>';
control=control+'	<span id="CA_cityLabel" STYLE="display:none;">City</span><span id="CA_provinceLabel" STYLE="display:none;">Province</span><span id="CA_postalLabel" STYLE="display:none;">Postal Code</span><span id="CA_displayProvince" STYLE="display:none;">True</span><span id="US_cityLabel" STYLE="display:none;">City</span><span id="US_provinceLabel" STYLE="display:none;">State</span><span id="US_postalLabel" STYLE="display:none;">Zip</span><span id="US_displayProvince" STYLE="display:none;">True</span><span id="GB_cityLabel" STYLE="display:none;">Town/City</span><span id="GB_provinceLabel" STYLE="display:none;"></span><span id="GB_postalLabel" STYLE="display:none;">Postcode</span><span id="GB_displayProvince" STYLE="display:none;">False</span><span id="ZW_cityLabel" STYLE="display:none;">City</span><span id="ZW_provinceLabel" STYLE="display:none;">Province/State</span><span id="ZW_postalLabel" STYLE="display:none;">Postal/Zip</span><span id="ZW_displayProvince" STYLE="display:none;">False</span><span id="default_cityLabel" STYLE="display:none;">City</span><span id="default_provinceLabel" STYLE="display:none;">Province/State</span><span id="default_postalLabel" STYLE="display:none;">Postal/Zip</span><span id="default_displayProvince" STYLE="display:none;">False</span>';

control=control+'	<input type="hidden" name="paymentMethod" value="CC">	';
control=control+'   	<table border=0 cellpadding=0 cellspacing=1>';
control=control+'   		<tr><td colspan="3" height="30px" valign="top"><font face=Arial size=3 color=000066><b>Address Information</b></font></td></tr>';
control=control+'		<tr><td rowspan=9>&nbsp;</td><td><font face=Arial size=2 color=000000>Name:</font></td><td>'+ordName+'</td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Phone Number:</font></td><td>'+ordPhoneNumber+'</td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Address Line 1:</font></td><td>'+ordAddress1+'</td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Address Line 2:</font></td><td>'+ordAddress2+'</td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000><span id="ordCountry_cityLabel">City</span>:</font></td><td>'+ordCity+'</td></tr>';
control=control+'		<tr id="ordCountry_displayProvince">';
control=control+'   			<td><font face=Arial size=2 color=000000><span id="ordCountry_provinceLabel">Province/State</span>:</font></td>';
control=control+'   			<td>'+ordProvince;

control=control+'			</td>';
control=control+'   		</tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000><span id="ordCountry_postalLabel">Postal/Zip Code</span>:</font></td><td>'+ordPostalCode+'</td></tr>';
control=control+'		<tr>';
control=control+'   			<td><font face=Arial size=2 color=000000>Country:</font></td>';
control=control+'   			<td>'+ordCountry;
control=control+'   			</td>';
control=control+'   		</tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Email:</font></td><td>'+ordEmailAddress+'</td>   		</tr>';
control=control+'		<tr><td colspan=3 height=0><input type="hidden" name="shipName" value=""><input type="hidden" name="shipEmailAddress" value=""><input type="hidden" name="shipPhoneNumber" value=""><input type="hidden" name="shipAddress1" value=""><input type="hidden" name="shipAddress2" value=""><input type="hidden" name="shipCity" value=""><input type="hidden" name="shipProvince" value=""><input type="hidden" name="shipPostalCode" value=""><input type="hidden" name="shipCountry" value=""></td></tr><tr><td colspan=3><br></td></tr>';
control=control+'   		<tr><td colspan="3" height="45px" valign="top"><div style="float:left;"><font face=Arial size=3 color=000066><b>Payment Information</b></font></div></td></tr>';
control=control+'   		<tr><td rowspan=7>&nbsp;</td><td><font face=Arial size=2 color=000000>Invoice/Order Number:</font></td><td>'+trnOrderNumber+'</td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Amount (CAD):</font></td><td>'+trnAmount+'</td></tr>';
control=control+'   		';
control=control+'   		';
control=control+'		';
control=control+'   		<tr><td><font face=Arial size=2 color=000000>Name on card:</font></td><td>'+trnCardOwner+'</td></tr>	';
control=control+'   		<tr><td><font face=Arial size=2 color=000000>Credit Card Type:</font></td><td>'+trnCardType+'</td></tr>';
control=control+'   		<tr><td><font face=Arial size=2 color=000000>Credit Card Number:</font></td><td>'+trnCardNumber+'</td></tr>';
control=control+'		<tr><td><font face=Arial size=2 color=000000>Expiration Date:</font></td>';
control=control+'   			<td>'+trnExpMonth;
control=control+'   				/'+trnExpYear;
control=control+'   			</td>';
control=control+'   		</tr>';
control=control+'		<tr><td colspan=3><br></td></tr>';
control=control+'		<tr><td colspan="3" height="30px" valign="top"><font face=Arial size=3 color=000066><b>Comments</b></font></td></tr>';
control=control+'		<tr><td colspan=3>'+trnComments+'</td></tr>';
control=control+'	</table>';
control=control+'	';
control=control+'	<br><table border=0><tr><td></td></tr></table>';
control=control+'		</td>';
control=control+'	</tr>';
control=control+'</table>';
	return control;
}
