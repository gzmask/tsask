function controlTOSItem(str_check_box_item_value,str_check_box_item_label_text){
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
	if (typeof str_tos_name == 'undefined')str_tos_name='';
	if (typeof str_tos_text == 'undefined')str_tos_text='';
	if (typeof str_tos_items == 'undefined')str_tos_items='';
	
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
	'    <div class="fbct_half">'+
	//'      <input class="intxt inhalf" id="check_box_text" value="'+str_check_box_text+'" onfocus="if(value ==\'Click to edit this text...\'){value =\'\'}" onblur="if (value ==\'\'){value=\'Click to edit this text...\'}"/>'+	
	'      <input class="intxt inhalf" id="tos_text" value="'+str_tos_text+'" />'+
	'      </div>'+
	'    <div class="oplist">'+
	'    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_check_box" >'+
	'  <tr>'+
	'  <td>&nbsp;</td>'+
	'  <td><img src="/images/icon_imgjia.jpg" alt="" title="add" class="plus_check_box" /></td>'+
	'  </tr>'+
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
		bindActionTOSMinus(c.find(".table_tos .item_tos:last"));
	})	
}

function rebindActionTOS(obj){	
	var c=obj;
	bindActionTOSItemPlus(c);
	c.find(".item_tos").each(function(){
		bindActionTOSMinus($(this));
	});
}

function addTOS(obj, sm) {
	var c=$('.fbc_list').append(controlTOS()).find("li.control:last");
	bindActionTOSPlus(c);	
	bindAction(c);
};

function saveTOS(obj, sm) {//保存
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

function makeTOS(obj, sm) {//生成前端
	var check_box_name=obj.find("#check_box_name").val();	
	var check_box_text=obj.find("#check_box_text").val();
	var control='';
	control=control+'<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab form_control form_check_box">';
	control=control+'  <tr>';
	if(''!=check_box_text){
	control=control+'    <td width="70" id="form_check_box_text" style="Vertical-align:Top;">'+check_box_text+'</td>';	
	}
	control=control+'    <td style="Vertical-align:Top;">';		
	control=control+'      <table width="100%" border="0" cellspacing="0" cellpadding="0">';
	obj.find("#item_check_box_label").each(function(){
		control=control+'<tr><td id="form_check_box_item" ><input id="form_check_box_value" name="" type="checkbox" value="" /><label id="form_check_box_label">';
		control=control+$(this).val()+'</label></td></tr>';
	});
	control=control+'      </table>';
	control=control+'    </td>';			
	control=control+'  </tr>';
	control=control+'</table>';
	return control;
}
