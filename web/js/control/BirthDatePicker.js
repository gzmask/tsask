function controlBirthDatePickerItemMonth(){
	var control_item_month="";
//	var monthArray=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
	var monthArray=new Array("01","02","03","04","05","06","07","08","09","10","11","12");
	for (i in monthArray){
		control_item_month+=
		'    <li><a href="#">'+monthArray[i]+'</a></li>';			
	}
	return control_item_month
}

function controlBirthDatePickerItemDay(){
	var control_item_day="";
	for(i=1;i<=31;i++){
		if(i<10){
	control_item_day+=
	'    <li><a href="#">0'+i+'</a></li>';	
		}else{
	control_item_day+=
	'    <li><a href="#">'+i+'</a></li>';			
		}
	}
	return control_item_day
}

function controlBirthDatePickerItemYear(){
	var control_item_year="";
	year=new Date().getFullYear();
	for(i=year-5;i<=year+10;i++){	
	control_item_year+=
	'    <li><a href="#">'+i+'</a></li>';	
	}
	return control_item_year
}

function controlBirthDatePicker(str_birth_date_picker_name,str_birth_date_picker_text) {
	if (typeof str_birth_date_picker_name == 'undefined')str_birth_date_picker_name='';
	if (typeof str_birth_date_picker_text == 'undefined')str_birth_date_picker_text='Birth Date:';	
	var control_birth_date_picker='    <li class="control control_birth_date_picker">'+
	'    <div class="fbc_bar">'+
	'    <div class="bar_tit">Birth Date Picker</div>'+
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
	'      	<input class="intxt" id="birth_date_picker_name" value="'+str_birth_date_picker_name+'" style="width:235px;" />'+
	'    	</div>'+	
	'    </div>'+	
	
	'    <div class="fbc_txt">'+
	'    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">'+	
	'  <tr>'+
	'    <td width="98" valign="top"><input id="birth_date_picker_text" class="intxt" value="'+str_birth_date_picker_text+'" style="width:80px;"/></td>'+
	'    <td width="222" align="left" valign="top">'+
	'    <div class="hoptions op_month">'+
	'     <input name="searchdomain" type="hidden" value="">'+
	'     <input id="monthSearchType" name="" type="hidden" value="">'+
	'     <div class="selSearch">'+
	'    <div class="nowSearch" id="monthSelected" ></div>'+
	'    <div class="btnSel" id="monthSelButton"><a href="#" ></a></div>'+
	'    <div class="clear"></div>'+
	'    <ul class="selOption" id="monthSel" style="display:none;">'+
//controlBirthDatePickerItemMonth()
controlBirthDatePickerItemMonth()+
	'    </ul>'+
	'     </div>'+
	'   </div>'+
	'    <br />'+
	'<span class="fonti">Month</span></td>'+
	'    <td width="136" align="left" valign="middle">'+
	'    <div class="hoptions op_day">'+
	'     <input name="searchdomain" type="hidden" value="">'+
	'     <input id="daySearchType" name="searchType" type="hidden" value="">'+
	'     <div class="selSearch">'+
	'    <div class="nowSearch" id="daySelected"></div>'+
	'    <div class="btnSel" id="daySelButton"><a href="#"></a></div>'+
	'    <div class="clear"></div>'+
	'    <ul class="selOption" id="daySel" style="display:none;">'+
//controlBirthDatePickerItemDay()	
controlBirthDatePickerItemDay()+
	'    </ul>'+
	'     </div>'+
	'   </div>'+
	'   <br />'+
	'<span class="fonti">Day</span></td>'+
	'    <td align="left" valign="top">'+
	'    <div class="hoptions op_year">'+
	'     <input name="searchdomain" type="hidden" value="">'+
	'     <input id="yearSearchType" name="searchType" type="hidden" value="">'+
	'     <div class="selSearch">'+
	'    <div class="nowSearch" id="yearSelected" ></div>'+
	'    <div class="btnSel" id="yearSelButton"><a href="#"></a></div>'+
	'    <div class="clear"></div>'+
	'    <ul class="selOption" id="yearSel" style="display:none;">'+
//controlBirthDatePickerItemYear()	
controlBirthDatePickerItemYear()+	
	'    </ul>'+
	'     </div>'+
	'   </div>'+
	'    <br />'+
	'<span class="fonti">Year</span></td>'+
	'  </tr>'+
	'</table>'+
	'    </div>'+
	'    </li>';
	return control_birth_date_picker;
}

