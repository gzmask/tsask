<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>technical safety authority interface design</title>
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<!--[if lte IE 6]>
<script src="/js/DD_belatedPNG.js" mce_src="/js/DD_belatedPNG.js"></script>
<script type="text/javascript">     /* EXAMPLE */   DD_belatedPNG.fix('div,h3,li,h1,a,h6,input,h4,span,img');   /* 此处添加应用透明PNG的CSS选择器即可*/   </script>
<![endif]-->

</head>
<body>
<div class="wrapper">
  <div class="header">
    <div class="icon_logo"><a href="#" title="technical safety authority interface design"><img src="/images/icon_logo.jpg" alt="technical safety authority interface design"/></a></div>
    <div class="icon_exit"><a href="#!" title="logOut">Log Out</a></div>
  </div>
  <div class="container">
  <div class="mainnav"></div>
<div class="mainbox">
<dl class="txtcont requtxt">
<dt>
<div class="ltit"><strong>Request for Examination Remark</strong></div>
<div class="clear"></div>
</dt>
<dd>
<div class="requ_desc">
This form must be filled in completely and returned to the Authority with payment. Failure to do so may result in a delay in the processing of your application. Please refer to TSK-0003 Client Authorization Payment form for payment options. You can download all forms at<a href="#!" class="fontc"> www.tsask.ca</a>. Please fax or email completed application with payment. </div>
<div class="requ_formtxt">
<div class="requf_tit">I. Personal Information</div>
<div class="requf_cont">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">
  <tr>
  <th width="80" valign="top">Name:</th>
    <td width="251" align="left" valign="top"><input class="intxt" value="" style="width:235px;"/>
      <br />
      <span class="fonti">First Name</span></td>
    <td align="left" valign="top"><input class="intxt" value="" style="width:238px;"/>
     <br />
      <span class="fonti">Last Name</span></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">
  <tr>
    <th width="80" rowspan="4" valign="top">Address:</th>
    <td colspan="2" align="left" valign="top"><input class="intxt" value="" style="width:500px;" /><br />
<span class="fonti">Street Address</span></td>
  </tr>
  <tr>
    <td colspan="2" align="left" valign="top"><input class="intxt" value="" style="width:500px;" /><br />
  <span class="fonti">Street Address Line2</span></td>
  </tr>
  <tr>
    <td width="251" align="left" valign="top"><input class="intxt" value="" style="width:235px;" /><br />
  <span class="fonti">City</span></td>
    <td align="left" valign="top"><input class="intxt" value="" style="width:238px;" />
      <br />
<span class="fonti">State/Province</span></td>
  </tr>
  <tr>
    <td width="235" align="left" valign="top"><input class="intxt" value="" style="width:235px;" /><br />
  <span class="fonti">Postal/Zip Code</span></td>
    <td align="left" valign="top"><div class="countrys">
     <input name="searchdomain" type="hidden" value="">
     <input id="countrySearchType" name="searchType" type="hidden" value="playlist">
     <div class="selSearch">
    <div class="nowSearch" id="countrySlected" onclick="if(document.getElementById('countrySel').style.display=='none'){document.getElementById('countrySel').style.display='block';}else {document.getElementById('countrySel').style.display='none';};return false;" onmouseout="drop_mouseout('country');"></div>
    <div class="btnSel"><a href="#" onclick="if(document.getElementById('countrySel').style.display=='none'){document.getElementById('countrySel').style.display='block';}else {document.getElementById('countrySel').style.display='none';};return false;" onmouseout="drop_mouseout('country');"></a></div>
    <div class="clear"></div>
    <ul class="selOption" id="countrySel" style="display:none;">
    <li><a href="#" onclick="return search_show('country','cn',this)" onmouseover="drop_mouseover('country');" onmouseout="drop_mouseout('country');">Contryname</a></li>
    </ul>
     </div>
   </div><br />
<span class="fonti">Country</span></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">
  <tr>
    <th width="98" valign="top">Home Phone:</th>
    <td width="136" align="left" valign="top"><input class="intxt" value="" style="width:120px;"/><br />
<span class="fonti">Area Code</span></td>
    <td width="10" align="center" valign="middle"><span class="fontc2">-</span><br />
