[?php use_helper('I18N', 'Date') ?]
[?php include_partial('<?php echo $this->getModuleName() ?>/assets') ?]
  <div id="sf_admin_header">
    [?php include_partial('<?php echo $this->getModuleName() ?>/list_header', array('pager' => $pager)) ?]
  </div>
<dl class="txtcont">
<dt>
  <div class="ltit"><strong>[?php echo <?php echo $this->getI18NString('list.title') ?> ?]</strong></div>

  [?php include_partial('<?php echo $this->getModuleName() ?>/flashes') ?]



<?php if ($this->configuration->hasFilterForm()): ?>
  <div id="sf_admin_bar">
    [?php include_partial('<?php echo $this->getModuleName() ?>/filters', array('form' => $filters, 'configuration' => $configuration)) ?]
  </div>
<?php endif; ?>
<!--
<div class="sortby">
<div class="sb_tit">Sort By:</div>
<div class="chose">
<form method="get" action="" name="headSearchForm" id="headSearchForm">
     <input name="searchdomain" type="hidden" value="">
     <input id="headSearchType" name="searchType" type="hidden" value="playlist">
     <div class="selSearch">
    <div class="nowSearch" id="headSlected" onclick="if(document.getElementById('headSel').style.display=='none'){document.getElementById('headSel').style.display='block';}else {document.getElementById('headSel').style.display='none';};return false;" onmouseout="drop_mouseout('head');">Newest First</div>
    <div class="btnSel"><a href="#" onclick="if(document.getElementById('headSel').style.display=='none'){document.getElementById('headSel').style.display='block';}else {document.getElementById('headSel').style.display='none';};return false;" onmouseout="drop_mouseout('head');"></a></div>
    <div class="clear"></div>
    <ul class="selOption" id="headSel" style="display:none;">
    <li><a href="#" onclick="return search_show('head','nf',this)" onmouseover="drop_mouseover('head');" onmouseout="drop_mouseout('head');">Newest First</a></li>
     <li><a href="#" onclick="return search_show('head','sv',this)" onmouseover="drop_mouseover('head');" onmouseout="drop_mouseout('head');">sales volume</a></li>
     <li><a href="#" onclick="return search_show('head','cl',this)" onmouseover="drop_mouseover('head');" onmouseout="drop_mouseout('head');">collect
</a></li>
    </ul>
     </div>
     </form>
	 <script>
function drop_mouseover(pos){
 try{window.clearTimeout(timer);}catch(e){}
}
function drop_mouseout(pos){
 var posSel=document.getElementById(pos+"Sel").style.display;
 if(posSel=="block"){
  timer = setTimeout("drop_hide('"+pos+"')", 1000);
 }
}
function drop_hide(pos){
 document.getElementById(pos+"Sel").style.display="none";
}
function search_show(pos,searchType,href){
    document.getElementById(pos+"SearchType").value=searchType;
    document.getElementById(pos+"Sel").style.display="none";
    document.getElementById(pos+"Slected").innerHTML=href.innerHTML;
 try{window.clearTimeout(timer);}catch(e){}
 return false;
}
</script>
   </div>
</div>-->
<div class="clear"></div>
</dt>



<?php if ($this->configuration->getValue('list.batch_actions')): ?>
    <form action="[?php echo url_for('<?php echo $this->getUrlForAction('collection') ?>', array('action' => 'batch')) ?]" method="post">
<?php endif; ?>
    [?php include_partial('<?php echo $this->getModuleName() ?>/list', array('pager' => $pager, 'sort' => $sort, 'helper' => $helper)) ?]
<!--    <ul class="sf_admin_actions">
      [?php include_partial('<?php echo $this->getModuleName() ?>/list_batch_actions', array('helper' => $helper)) ?]
      [?php include_partial('<?php echo $this->getModuleName() ?>/list_actions', array('helper' => $helper)) ?]
    </ul>
-->	
<?php if ($this->configuration->getValue('list.batch_actions')): ?>
    </form>
<?php endif; ?>
  

</dl>  

 <div id="sf_admin_footer">
    [?php include_partial('<?php echo $this->getModuleName() ?>/list_footer', array('pager' => $pager)) ?]
  </div>