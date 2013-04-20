[?php use_helper('I18N', 'Date') ?]
[?php include_partial('<?php echo $this->getModuleName() ?>/assets') ?]
[?php include_partial('<?php echo $this->getModuleName() ?>/flashes') ?]
<dl class="txtcont">
  [?php echo form_tag_for($form, '@<?php echo $this->params['route_prefix'] ?>') ?]
<dt>
<div class="ltit"><strong>[?php echo <?php echo $this->getI18NString('edit.title') ?> ?]</strong></div>
<div class="rbtns">
    [?php include_partial('<?php echo $this->getModuleName() ?>/form_actions', array('<?php echo $this->getSingularName() ?>' => $<?php echo $this->getSingularName() ?>, 'form' => $form, 'configuration' => $configuration, 'helper' => $helper)) ?]</div>
<div class="clear"></div>
</dt>
<dd>
<div class="forms_cont">

<div class="fc_tit">
    [?php include_partial('<?php echo $this->getModuleName() ?>/form_header', array('<?php echo $this->getSingularName() ?>' => $<?php echo $this->getSingularName() ?>, 'form' => $form, 'configuration' => $configuration)) ?]
</div>

<div class="fc_con userbuil_box">
    [?php include_partial('<?php echo $this->getModuleName() ?>/form', array('<?php echo $this->getSingularName() ?>' => $<?php echo $this->getSingularName() ?>, 'form' => $form, 'configuration' => $configuration, 'helper' => $helper)) ?]
<!--<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ub_tab">
  <tr>
    <th width="25%">
      <input name="" type="text" class="intxt" value="" /><br />
<span class="fonti">Username</span>
    </th>
    <td width="25%"><input name="input" type="text" class="intxt" value="" />
      <br />
     <span class="fonti"> Email</span></td>
    <td width="25%"><input name="input2" type="text" class="intxt" value="" />
      <br />
      <span class="fonti">First Name</span></td>
    <td>
      <input name="input3" type="text" class="intxt" value="" />
      <br />
      <span class="fonti">Last Name</span></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input name="input4" type="text" class="intxt" value="" />
      <br />
      <span class="fonti">Password</span></td>
    <td><span class="fonti fontc"><img src="../images/icon_ts.jpg" />Password must be at least 8 characters</span></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input name="input5" type="text" class="intxt" value="" />
      <br />
      <span class="fonti">Confirm Password</span></td>
    <td>&nbsp;</td>
  </tr>
</table>-->
</div>

</div>
</dd>
  </form>
</dl>
