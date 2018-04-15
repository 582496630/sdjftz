<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>角色列表</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="statics/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="statics/dist/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="statics/dist/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="statics/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="statics/dist/css/skins/all-skins.min.css">
<!-- table style -->
<link rel="stylesheet"
	href="statics/plugins/bootstrap-table/bootstrap-editable.css">
<link rel="stylesheet"
	href="statics/plugins/bootstrap-table/bootstrap-table.min.css">
<!-- bootstrap-select style -->
<link rel="stylesheet"
	href="statics/plugins/bootstrap-select/bootstrap-select.css">
<!-- layer style -->
<link rel="stylesheet"
	href="statics/plugins/layer/skin/default/layer.css">
<link rel="stylesheet" href="statics/plugins/zTree/zTreeStyle.css">
<style type="text/css">
/* html {
	overflow: hidden;
} */
.ztree * {
	font-size: 16px;
}
</style>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="statics/plugins/ie9/html5shiv.min.js"></script>
    <script src="statics/plugins/ie9/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container" style="width: 90%;">
		<div class="col-lg-4 col-md-4 col-sm-4">
			<div class="box box-default">
				<div class="box-header with-border">
					<h3 class="box-title">角色权限分配</h3>
				</div>
				<div class="box-body">
					<div class="chart-responsive">
						<div style="font-size: 18px;">
							<ul id="treeDemo" class="ztree">
							</ul>
						</div>
					</div>
				</div>
				<div class="box-tools">
					<div class="form-group">
						<label class="col-md-4 control-label">角色名:</label>
						<div class="col-md-8">
							<input style="width: 160px; height: 28px;" id="show_rolename"
								 type="text" maxlength="25" disabled="disabled"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label">角色描述:</label>
						<div class="col-md-8">
							<input style="width: 160px; height: 28px;" id="show_dec"
								 type="text" maxlength="250" disabled="disabled"/>
						</div>
					</div>
				</div>
				<div class="box-footer pull-right">
					<input type="button" class="btn btn-info" value="保存" style="width: 60px;" onclick="updAuth();"/>
				</div>
			</div>
		</div>
		<div class="col-lg-8 col-md-8 col-sm-8">
			<div id="interval_toolbar">
				<form class="form-inline" role="form"
					style="float: left; width: 100%" method="post" id="cjrw_queryForm"
					autocomplete="off">
					<input id="userId" type="text" value="${sessionScope.userId}"
						hidden="hidden">
					<div class="form-group">
						<button type="button" onclick="addRoleInfo()" class="btn btn-info">
							<span class="glyphicon glyphicon-plus"></span>&nbsp;添加角色
						</button>
					</div>
				</form>
			</div>

			<table id="userTable">
			</table>
			<br>
		</div>
		<!-- 点击新增、修改按钮时的弹框 -->
		<div style="display: none;" id="updRoleInfo" align="center">
			<form class="form-horizontal"
				style="margin-top: 45px; width: 300px; height: 150px;">
				<div class="form-group">
					<label class="col-lg-4 control-label"><span
						style="color: red;">*</span>角色名:</label>
					<div class="col-lg-8">
						<input style="width: 195px; height: 35px;" id="update_rolename"
							name="name" type="text" maxlength="25" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-4 control-label"><span
						style="color: red;">*</span>角色描述:</label>
					<div class="col-lg-8">
						<input style="width: 195px; height: 35px;" id="update_dec"
							name="description" type="text" maxlength="250" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-4 control-label"><span
						style="color: red;">*</span>角色权限描述描述:</label>
					<div class="col-lg-8">
						<input style="width: 195px; height: 35px;" id="update_authodec"
							name="description" type="text" maxlength="250" />
					</div>
				</div>
			</form>
		</div>

		<!-- <div style="display: none;" id="grant_auth" align="center">
			<div style="font-size: 18px;">
				<ul id="treeDemo" class="ztree">
				</ul>
			</div>
		</div> -->
		<!--修改角色弹框  -->
	</div>
	<div style="display: none;" id="roleHandleInfo" align="center">
		<div id="roleHandle_toolbar">
			<button type="button" id="addUserInfo" onclick="addRole()"
				class="btn btn-success">
				<span class="glyphicon glyphicon-plus"></span>&nbsp;新增角色
			</button>
		</div>
		<table id="roleHandle-detail-table">
		</table>
	</div>
	<!-- ./wrapper -->
	<!-- jQuery 2.2.3 -->
	<script src="statics/plugins/jQuery/jquery-2.2.4.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="statics/bootstrap/js/bootstrap.js"></script>
	<!-- Slimscroll -->
	<script src="statics/plugins/slimScroll/jquery.slimscroll.js"></script>
	<!-- FastClick -->
	<script src="statics/plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="statics/dist/js/app.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="statics/dist/js/demo.js"></script>
	<!--tabs-->
	<script src="statics/dist/js/app_iframe.js"></script>
	<!--tables-->
	<script src="statics/plugins/bootstrap-table/bootstrap-editable.js"></script>
	<script src="statics/plugins/bootstrap-table/bootstrap-table.js"></script>
	<script src="statics/plugins/bootstrap-table/bootstrap-table-zh-CN.js"></script>
	<script src="statics/plugins/bootstrap-table/bootstrap-table-export.js"></script>
	<script src="statics/plugins/bootstrap-table/tableExport.js"></script>
	<!-- bootstrap-select -->
	<script src="statics/plugins/bootstrap-select/bootstrap-select.js"></script>
	<!-- layer -->
	<script src="statics/plugins/layer/layer.js"></script>
	<!-- zTree -->
	<script src="statics/plugins/zTree/jquery.ztree.all-3.5.min.js"></script>
	<!-- Highcharts v5.0.7 -->
	<script src="statics/plugins/chartjs/highcharts.js"></script>
	<script src="statics/plugins/chartjs/highcharts-3d.js"></script>
	<!-- 	<script src="statics/plugins/base64/base64.js"></script> -->

	<script src="statics/js/userManager/authorityManager.js"></script>
</body>
</html>