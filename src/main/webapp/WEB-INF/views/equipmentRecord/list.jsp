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
<title>设备台账维护</title>
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
#tooltip {
	display: none;
	position: absolute;
	z-index: 999999;
	background-color: black;
	border-radius: 5px;
	padding: 5px 20px 10px 5px;
	color: white;
	opacity: 0.7;
	border: 1px #00ff00 solid;
}
</style>
</head>
<body class="easyui-layout">

	<div id="panel_west"
		data-options="region:'west',title:'列表',collapsible:true,split:true,tools:'#tree_left_tool'"
		style="width: 240px;">
		<ul id="tree_left" class="ztree"></ul>
	</div>
	<div id="tree_left_tool" style="display: none;">
		<a href="javascript:;" class="icon-zoomin"
			onclick="$.fn.zTree.getZTreeObj('tree_left').expandAll(true)"></a>
		&nbsp; <a href="javascript:;" class="icon-zoomout"
			onclick="$.fn.zTree.getZTreeObj('tree_left').expandAll(false); $.fn.zTree.getZTreeObj('tree_left').expandNode($.fn.zTree.getZTreeObj('tree_left').getNodes()[0])"></a>
		&nbsp;
	</div>

	<div data-options="region:'center',noheader:true,"
		class="col-sm-12 col-md-12 col-ms-12">
		<div id="equip_div" style="display: none;">
			<div style="padding-top: 15px; padding-left: 15px;"
				class="form-inline">
				<div class="form-group">
					<label role="button" id="cjrw_queryBtn" onclick="addEquip()"
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
					style="float: left; width: 100%" method="post" id="cjrw_queryForm"
					autocomplete="off">
					<div class="form-group">
						<label class="input-sm">分区:</label><select id="device_fq"
							class="selectpicker">
							<optgroup id="aa" label="请选择：" class="input-sm">
								<option value=0>全部</option>
								<option value=1>Ⅰ区</option>
								<option value=2>Ⅱ区</option>
								<option value=3>Ⅲ区</option>
							</optgroup>
						</select>
					</div>
					<div class="form-group">
						<label class="input-sm">名称:</label> <input id="device_name"
							type="text" style="width: 195px; height: 35px;" />
					</div>

					<div class="form-group" style="padding-bottom: 5px;">
						<button type="button" id="cjrw_queryBtn" onclick="query()"
							class="btn btn-md btn-success">
							<span class="glyphicon glyphicon-search"></span>&nbsp;查询
						</button>
					</div>
				</form>
			</div>
			<table id="ledgerTable">
			</table>
		</div>
		<div id="system_div" style="display: block;">
			<div style="padding-top: 15px; padding-left: 15px;"
				class="form-inline">
				<div class="form-group">
					<label role="button" id="cjrw_queryBtn" onclick="system_add();"
						class="btn btn-sm btn-success"> <span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加
					</label>
				</div>
				<div class="form-group">
					<label role="button" id="cjrw_queryBtn" onclick="system_upd()"
						class="btn btn-sm btn-success"> <span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改
					</label>
				</div>
				<div class="form-group">
					<label role="button" id="cjrw_queryBtn" onclick="system_del()"
						class="btn btn-sm btn-success"> <span
						class="glyphicon glyphicon-minus"></span>&nbsp;删除
					</label>
				</div>
				<div class="form-group">
					<label role="button" id="cjrw_queryBtn" onclick="system_refresh()"
						class="btn btn-sm btn-success"> <span
						class="glyphicon glyphicon-refresh"></span>&nbsp;重载
					</label>
				</div>
			</div>
			<table id="systemTable">
			</table>
		</div>
	</div>

	<div style="display: none;" id="add_equip" align="center">
		<form class="form-horizontal"
			style="margin-top: 15px; padding-right: 10px"
			enctype="multipart/form-data">
			<div class="form-group col-md-12">
				<div class="checkbox-custom checkbox-default">
					<label class="col-md-2 control-label front-size">边界设备:</label>
					<div class="col-md-10" align="left">
						<input type="checkbox" id="bjsb"/>
					</div>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">系统:</label>
				<div class="col-md-8" style="margin-top: -4px;">
					<select class="selectpicker" name="form_select" id="systemId"  data-live-search="true">
						<option value=0>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">分区:</label>
				<div class="col-md-8" style="margin-top: -4px;">
					<select class="selectpicker" name="form_select" id="fq">
							<option value=1>Ⅰ区</option>
							<option value=2>Ⅱ区</option>
							<option value=3>Ⅲ区</option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">设备类型:</label>
				<div class="col-md-8" style="margin-top: -4px;">
					<select class="selectpicker" name="form_select" id="typesetId" data-live-search="true">
							<option value=0>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">设备名称:</label>
				<div class="col-md-8">
					<input style="width: 195px; height: 30px;" type="text"
						autocomplete="off" maxlength="10" id="sb_name"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">设备责任人:</label>
				<div class="col-md-8">
					<input style="width: 195px; height: 30px;" type="text"
						autocomplete="off" maxlength="10" id="sbzrrName" list="datalist_sbzrrName"/>
					<datalist id="datalist_sbzrrName">
						<option>暂无数据</option>
					</datalist>	
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">设备维护人:</label>
				<div class="col-md-8">
					<input style="width: 195px; height: 30px;" type="text"
						autocomplete="off" maxlength="10" id="sbwhrName" list="datalist_sbwhrName"/>
					<datalist id="datalist_sbwhrName">
						<option>暂无数据</option>
					</datalist>	
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">序列号:</label>
				<div class="col-md-8">
					<input style="width: 195px; height: 30px;" type="text"
						autocomplete="off" id="xlh"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">设备型号:</label>
				<div class="col-md-8">
					<input style="width: 195px; height: 30px;" type="text"
						autocomplete="off" id="sbxh" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">生产厂家:</label>
				<div class="col-md-8" style="margin-top: -4px;">
					<select class="selectpicker" name="form_select" id="sccj" data-live-search="true">
						<option value=0>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">运维等级:</label>
				<div class="col-md-8">
					<input style="width: 195px; height: 30px;" type="text"
						autocomplete="off" id="ywdj" list="datalist_ywdj"/>
					<datalist id="datalist_ywdj">
						<option>暂无数据</option>
					</datalist>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">屏柜:</label>
				<div class="col-md-8">
					<input style="width: 195px; height: 30px;" type="text"
						autocomplete="off" id="pgId" list="datalist_pgId"/>
					<datalist id="datalist_pgId">
						<option>暂无数据</option>
					</datalist>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">产站:</label>
				<div class="col-md-8" style="margin-top: -4px;">
					<select class="selectpicker" name="form_select" id="facId"  data-live-search="true">
							<option value=0>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label class="col-md-2 control-label front-size">备注:</label>
				<div class="col-md-9" style="margin-top: -4px;">
					<textarea style="min-width: 552px; height: 60px;" id="remark"></textarea>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">网口:</label>
				<div class="col-md-3">
					<a href="javascript:openPort();" role="button"
						class="label label-success"><span
						class="glyphicon glyphicon-plus"></span></a>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">上传附件:</label>
				<div class="col-md-8">
					<input id="file-u" type="file" class="file" multiple
						data-min-file-count="1" id="scfj"/>
				</div>
			</div>
			<div class="form-group col-md-12">
				<div class="col-md-9 col-md-offset-2">
					<canvas id="canvas_port" width="552" height="120"
						style="border: 1px solid gray;">您的浏览器不支持该技术显示</canvas>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">SNMP读串:</label>
				<div class="col-md-8">
					<input style="width: 195px; height: 30px;" type="text"
						autocomplete="off" id="snmpRead"/>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label front-size">SNMP端口:</label>
				<div class="col-md-8">
					<input style="width: 195px; height: 30px;" type="text"
						autocomplete="off" id="snmpPort" />
				</div>
			</div>
		</form>
		<div id="tooltip">
		</div>
	</div>

	<!--网口界面  -->
	<div style="display: none;" id="port_view" align="center">
		<div id="portMenu" class="justManager easyui-menu"
			style="width: 120px;">
			<div onclick="showRemovePort()" iconCls="icon-remove">删除网卡</div>
			<div onclick="showModifyPort()" iconCls="icon-edit">编辑网卡</div>
			<div id="portMenu_jianshi" onclick="jianshi()" iconCls="icon-edit">监视</div>
			<div id="portMenu_quxiaojianshi" onclick="quxiaojianshi()"
				iconCls="icon-edit">取消监视</div>
		</div>
		<div id="dlg_port" style="padding: 10px;">
			<form class="mainForm form-horizontal"
				style="margin-top: 15px; padding-right: 10px">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label front-size">名称:</label>
					<div class="col-md-8">
						<input style="width: 195px; height: 30px;" type="text"
							id="port_name" autocomplete="off" maxlength="10" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label front-size">IP:</label>
					<div class="col-md-8">
						<input style="width: 195px; height: 30px;" type="text"
							id="port_ip" autocomplete="off" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label front-size">MAC:</label>
					<div class="col-md-8">
						<input style="width: 195px; height: 30px;" type="text"
							id="port_mac" autocomplete="off" />
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label front-size">平面:</label>
					<div class="col-md-8">
						<label> <input style="margin-top: 10px;" type="radio"
							name="port_pm" value="一平面" />一平面
						</label> &nbsp; <label> <input type="radio" name="port_pm"
							value="二平面" />二平面
						</label> &nbsp; <label> <input type="radio" name="port_pm"
							value="" checked="checked" /> 无
						</label>
					</div>
				</div>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label front-size">监视:</label>
					<div class="col-md-2">
						<input type="checkbox" name="port_star" value="1" />
					</div>
				</div>
			</form>
		</div>
	</div>

	<div style="display: none;" id="sys_add" align="center">
		<form class="form-horizontal"
			style="margin-top: 15px; padding-right: 10px">
			<div class="form-group col-md-12">
				<label class="col-md-3 control-label front-size"><span
					style="color: red;">*</span>名称:</label>
				<div class="col-md-9">
					<input style="width: 195px; height: 30px;" type="text"
						autocomplete="off" id="system_name" />
				</div>
			</div>
		</form>
	</div>
	<input id="role" type="hidden" value="${role.areaId}" />
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
	<script src="statics/js/equipmentRecord/list.js"></script>
</body>
</html>