function bindActionBirthDatePickerList(obj){
	var c=obj;	
	bindActionSelectList(c,"#monthSelected","#monthSelButton","#monthSel");
	bindActionSelectList(c,"#daySelected","#daySelButton","#daySel");
	bindActionSelectList(c,"#yearSelected","#yearSelButton","#yearSel");
	bindActionSelectedItem(c,"#monthSel li","#monthSearchType","#monthSel","#monthSelected");
	bindActionSelectedItem(c,"#daySel li","#daySearchType","#daySel","#daySelected");
	bindActionSelectedItem(c,"#yearSel li","#yearSearchType","#yearSel","#yearSelected");		
}

function rebindActionBirthDatePicker(obj){
	bindActionBirthDatePickerList(obj);	
}

function addBirthDatePicker(obj, sm) {
	var c=$('.fbc_list').append(controlBirthDatePicker()).find("li.control:last");
	bindAction(c);
	bindActionBirthDatePickerList(c);
};

function saveBirthDatePicker(obj, sm) {
	var birth_date_picker_name=obj.find("#birth_date_picker_name").val();	
	var title_text=obj.find("#birth_date_picker_text").val();
	var control='';
	control=controlBirthDatePicker(birth_date_picker_name,title_text);		
	return control;
}

function makeBirthDatePicker(obj, sm) {
	var birth_date_picker_name=obj.find("#birth_date_picker_name").val();
	var title_text=obj.find("#birth_date_picker_text").val();
	if('Click to edit this text...'==title_text){
		title_text='';
	}
	var control='';
	control=control+'<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab form_control form_birth_date_picker">';
	control=control+'  <tr>';
	control=control+'    <th width="80" valign="top" id="birth_date_picker_label" >'+title_text+'</th>';
	control=control+'    <td width="222" align="left" valign="top">';
	control=control+'    <div class="hoptions op_month">';
	control=control+'     <input name="searchdomain" type="hidden" value="">';
	control=control+'     <input id="monthSearchType" name="'+birth_date_picker_name+'_month" type="hidden" value="">';
	control=control+'     <div class="selSearch">';
	control=control+'    <div class="nowSearch" id="monthSelected" ></div>';
	control=control+'    <div class="btnSel" id="monthSelButton"><a href="#" ></a></div>';
	control=control+'    <div class="clear"></div>';
	control=control+'    <ul class="selOption" id="monthSel" style="display:none;">';
	control=control+controlBirthDatePickerItemMonth();
	control=control+'    </ul>';
	control=control+'     </div>';
	control=control+'   </div>';
	control=control+'    <br />';
	control=control+'<span class="fonti">Month</span></td>';
	control=control+'    <td width="132" align="left" valign="middle">';
	control=control+'    <div class="hoptions op_day">';
	control=control+'     <input name="searchdomain" type="hidden" value="">';
	control=control+'     <input id="daySearchType" name="'+birth_date_picker_name+'_day" type="hidden" value="">';
	control=control+'     <div class="selSearch">';
	control=control+'    <div class="nowSearch" id="daySelected" ></div>';
	control=control+'    <div class="btnSel" id="daySelButton" ><a href="#" ></a></div>';
	control=control+'    <div class="clear"></div>';
	control=control+'    <ul class="selOption" id="daySel" style="display:none;">';
	control=control+controlBirthDatePickerItemDay();
	control=control+'    </ul>';
	control=control+'     </div>';
	control=control+'   </div>';
	control=control+'   <br />';
	control=control+'<span class="fonti">Day</span></td>';
	control=control+'    <td align="left" valign="top">';
	control=control+'    <div class="hoptions op_year">';
	control=control+'     <input name="searchdomain" type="hidden" value="">';
	control=control+'     <input id="yearSearchType" name="'+birth_date_picker_name+'_year" type="hidden" value="">';
	control=control+'     <div class="selSearch">';
	control=control+'    <div class="nowSearch" id="yearSelected" ></div>';
	control=control+'    <div class="btnSel" id="yearSelButton" ><a href="#" ></a></div>';
	control=control+'    <div class="clear"></div>';
	control=control+'    <ul class="selOption" id="yearSel" style="display:none;">';
	control=control+controlBirthDatePickerItemYear();
	control=control+'    </ul>';
	control=control+'     </div>';
	control=control+'   </div>';
	control=control+'    <br />';
	control=control+'<span class="fonti">Year</span></td>';
	control=control+'  </tr>';
	control=control+'</table>';		
	return control;
}