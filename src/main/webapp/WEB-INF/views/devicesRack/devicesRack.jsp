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
<title>屏柜维护</title>
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
<!--zTree  -->
<link rel="stylesheet" href="statics/plugins/zTree/zTreeStyle.css">
<!--easyui  -->
<link href="statics/plugins/jquery-easyui-1.4/themes/default/easyui.css"
	rel="stylesheet" />
<link href="statics/plugins/jquery-easyui-1.4/themes/icon.css"
	rel="stylesheet" />
<!--bootstrap-fileinput  -->
<link href="statics/plugins/bootstrap-fileinput/css/fileinput.min.css"
	rel="stylesheet" />

<style type="text/css">
.fixed-table-container tbody .selected td {
    background-color: #b7bbce;
}
</style>
</head>
<body class="easyui-layout">
		<div id="panel_west"
		data-options="region:'west',title:'机房地址列表',collapsible:true,split:true,tools:'#tree_left_tool'"
		style="width: 240px;">
		<ul id="tree_left" class="ztree"></ul>
	</div>
	<div id="tree_left_tool" style="display: none;">
		<span class="glyphicon glyphicon-plus" onclick="addEngineRoom()" style="margin-right: 5px;cursor: pointer;"></span>
		<span class="glyphicon glyphicon-minus" onclick="deleteEngineRoom()" style="margin-right: 5px;cursor: pointer;"></span>
		<a href="javascript:;" class="icon-zoomin"
			onclick="$.fn.zTree.getZTreeObj('tree_left').expandAll(true)"></a>
		&nbsp; <a href="javascript:;" class="icon-zoomout"
			onclick="$.fn.zTree.getZTreeObj('tree_left').expandAll(false); $.fn.zTree.getZTreeObj('tree_left').expandNode($.fn.zTree.getZTreeObj('tree_left').getNodes()[0])"></a>
		&nbsp;
	</div>

	<div data-options="region:'center',noheader:true,"
		class="col-sm-12 col-md-12 col-ms-12">
		<div id="equip_div">
			<div style="padding-top: 15px; padding-left: 15px;"
				class="form-inline">
				<div class="form-group">
					<label role="button" id="cjrw_queryBtn" onclick="addDevicesRack()"
						class="btn btn-sm btn-success"> <span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加
					</label>
				</div>
				<div class="form-group">
					<label role="button" id="cjrw_queryBtn" onclick="query()"
						class="btn btn-sm btn-success"> <span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改
					</label>
				</div>
				<div class="form-group">
					<label role="button" id="cjrw_queryBtn" onclick="query()"
						class="btn btn-sm btn-success"> <span
						class="glyphicon glyphicon-minus"></span>&nbsp;删除
					</label>
				</div>
				<div class="form-group">
					<label role="button" id="cjrw_queryBtn" onclick="query()"
						class="btn btn-sm btn-success"> <span
						class="glyphicon glyphicon-refresh"></span>&nbsp;重载
					</label>
				</div>
			</div>
			<div id="equip_toolbar" class="toolbar" style="padding-top: 15px;">
				<form class="form-inline" role="form" 
					style="float: left; width: 100%" method="post" id="pg_queryForm"
					autocomplete="off">
					<div class="form-group">
						<label class="input-sm">名称:</label> 
						<input id="name"type="text" style="width: 195px; height: 35px;" />
					</div>
					<div class="form-group">
						<label class="input-sm">CODE:</label> 
						<input id="code" type="text" style="width: 195px; height: 35px;" />
					</div>
					<div class="form-group">
						<label class="input-sm">分区:</label>
						<select id="fq" class="selectpicker">
							<optgroup id="aa" label="请选择：" class="input-sm">
								<option value=0>全部</option>
								<option value=1>Ⅰ区</option>
								<option value=2>Ⅱ区</option>
								<option value=3>Ⅲ区</option>
								<option value=4>Ⅳ区</option>
							</optgroup>
						</select>
					</div>
					<input id="roomId" type="text" style="display: none; width: 195px; height: 35px;" />
					<div class="form-group" style="padding-bottom: 5px;">
						<button type="button" onclick="query()"
							class="btn btn-md btn-success">
							<span class="glyphicon glyphicon-search"></span>&nbsp;查询
						</button>
					</div>
				</form>
			</div>
			<table id="devicesRackTable">
			</table>
		</div>
	</div>
	
		<div style="display: none;OVERFLOW-X:hidden;" id="add_engineroom" align="center">
		<form class="form-horizontal" style="vertical-align: middle;">
			<div class="form-group">
			<br/>
			<br/>
				<label class="col-sm-4 control-label front-size"><span style="color: red;">*</span>机房名称:</label>
				<div class ="col-sm-5">
    				<input type="text" class="form-control" id="roomName" placeholder="请输入名称">
    			</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label front-size"><span style="color: red;">*</span>机房所在地址:</label>
				<div class ="col-sm-5">
    				<input type="text" class="form-control" id="address" placeholder="请输入地址">
    			</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label front-size"><span style="color: red;">*</span>机房所在楼层:</label>
				<div class ="col-sm-3">
    				<input type="text" class="form-control" id="floor" placeholder="请输入楼层">
    			</div>
			</div>
			<div class="form-group">
				<label for="remark" class="control-label col-sm-4">描述:</label>
				<div class ="col-sm-5">
	    			<textarea class="form-control" rows="3" id="description"></textarea>
				</div>
			</div>
		</form>
	</div>
	
	
<input type="hidden" id = ctxValue value="${pageContext.request.contextPath}" />
	<!-- jQuery 2.2.3 -->
	<script src="statics/plugins/jQuery/jquery-2.2.4.js"></script>
	<!--Jtopo  -->
	<script src="statics/plugins/Jtopo/jtopo-0.4.8-min.js"></script>
	<script src="statics/plugins/Jtopo/zJTopo.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="statics/bootstrap/js/bootstrap.js"></script>
	<!--bootstrap-fileinput -->
	<script src="statics/plugins/bootstrap-fileinput/js/fileinput.min.js"></script>
	<script src="statics/plugins/bootstrap-fileinput/js/locales/zh.js"></script>
	<!--tabs-->
	<script src="statics/dist/js/app_iframe.js"></script>
	<!--tables-->
	<script src="statics/plugins/bootstrap-table/bootstrap-editable.js"></script>
	<script src="statics/plugins/bootstrap-table/bootstrap-table.js"></script>
	<script src="statics/plugins/bootstrap-table/bootstrap-table-zh-CN.js"></script>
	<script src="statics/plugins/bootstrap-table/bootstrap-table-export.js"></script>
	<script src="statics/plugins/bootstrap-table/tableExport.js"></script>
	<!-- Slimscroll -->
	<script src="statics/plugins/slimScroll/jquery.slimscroll.js"></script>
	<!-- FastClick -->
	<script src="statics/plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="statics/dist/js/demo.js"></script>
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
	<!-- zTree -->
	<script src="statics/plugins/zTree/jquery.ztree.all-3.5.min.js"></script>
	<script src="statics/plugins/zTree/jquery.ztree.exhide-3.5.min.js"></script>

	<script src="statics/plugins/jquery-easyui-1.4/plugin_easyui.js"></script>
	<script src="statics/plugins/jquery-easyui-1.4/jquery.easyui.min.js"
		type="text/javascript"></script>
	<script
		src="statics/plugins/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"
		type="text/javascript"></script>

	<!-- intervalInfo -->
	<script src="statics/js/devicesRack/devicesRack.js"></script>
</body>
</html>