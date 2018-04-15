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
<title>跳闸信息总台账</title>
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
<!-- datepicker style -->
<link rel="stylesheet"
	href="statics/plugins/datepicker/bootstrap-datetimepicker.css">
<!-- laydate -->
<link rel="stylesheet"
	href="statics/plugins/laydate/skins/default/laydate.css">
<link rel="stylesheet" href="statics/plugins/laydate/need/laydate.css">
<!-- layer style -->
<link rel="stylesheet"
	href="statics/plugins/layer/skin/default/layer.css">
<style type="text/css">
</style>
<style>
legend {
	margin-bottom: 0px;
	font-size: 15px;
	text-align: left;
}

.borad-del {
	border: none;
	outline: none;
}

.form-group {
	margin-bottom: 1px;
}

.btn {
	padding-top: 3px;
	padding-bottom: 3px;
}

.front-size {
	font-size: 10px;
	font-family: Microsoft YaHei;
}

#top_block {
	display: block;
	margin-left: auto;
	margin-right: auto;
	border-style: ridge;
	background-color: rgba(248, 248, 248, 1);
}

.toolbar {
	height: 60px;
}

#divBorder {
	border: 2px solid #eee;
	display: block;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: inset 0 1px 1px 0 #c7c7c7;
	-moz-box-shadow: inset 0 1px 1px 0 #c7c7c7;
	box-shadow: inset 0 1px 1px 0 #c7c7c7;
}

#divBorder2 {
	border: 2px solid #eee;
	display: block;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: inset 0 1px 1px 0 #c7c7c7;
	-moz-box-shadow: inset 0 1px 1px 0 #c7c7c7;
	box-shadow: inset 0 1px 1px 0 #c7c7c7;
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
	<div class="col-sm-12 col-md-12 col-ms-12">
		<div id="defect_toolbar" class="toolbar" style="padding-top: 15px;">
			<form class="form-inline" role="form"
				style="float: left; width: 100%" method="post" id="cjrw_queryForm"
				autocomplete="off">
				<div class="form-group">
					<label class="input-sm">发生日期:</label>
					<div class='input-group' id='datetimepicker_startdate'
						style="width: 180px;">
						<!-- <input id="startTime" name="startDate" type='text'
							class="form-control" placeholder="开始日期" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span> -->
						<input id="startTime" name="startDate" placeholder="日期(年-月-日 时:分)" style="height: 28px;"
								class="laydate-icon timeUstyle stateUTime form-control"
								onclick="laydate({istime: true,format:'YYYY-MM-DD hh:mm'})">
					</div>
					&nbsp;&nbsp;至&nbsp;
					<div class='input-group' id='datetimepicker_enddate'
						style="width: 180px;">
					<!-- 	<input id="endTime" name="endDate" type='text'
							class="form-control" placeholder="结束日期" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span> -->
						<input id="endTime" name="endDate" placeholder="日期(年-月-日 时:分)" style="height: 28px;"
								class="laydate-icon timeUstyle stateUTime form-control"
								onclick="laydate({istime: true,format:'YYYY-MM-DD hh:mm'})">
					</div>
				</div>

				<div class="form-group" style="padding-bottom: 5px;">
					<button type="button" id="cjrw_queryBtn" onclick="query()"
						class="btn btn-success">
						<span class="glyphicon glyphicon-search"></span>&nbsp;搜索
					</button>
				</div>
			</form>
		</div>
		<table id="logTable">
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
	<!-- bootstrap-datepicker -->
	<script src="statics/plugins/datepicker/bootstrap-datetimepicker.js"></script>
	<script
		src="statics/plugins/datepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<!-- layer -->
	<script src="statics/plugins/layer/layer.js"></script>
	<!-- laydate -->
	<script src="statics/plugins/laydate/laydate.js"></script>	
	<!-- intervalInfo -->
	<script src="statics/js/log/showLog.js"></script>
</body>
</html>