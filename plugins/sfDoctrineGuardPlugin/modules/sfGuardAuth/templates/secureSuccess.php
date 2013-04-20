<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="/backend/css/login.css" type="text/css" />
<style type="text/css">
<!--
.error_list {color: #FF0000}
-->
</style>
<!--[if lt IE 7]>
<script language="javascript" src="/js/iepng.js"></script>
    <script type="text/javascript">
       EvPNG.fix('div, ul, img, li, input');  //EvPNG.fix('包含透明PNG图片的标签'); 多个标签之间用英文逗号隔开。
    </script>
<![endif]-->  
<title></title>
</head>
<body>
<div class="r_logo l"><img src="/images/logo_png.png" /></div>    
<?php //echo get_component('sfGuardAuth', 'signin_form') ?>
<div class="hint_content">
<?php use_helper('I18N') ?>
<h2><?php echo __('你所访问的页面没有获得正确的授权.', null, 'sf_guard') ?></h2>
<h3><?php echo __('请重新登录获取访问授权', null, 'sf_guard') ?></h3>
</div>
</body>
</html>