<span class="fonti">&nbsp;</span></td>
    <td align="left" valign="top"><input class="intxt" value="" style="width:160px;"/><br />
<span class="fonti">Phone Number</span></td>
  </tr>
  <tr>
    <th width="98" valign="top">Work Phone:</th>
    <td width="136" align="left" valign="top"><input class="intxt" value="" style="width:120px;"/><br />
<span class="fonti">Area Code</span></td>
    <td width="10" align="center" valign="middle"><span class="fontc2">-</span><br />
<span class="fonti">&nbsp;</span></td>
    <td align="left" valign="top"><input class="intxt" value="" style="width:160px;"/><br />
<span class="fonti">Phone Number</span></td>
  </tr>
  <tr>
    <th width="98" valign="top">Fax:</th>
    <td width="136" align="left" valign="top"><input class="intxt" value="" style="width:120px;"/><br />
<span class="fonti">Area Code</span></td>
    <td width="10" align="center" valign="middle"><span class="fontc2">-</span><br />
<span class="fonti">&nbsp;</span></td>
    <td align="left" valign="top"><input class="intxt" value="" style="width:160px;"/><br />
<span class="fonti">Phone Number</span></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">
  <tr>
    <th width="80" valign="top">Email:</th>
    <td colspan="2" align="left" valign="top"><input class="intxt" value="" style="width:500px;"/> </td>
  </tr>
  </table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">
  <tr>
    <th width="80" valign="top">Birthday:</th>
    <td width="222" align="left" valign="top">
    <div class="hoptions op_month">
     <input name="searchdomain" type="hidden" value="">
     <input id="monthSearchType" name="searchType" type="hidden" value="playlist">
     <div class="selSearch">
    <div class="nowSearch" id="monthSlected" onclick="if(document.getElementById('monthSel').style.display=='none'){document.getElementById('monthSel').style.display='block';}else {document.getElementById('monthSel').style.display='none';};return false;" onmouseout="drop_mouseout('month');"></div>
    <div class="btnSel"><a href="#" onclick="if(document.getElementById('monthSel').style.display=='none'){document.getElementById('monthSel').style.display='block';}else {document.getElementById('monthSel').style.display='none';};return false;" onmouseout="drop_mouseout('month');"></a></div>
    <div class="clear"></div>
    <ul class="selOption" id="monthSel" style="display:none;">
    <li><a href="#" onclick="return search_show('month','cn',this)" onmouseover="drop_mouseover('month');" onmouseout="drop_mouseout('month');">Monthname</a></li>
    </ul>
     </div>
   </div>
    <br />
<span class="fonti">Month</span></td>
    <td width="132" align="left" valign="middle">
    <div class="hoptions op_day">
     <input name="searchdomain" type="hidden" value="">
     <input id="daySearchType" name="searchType" type="hidden" value="playlist">
     <div class="selSearch">
    <div class="nowSearch" id="daySlected" onclick="if(document.getElementById('daySel').style.display=='none'){document.getElementById('daySel').style.display='block';}else {document.getElementById('daySel').style.display='none';};return false;" onmouseout="drop_mouseout('day');"></div>
    <div class="btnSel"><a href="#" onclick="if(document.getElementById('daySel').style.display=='none'){document.getElementById('daySel').style.display='block';}else {document.getElementById('daySel').style.display='none';};return false;" onmouseout="drop_mouseout('day');"></a></div>
    <div class="clear"></div>
    <ul class="selOption" id="daySel" style="display:none;">
    <li><a href="#" onclick="return search_show('day','cn',this)" onmouseover="drop_mouseover('day');" onmouseout="drop_mouseout('day');">Dayname</a></li>
    </ul>
     </div>
   </div>
   <br />
<span class="fonti">Day</span></td>
    <td align="left" valign="top">
    <div class="hoptions op_year">
     <input name="searchdomain" type="hidden" value="">
     <input id="yearSearchType" name="searchType" type="hidden" value="playlist">
     <div class="selSearch">
    <div class="nowSearch" id="yearSlected" onclick="if(document.getElementById('yearSel').style.display=='none'){document.getElementById('yearSel').style.display='block';}else {document.getElementById('yearSel').style.display='none';};return false;" onmouseout="drop_mouseout('year');"></div>
    <div class="btnSel"><a href="#" onclick="if(document.getElementById('yearSel').style.display=='none'){document.getElementById('yearSel').style.display='block';}else {document.getElementById('yearSel').style.display='none';};return false;" onmouseout="drop_mouseout('year');"></a></div>
    <div class="clear"></div>
    <ul class="selOption" id="yearSel" style="display:none;">
    <li><a href="#" onclick="return search_show('year','cn',this)" onmouseover="drop_mouseover('year');" onmouseout="drop_mouseout('year');">Yearname</a></li>
    </ul>
     </div>
   </div>
    <br />
