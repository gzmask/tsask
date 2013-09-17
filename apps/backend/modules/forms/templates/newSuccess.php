<?php use_helper('I18N', 'Date') ?>
<form id="form_design" action="<?php echo url_for('forms/save') ?>" method="POST">
<dl class="txtcont">
<dt>
<div class="ltit"><strong>Forms Builder</strong></div>
<div class="rbtns"><a href="#!" title="save" class="bsave" onclick="saveForm()">SAVE</a><a href="<?php echo url_for('@sa_forms')?>" title="cancle" class="bcancle">CANCLE</a></div>
<div class="clear"></div>
</dt>
<dd>
<div class="forms_cont">
<div class="fc_tit">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fct_tab">
  <tr>
    <th width="190">Form Tools</th>
    <th>Form Design Area</th>
    </tr>
</table>
</div>
<div class="fc_con formbuil_box">
    <div class="fbnav">
    <ul class="fbn_list">
    <li onclick="javascript:addHeading();"><img src="/images/blank.gif" class="icon_heading" />Heading</li>
    <li onclick="javascript:addTextBox();"><img src="/images/blank.gif" class="icon_textb" />Text Box</li>
    <li onclick="javascript:addTextArea();"><img src="/images/blank.gif" class="icon_texta" />Text Area</li>
    <li onclick="javascript:addDropDown();"><img src="/images/blank.gif" class="icon_dropd" />Drop Down</li>
    <li onclick="javascript:addRadioButton();"><img src="/images/blank.gif" class="icon_radio" />Radio Button</li>
    <li onclick="javascript:addCheckBox();"><img src="/images/blank.gif" class="icon_check" />Check Box</li>
    <li onclick="javascript:addTOS();"><img src="/images/blank.gif" class="icon_check" />TOS</li>
    <li onclick="javascript:addFileUpload();"><img src="/images/blank.gif" class="icon_file" />File Upload</li>
    <li onclick="javascript:addSubmitButton();"><img src="/images/blank.gif" class="icon_submit" />Submit Button</li>
    <li onclick="javascript:addResetButton();"><img src="/images/blank.gif" class="icon_reset" />Reset Button</li>
    <li onclick="javascript:addFullName();"><img src="/images/blank.gif" class="icon_fullname" />Full Name</li>
    <li onclick="javascript:addClientEmail();"><img src="/images/blank.gif" class="icon_email" />Client E-mail</li>	
    <li onclick="javascript:addEmail();"><img src="/images/blank.gif" class="icon_email" />E-mail</li>
    <li onclick="javascript:addAddress();"><img src="/images/blank.gif" class="icon_addr" />Address</li>
    <li onclick="javascript:addPhone();"><img src="/images/blank.gif" class="icon_phone" />Phone</li>
    <li onclick="javascript:addBirthDatePicker();"><img src="/images/blank.gif" class="icon_bdp" />Birth Date Picker</li>
    <li onclick="javascript:addNumber();"><img src="/images/blank.gif" class="icon_numb" />Number</li>
    <li onclick="javascript:addDateTime();"><img src="/images/blank.gif" class="icon_datet" />DateTime</li>
    <li onclick="javascript:addUniqueId();"><img src="/images/blank.gif" class="icon_uniid" />Unique Id</li>
    <li onclick="javascript:addPayment();"><img src="/images/blank.gif" class="icon_pay" />Payment</li>
    </ul>
    </div>
    <div class="fb_cont">
    <ul class="fbc_head">
        <li class="form_name">
            <div class="fbc_bar">
                <div class="bar_tit">Form Name</div>
            </div>
            <div class="fbc_txt">
                <input class="intxt" id="form_name" name="form_name" value="" onfocus="if(value ==\'Click to edit this text...\'){value =\'\'}" onblur="if (value ==\'\'){value=\'Click to edit this text...\'}"/>
            </div>
        </li>
    </ul>
    <ul class="fbc_list">

    </ul>
    </div>
    <div class="clear"></div>
</div>
</div>
</dd>
</dl>
<input type="hidden" value="" id="form_content" name="form_content" />
<input type="hidden" value="" id="form_published" name="form_published" />
</form>
