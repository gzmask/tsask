<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>technical safety authority interface design_login</title>
<link rel="stylesheet" type="text/css" href="/css/login.css"/>
</head>
<body>
<div class="loginbox">
<div class="icon_logo">
<a href="#" title="technical safety authority interface design">
<img src="/images/icon_logo.jpg" alt="technical safety authority interface design"/>
</a></div>
<div class="logincont">
<?php use_helper('I18N') ?>
<form action="<?php echo url_for('@sf_guard_signin') ?>" method="post">
<div class="lc_box">
<?php echo $form['username']->render(array('class' => 'intxt','value'=>'Username')) ?>
<?php echo $form['password']->render(array('class' => 'intxt','value'=>'******')) ?>
<?php //echo $form->render(array('username' => array('class' => 'intxt','value' => 'Username',))) ?>
<!--<input name="" type="text" class="intxt" value="Username" id="username" />
<input class="intxt" id="passwordText" value="******" type="text">-->
<?php echo $form->renderHiddenFields() ?>
<input name="" type="submit" class="inbtnlogin" value="" />
<div class="clear"></div></div>
</form>
<?php echo $form->renderGlobalErrors() ?>
<?php echo $form['username']->renderError() ?>
<?php echo $form['password']->renderError() ?>
</div>
</div>
</body>
</html>
<script language="javascript" src="/js/login.js"></script>




