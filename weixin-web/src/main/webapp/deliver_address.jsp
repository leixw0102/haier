<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="ui-page-login">
	<head>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>
		<link href="css/mui.min.css" rel="stylesheet" />
		
	    <!--App自定义的css-->
		<!--<link rel="stylesheet" type="text/css" href="css/app.css" />-->

	    <link rel="stylesheet" type="text/css" href="css/mui.picker.min.css" />
	    <link href="css/iconfont.css" rel="stylesheet"/>
	    <link rel="stylesheet" type="text/css" href="css/main.css"/>
	  	<link rel="stylesheet" type="text/css" href="css/deliveraddress.css"/>
	    
	    <script src="js/mui.min.js"></script>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/views/deliveraddress.js"></script>
		
    <style> 
		.mui-control-content {
			background-color: white;
			min-height: 500px;
		}
	
	</style>
		

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left title-back">返回</a>
	    	<h1 class="mui-title">地址档案</h1>
	    	<a id="morelink" class="mui-icon mui-icon-more icon-mark mui-pull-right"></a>  
	    </header> 
	    <nav class="mui-bar mui-bar-tab">
			<div style="width:80%;margin:0 auto">    
		    	<button id="add" onclick="addDeliverAddress()" type="button" class="mui-btn mui-btn-primary" style="width:100%;background-color: #32beff;border:-20">新增收货地址</button>
		   </div>
		</nav>
		<div class="mui-content">
			
			<div id="slider" class="mui-slider">
				<div class="deliver-address">
					<div class="deliver-address-item" id="address_1">
						<div class="col-man clearfix">
							<div class="col-name">王新亮</div>
							<div class="col-phone">13729802271</div>
						</div>
						<p>北京市海淀区中关村路南大街23号院花园小区14号楼8单元602室</p>
						<div class="deliver-address-opt clearfix">
							<div class="mui-input-row1 mui-radio mui-left fleft">
								<input name="address_radio" id="address_radio1" type="radio" checked><label>默认地址</label>
							</div>
							<div class="fright">
								<a name="icon-edit" class="edit" href="deliver_address_edit.jsp">编辑</a>
								<a name="address-del" class="del" href="javascript:;" attr_id="1">删除</a>
							</div>
						</div>
					</div>
					
					<div class="deliver-address-item" id="address_2">
						<div class="col-man clearfix">
							<div class="col-name">王新亮</div>
							<div class="col-phone">13729802271</div>
						</div>
						<p>北京市海淀区中关村路南大街23号院花园小区14号楼8单元602室</p>
						<div class="deliver-address-opt clearfix">
							<div class="mui-input-row1 mui-radio mui-left fleft">
								<input name="address_radio" id="address_radio2" type="radio" ><label>设为默认</label>
								
							</div>
							<div class="fright">
								<a name="icon-edit" class="edit" href="deliver_address_edit.jsp">编辑</a>
								<a name="address-del" class="del" href="javascript:;" attr_id="2">删除</a>
							</div>
						</div>
					</div>
					
					<div class="deliver-address-item" id="address_3">
						<div class="col-man clearfix">
							<div class="col-name">赵艳霞</div>
							<div class="col-phone">13729802271</div>
						</div>
						<p>北京市海淀区中关村路南大街23号院花园小区14号楼8单元602室</p>
						<div class="deliver-address-opt clearfix">
							<div class="mui-input-row1 mui-radio mui-left fleft">
								<input name="address_radio" id="address_radio3" type="radio"><label>设为默认</label>
								
							</div>
							<div class="fright">
								<a name="icon-edit" class="edit" href="deliver_address_edit.jsp">编辑</a>
								<a name="address-del" class="del" href="javascript:;" attr_id="3">删除</a>
							</div>
						</div>
					</div>
					
					<div class="deliver-address-item" id="address_4">
						<div class="col-man clearfix">
							<div class="col-name">李丽梅</div>
							<div class="col-phone">13729802271</div>
						</div>
						<p>北京市海淀区中关村路南大街23号院花园小区14号楼8单元602室</p>
						<div class="deliver-address-opt clearfix">
							<div class="mui-input-row1 mui-radio mui-left fleft">
								<input name="address_radio" id="address_radio4" type="radio"><label>设为默认</label>
								
							</div>
							<div class="fright">
								<a name="icon-edit" class="edit" href="deliver_address_edit.jsp">编辑</a>
								<a name="address-del" class="del" href="javascript:;" attr_id="4">删除</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
			(function($, doc) {
				$.init({
					
				});
				
			}(mui, document));
		</script>
	</body>

</html>