<span class="fonti">Year</span></td>
  </tr>
</table>
<div class="re_tig">To the best of my knowledge, this application and all supporting documents are accurate. I understand that a false or misleading statement in this application or in any of the reference or other evidence or qualification submitted by myself or on my behalf may result in the Chielf Inspector denying this application.</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th width="70">Consent:</th>
    <td><label><input name="" type="checkbox" value="" />&nbsp;&nbsp;by checking this box, I hereby agree to the terms stated above.</label></td>
  </tr>
</table>
</div>
<div class="requf_tit">II. Examination Information</div>
<div class="requf_cont">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">
  <tr>
    <th width="14%">Reference Number:</th>
    <td width="86%"><input class="intxt" value="" style="width:500px;"/></td>
  </tr>
  <tr>
    <th>Class:</th>
    <td><input class="intxt" value="" style="width:500px;"/></td>
  </tr>
  <tr>
    <th>Part:</th>
    <td><input class="intxt" value="" style="width:500px;"/></td>
  </tr>
  <tr>
    <th>Paper:</th>
    <td><input class="intxt" value="" style="width:500px;"/></td>
  </tr>
</table>
</div>
<div class="requf_tit">
III. Remark Schedule of Fees</div>
<div class="requf_cont">
<ul class="for3list">
<li><label><input name="" type="checkbox" value="" />&nbsp;&nbsp;</label>
  First and Second Class Examination Remark Fee $150</li>
<li><label><input name="" type="checkbox" value="" />&nbsp;&nbsp;</label>
  Third, Fourth, Fifth and Refrigeration Engineering Examination Remark Fe $75</li>
<li><label><input name="" type="checkbox" value="" />&nbsp;</label>
  Limited Power Engineering Examination Remark Fee $37.50(Oilfield, traction, commercial, fireman, and refrigeration operators)</li>
</ul>
</div>
<div class="requf_tit">IV. Payment Options</div>
<div class="requf_cont">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">
  <tr>
    <th colspan="4">Total Cost Due (+Tax):<span class="c">$999.99</span></th>
    </tr>
  <tr>
    <th width="120">Card Type:</th>
    <td colspan="3"><div class="countrys">
     <input name="searchdomain" type="hidden" value="">
     <input id="cardtpSearchType" name="searchType" type="hidden" value="playlist">
     <div class="selSearch">
    <div class="nowSearch" id="cardtpSlected" onclick="if(document.getElementById('cardtpSel').style.display=='none'){document.getElementById('cardtpSel').style.display='block';}else {document.getElementById('cardtpSel').style.display='none';};return false;" onmouseout="drop_mouseout('cardtp');"></div>
    <div class="btnSel"><a href="#" onclick="if(document.getElementById('cardtpSel').style.display=='none'){document.getElementById('cardtpSel').style.display='block';}else {document.getElementById('cardtpSel').style.display='none';};return false;" onmouseout="drop_mouseout('cardtp');"></a></div>
    <div class="clear"></div>
    <ul class="selOption" id="cardtpSel" style="display:none;">
    <li><a href="#" onclick="return search_show('cardtp','cn',this)" onmouseover="drop_mouseover('cardtp');" onmouseout="drop_mouseout('cardtp');">Contryname</a></li>
    </ul>
     </div>
   </div></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">
  <tr>
    <th width="120">Card Number:</th>
    <td colspan="3"><input class="intxt" value="" style="width:500px;"/></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">
  <tr>
    <th width="120">Expiration Date:</th>
    <td width="222"><div class="hoptions op_month">
     <input name="searchdomain" type="hidden" value="">
     <input id="monthSearchType" name="searchType" type="hidden" value="playlist">
     <div class="selSearch">
    <div class="nowSearch" id="monthSlected" onclick="if(document.getElementById('monthSel').style.display=='none'){document.getElementById('monthSel').style.display='block';}else {document.getElementById('monthSel').style.display='none';};return false;" onmouseout="drop_mouseout('month');"></div>
    <div class="btnSel"><a href="#" onclick="if(document.getElementById('monthSel').style.display=='none'){document.getElementById('monthSel').style.display='block';}else {document.getElementById('monthSel').style.display='none';};return false;" onmouseout="drop_mouseout('month');"></a></div>
    <div class="clear"></div>
    <ul class="selOption" id="monthSel" style="display:none;">
    <li><a href="#" onclick="return search_show('month','cn',this)" onmouseover="drop_mouseover('month');" onmouseout="drop_mouseout('month');">Monthname</a></li>
    </ul>
     </div>
   </div><br />
