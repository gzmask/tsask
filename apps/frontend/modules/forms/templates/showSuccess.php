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
    <div class="icon_logo">
		<a href="#" title="technical safety authority interface design">
			<img src="/images/icon_logo.jpg" alt="technical safety authority interface design"/>
		</a>
	</div>
    <div class="icon_exit"></div>
  </div>
  
<form id="form_user" action="<?php echo url_for('forms/commit') ?>" method="POST" onsubmit="return validateForm()">
<div class="container">
<div class="mainnav"></div>
<div class="mainbox">
	<dl class="txtcont requtxt">
	<!--main title-->
		<dt>
		<div class="ltit"><strong><?php echo $sf_data->getRaw('form_name');?></strong></div>
		<div class="clear"></div>
		</dt>
	<!--main content-->
		<dd>
		<?php echo $sf_data->getRaw('form_published');?>
		</dd>
	</dl>
</div>
</div>
<input type="hidden" value="" id="order_content" name="order_content" />
<input type="hidden" value="<?php echo $sf_data->getRaw('form_name');?>" id="form_name" name="form_name" />
</form>
<div class="footer"></div>

</div>
</body>
</html>
<script src="/js/jquery-1.7.2.js" language="javascript"></script>
<script src="/js/layout.js" language="javascript"></script>
<script src="/js/form_commit.js" language="javascript"></script>
