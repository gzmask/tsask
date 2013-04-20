<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<?php include_http_metas() ?>
<?php include_metas() ?>
<?php include_title() ?>
<?php include_stylesheets() ?>
<?php include_javascripts() ?>	
</head>
<body>
<div class="wrapper">
<?php include_partial('global/header')?>  
	<div class="container">
<?php include_partial('global/mainNav',array('currentTab'=>$currentTab))?> 	
		<div class="mainbox">
<?php include_partial('global/subNav')?> 
<?php echo $sf_content ?>
		</div>
	</div>
	<div class="footer"></div>
</div>
</body>
</html>