<span class="fonti">Month</span></td>
    <td width="135"><div class="hoptions op_edcvc">
     <input name="searchdomain" type="hidden" value="">
     <input id="ed_edyearSearchType" name="searchType" type="hidden" value="playlist">
     <div class="selSearch">
    <div class="nowSearch" id="edyearSlected" onclick="if(document.getElementById('edyearSel').style.display=='none'){document.getElementById('edyearSel').style.display='block';}else {document.getElementById('edyearSel').style.display='none';};return false;" onmouseout="drop_mouseout('edyear');"></div>
    <div class="btnSel"><a href="#" onclick="if(document.getElementById('edyearSel').style.display=='none'){document.getElementById('edyearSel').style.display='block';}else {document.getElementById('edyearSel').style.display='none';};return false;" onmouseout="drop_mouseout('edyear');"></a></div>
    <div class="clear"></div>
    <ul class="selOption" id="edyearSel" style="display:none;">
    <li><a href="#" onclick="return search_show('edyear','cn',this)" onmouseover="drop_mouseover('edyear');" onmouseout="drop_mouseout('edyear');">edyearname</a></li>
    </ul>
     </div>
   </div><br />
<span class="fonti">Year</span></td>
    <td><div class="hoptions op_edcvc">
     <input name="searchdomain" type="hidden" value="">
     <input id="edcvcSearchType" name="searchType" type="hidden" value="playlist">
     <div class="selSearch">
    <div class="nowSearch" id="edcvcSlected" onclick="if(document.getElementById('edcvcSel').style.display=='none'){document.getElementById('edcvcSel').style.display='block';}else {document.getElementById('edcvcSel').style.display='none';};return false;" onmouseout="drop_mouseout('edcvc');"></div>
    <div class="btnSel"><a href="#" onclick="if(document.getElementById('edcvcSel').style.display=='none'){document.getElementById('edcvcSel').style.display='block';}else {document.getElementById('edcvcSel').style.display='none';};return false;" onmouseout="drop_mouseout('edcvc');"></a></div>
    <div class="clear"></div>
    <ul class="selOption" id="edcvcSel" style="display:none;">
    <li><a href="#" onclick="return search_show('edcvc','cn',this)" onmouseover="drop_mouseover('edcvc');" onmouseout="drop_mouseout('edcvc');">Yearname</a></li>
    </ul>
     </div>
   </div><br />
   <span class="fonti">CVC</span></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="fulln_tab">
   <tr>
    <th width="120">Name on Card:</th>
    <td width="210"><input class="intxt" value="" style="width:200px;"/><br /><span class="fonti">First</span></td>
    <td width="90"><input class="intxt" value="" style="width:80px;"/><br /><span class="fonti">Middle</span></td>
    <td><input class="intxt" value="" style="width:176px;"/><br /><span class="fonti">Last</span></td>
  </tr>
</table>
<div class="re_tig">Note: After receipt of this request it may take up to 2 weeks for the exam to be remarked. Once complete, a letter detailing the results will be sent to the address listed above.</div>
</div>
<div class="resubmit">
<a href="#!" title="submit"><img src="/images/btn_submit.jpg" /></a>
</div>
</div>
</dd>
</dl>
</div>
</div>
<div class="footer"></div>
</div>
</body>
</html>
<script src="/js/jquery-1.7.2.js" language="javascript"></script>
<script src="/js/layout.js" language="javascript"></script>