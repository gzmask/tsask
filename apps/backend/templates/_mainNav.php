<div class="mainnav">
	<ul class="navlist">
	<li><a href="<?php echo url_for('@sa_forms')?>" title="form" class="nav1<?php include_slot('tab_forms') ?>"><span>Form</span></a></li>
	<li><a href="<?php echo url_for('@sa_orders')?>" title="orders" class="nav2<?php include_slot('tab_orders') ?>"><span>orders</span></a></li>
	<li><a href="<?php echo url_for('@users_list')?>" title="users" class="nav3<?php include_slot('tab_users') ?>"><span>users</span></a></li>
        <li><a class="nav4" href="http://www.tsaskforms.ca:3000/csv/payment-report" title="payment report"><span>Payment Report</span></a></li>
	</ul>
	<div class="clear"></div>
</div>
