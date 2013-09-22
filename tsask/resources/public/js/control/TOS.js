function controlTOSItem(str_tos_item_value,str_tos_item_label_text){
	if (typeof str_tos_item_value == 'undefined')str_tos_item_value='';
	if (typeof str_tos_item_label_text == 'undefined')str_tos_item_label_text='';
	
	var item_tos=	
	'  <tr class="item_tos">'+
	'    <td>'+
	'        <label>'+
	'          <input type="checkbox" name="RadioGroup1" value="'+str_tos_item_value+'" id="RadioGroup1_0" />'+
	'          <input class="intxt inhalf" value="'+str_tos_item_label_text+'" id="item_tos_label"/></label>'+
	'   </td>'+
	'    <td><img src="/images/icon_imgjian.jpg" alt="" title="delete" class="minus_tos"/></td>'+
	'  </tr>';
	return item_tos;
}

function controlTOS(str_tos_name,str_tos_text,str_tos_items) {
        var tos_default = "Template Terms of Service\n"+
          "This is a collection of forms prepared by tsaskforms's developers designed to be used as a template for your applications, privacy policy and copyright policy. They are important legal documents and we recommend you get a qualified legal opinion before applying them to your own services and apps. We will occasionally update these documents to keep them up-to-date. To facilitate transparent discussion, we encourage users to create issues and/or submit pull requests with your feedback. Our general process is to incorporate user feedback on a roughly quarterly basis based on review with our legal team.\n"+
          "License\n"+
          "The tsaskforms terms of use and associated documents are available to you under the CC BY-SA 3.0 Creative Commons license. You acknowledge and agree that we neither make any representations or warranties with respect to the documents nor assume any liability with respect to the documents (including their use). These documents were created for general informational purposes only and do not constitute advertising, a solicitation or legal advice. Neither the availability, operation, transmission, receipt nor use of the documents is for the purpose of requesting legal advice, securing legal services, or retaining a lawyer nor is intended to create, or constitutes the formation of any attorney-client relationship or other special relationship or privilege.\n"+
          "Contact\n"+
          "If you have feedback which you would like to make available privately, please email support@tsaskforms.ca.\n";

	if (typeof str_tos_name == 'undefined')
          str_tos_name='Terms of Services';
	if (typeof str_tos_text == 'undefined')
          str_tos_text=tos_default;
	if (typeof str_tos_items == 'undefined')
          str_tos_items='';
	
	var control_tos='<li class="control control_tos">'+
	'    <div class="fbc_bar">'+
	'    <div class="bar_tit">Terms of Services</div>'+
	'    <div class="bar_btns"><span>'+
	'    <a href="#!" title="ARROW DOWN" class="btn_down" ></a>'+
	'    <a href="#!" title="ARROW UP" class="btn_up"></a>'+
	'    <a href="#!" title="ARROW BOTTOM" class="btn_bottom"></a>'+
	'    <a href="#!" title="ARROW TOP" class="btn_top"></a>'+
	'    <a href="#!" title="DELETE" class="btn_del"></a>'+
	'    </span></div>'+
	'    </div>'+
	'    <div class="fbc_txt">'+
	'    	<div class="fbct_98">control name:</div>'+
	'    	<div class="fbct_half">'+
	'      	<input class="intxt" id="tos_name" value="'+str_tos_name+'" style="width:235px;" />'+
	'    	</div>'+	
	'    </div>'+		
	'    <div class="fbc_txt">'+
	'      <textarea class="intxt inhalf" id="tos_text">'+str_tos_text+'</textarea>'+
	'      </div>'+
	'    <div class="fbc_txt">'+
	'    <div class="oplist">'+
	'    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_tos" >'+
	'  <tr>'+
	'  <td>&nbsp;</td>'+
	'  </tr>'+
	'<input type="radio" name="tos_response" value="yes">Accep'+
        '&nbsp;'+
	'<input type="radio" name="tos_response" value="no">Decline'+
	str_tos_items	+
	'</table>'+
	'    </div>'+
	'    </div>'+
	'    </li>';
	return control_tos;
}

function bindActionTOSItemMinus(item){
	var c=item;
	c.find(".minus_tos").click(
		function(){
			c.remove();
		}
	);
}

function bindActionTOSItemPlus(obj){
	var c=obj;
	c.find(".plus_tos").click(function(){
		c.find(".table_tos").append(controlTOSItem());
		bindActionTOSItemMinus(c.find(".table_tos .item_tos:last"));
	})	
}

function rebindActionTOS(obj){	
	var c=obj;
	bindActionTOSItemPlus(c);
	c.find(".item_tos").each(function(){
		bindActionTOSItemMinus($(this));
	});
}

function addTOS(obj, sm) {

	var c=$('.fbc_list').append(controlTOS()).find("li.control:last");
	bindActionTOSItemPlus(c);	
	bindAction(c);
};

function saveTOS(obj, sm) {
	var tos_name=obj.find("#tos_name").val();
	var tos_text=obj.find("#tos_text").val();
	var str_tos_items='';
	obj.find('.table_tos .item_tos').each(function(){
		str_tos_item_label=$(this).find("#item_tos_label").val();
		str_tos_items=str_tos_items+controlTOSItem('',str_tos_item_label);
	});	
	var control='';
	control=controlTOS(tos_name,tos_text,str_tos_items);
	return control;
}

function makeTOS(obj, sm) {
	var tos_name=obj.find("#tos_name").val();	
	var tos_text=obj.find("#tos_text").val();
	var control='';
	control=control+'<div>';
	if(''!=tos_text){
          control=control+'    <textarea id="form_tos_text" style="min-height: 150px; width: 30%; Vertical-align:Top;" class="fulln_tab form_control form_tos">'+tos_text+'</textarea></br>';	
        }
	control=control+'<input type="radio" name="tos_response" value="yes">Accept';
        control=control+'&nbsp;';
	control=control+'<input type="radio" name="tos_response" value="no">Decline';
	control=control+'</div>';
        /*
	control=control+'<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab form_control form_tos">';
	control=control+'  <tr>';
	if(''!=tos_text){
	control=control+'    <td width="70" id="form_tos_text" style="Vertical-align:Top;">'+tos_text+'</td>';	
	}
	control=control+'    <td style="Vertical-align:Top;">';		
	control=control+'      <table width="100%" border="0" cellspacing="0" cellpadding="0">';
	obj.find("#item_tos_label").each(function(){
		control=control+'<tr><td id="form_tos_item" ><input id="form_tos_value" name="" type="checkbox" value="" /><label id="form_tos_label">';
		control=control+$(this).val()+'</label></td></tr>';
	});
	control=control+'      </table>';
	control=control+'    </td>';			
	control=control+'  </tr>';
	control=control+'</table>';
        */
	return control;
}
