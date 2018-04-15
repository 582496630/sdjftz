<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>厂长信息</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
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
	<link rel="stylesheet" href="statics/plugins/bootstrap-table/bootstrap-editable.css">
	<link rel="stylesheet" href="statics/plugins/bootstrap-table/bootstrap-table.min.css">
	<!-- layer style -->
	<link rel="stylesheet" href="statics/plugins/layer/skin/default/layer.css">
	<style type="text/css">
	    html {
	        overflow: hidden;
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
	 
		<a href="#" id="department">选择部门</a>
	 
         
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
<script src="statics/plugins/bootstrap-table/tableExport.js" ></script>
<!-- layer -->
<script src="statics/plugins/layer/layer.js"></script>
<script>
	$(function(){
		   $('#department').editable({
	           type: "select",              //编辑框的类型。支持text|textarea|select|date|checklist等
	           source: [{ value: 1, text: "开发部" }, { value: 2, text: "销售部" }, {value:3,text:"行政部"}],
	           title: "选择部门",           //编辑框的标题
	           disabled: false,           //是否禁用编辑
	           emptytext: "空文本",       //空值的默认文本
	           mode: "popup",            //编辑框的模式：支持popup和inline两种模式，默认是popup
	           validate: function (value) { //字段验证
	               if (!$.trim(value)) {
	                   return '不能为空';
	               }
	           }
	       });
	
	});
</script>
</body>
</html>