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
<title>用户管理</title>
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
<style type="text/css">
/* html {
	overflow: hidden;
} */
</style>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="statics/plugins/ie9/html5shiv.min.js"></script>
    <script src="statics/plugins/ie9/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container" style="width: 100%;">
		<div id="user_toolbar">
			<form class="form-inline" role="form"
				style="float: left; width: 100%" method="post" id="cjrw_queryForm"
				autocomplete="off">
				<%-- <input id="userId" type="text" value="${sessionScope.userId}" hidden="hidden"> --%>
				<div class="form-group">
					<button type="button" onclick="addUserInfo()" class="btn btn-info">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;添加用户
					</button>
				</div>
			</form>
		</div>
		<table id="userTable">
		</table>

	</div>
	<!-- 点击新增、修改按钮时的弹框 -->
	<div style="display: none;" id="add_UserInfo_Form" align="center">
		<form class="form-horizontal"
			style="margin-top: 15px; padding-right: 10px">
			<div class="form-group col-lg-12">
				<label class="col-lg-4 control-label front-size">用户名称:</label>
				<div class="col-lg-8">
					<input style="width: 195px; height: 35px;" id="add_user"
						type="text" autocomplete="off" maxlength="10" />
				</div>
			</div>
			<div class="form-group col-lg-12">
				<label class="col-lg-4 control-label front-size">所属部门:</label>
				<div class="col-lg-8">
					<select id="add_dept" class="selectpicker"
						style="width: 195px; height: 35px;">
						<optgroup id="add_d" label="请选择:">
							<option value=0>请选择</option>
						</optgroup>
					</select>
				</div>
			</div>
			<div class="form-group col-lg-12">
				<label class="col-lg-4 control-label front-size" id="password_label">输入密码:</label>
				<div class="col-lg-8">
					<input style="width: 195px; height: 35px;" id="add_password"
						type="text" autocomplete="off" maxlength="30"/>
				</div>
			</div>
		</form>
	</div>

	<div style="display: none;" id="grantRole" align="center">
		<form class="form-horizontal"
			style="margin-top: 45px; width: 300px; height: 150px;">
			<div class="form-group">
				<label class="col-lg-4 control-label"><span
					style="color: red;">*</span>用户名:</label>
				<div class="col-lg-8">
					<input style="width: 195px; height: 35px;" id="grant_username"
						type="text" disabled="disabled" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-4 control-label"><span
					style="color: red;">*</span>角色:</label>
				<div class="col-lg-8">
					<select id="grant_role" class="selectpicker">
						<optgroup id="aa" label="请选择:">
						</optgroup>
					</select>
				</div>
			</div>
		</form>
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
	<!-- Highcharts v5.0.7 -->
	<script src="statics/plugins/chartjs/highcharts.js"></script>
	<script src="statics/plugins/chartjs/highcharts-3d.js"></script>
	<script src="statics/plugins/base64/base64.js"></script>

	<script src="statics/js/userManager/userManager.js"></script>
</body>
</html>