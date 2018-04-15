<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>屏柜详情</title>
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
.allRack {
	vertical-align: middle;
	width: 100%;
	height: 90%;
	OVERFLOW-X:hidden;
	OVERFLOW-Y: auto;
	overflow:hidden; /*关键*/ 
}
.rackTopo {
	width: 50%;
	height:100%;
	float:left;
	OVERFLOW-Y: auto;
	OVERFLOW-X:hidden;
}
.devices {
	width: 15%;
	height: 100%;
	float:left;
	OVERFLOW-Y: auto;
	OVERFLOW-X:hidden;
}
#devicesRackInfo {
	/* border: 1px #00ff00 solid; */
	vertical-align: middle;
	width: 30%;
	height: 100%;
	float:right;
}
.title {
	margin-top:3%;
	text-align:center;
	height: 5%;
	width:100%;
}

.rack {
	height: 80%;
	width:100%;
	/* display:flex;  
	justify-content:center; 
	align-items:center; */
} 

.rack1 {
	text-align:center;
	margin-left:20%;
	width:30%;
	margin-bottom:2%;
	border: 1px #00ff00 solid;
	float:left;
}
.rack2-1,.rack1-1 {
	text-align:right;
	width: 7%;
	font-size:16px;
	float:left;
	margin-top: 8%;
	margin-right:1.5%;
	line-height:161%;
}
.rack2-2,.rack1-2 {
	border: 1px black solid;
	float:left;
	width: 90%;
}
.rack2{
	text-align:center;
	float:left;
	margin-left:10%;
	margin-right:10%;
	margin-bottom:2%;
	border: 1px #00ff00 solid;
	width:30%;
} 
.rack2 label {
	hight: 7%;
}

.form-horizontal {
	margin-top: 30%;
}
.panel_west {
	margin-top:15%;
}
.centerdiv{  
    float:left;  
    width:50px;  
    border-right: 1px dashed black;  
    padding-bottom:1600px;  /*关键*/  
    margin-bottom:-1600px;  /*关键*/  
} 
</style>
</head>
<body class="easyui-layout">

	<div data-options="region:'center',noheader:true,"
		class="col-sm-12 col-md-12 col-ms-12">
		<div id="equip_div">
			<div style="padding-top: 15px; padding-left: 15px;"
				class="form-inline">
				<div class="form-group">
					<label role="button" onclick="save()"
						class="btn btn-sm btn-success"> <span
						class="glyphicon glyphicon-save"></span>&nbsp;保存
					</label>
				</div>
				<div class="form-group">
					<label role="button" onclick="back()"class="btn btn-sm btn-success"> 
					<span class="glyphicon glyphicon-backward"></span>&nbsp;返回
					</label>
				</div>
			</div>
		</div>
	</div>
	<hr style="border: solid 0.7px #95B8E7;width: 98%;"/>
<div class="allRack">
	<div class = "devices">
		<div style="background-color: #95B8E7;width: 100%;">
			<label style="margin-top: 4px;margin-bottom: 3px;margin-left: 5px;">设备列表</label>
		</div>
		<div id="panel_west" data-options="region:'west',title:'列表',collapsible:true,split:true,tools:'#tree_left_tool'">
		<ul id="tree_left" class="ztree"></ul>
	</div>
	<div id="tree_left_tool" style="display: none;">
		<a href="javascript:;" class="icon-zoomin"
			onclick="$.fn.zTree.getZTreeObj('tree_left').expandAll(true)"></a>
		&nbsp; <a href="javascript:;" class="icon-zoomout"
			onclick="$.fn.zTree.getZTreeObj('tree_left').expandAll(false); $.fn.zTree.getZTreeObj('tree_left').expandNode($.fn.zTree.getZTreeObj('tree_left').getNodes()[0])"></a>
		&nbsp;
	</div>
	</div>
	<div class="centerdiv"></div> 
	<div class = "rackTopo">
		<div class="title"><label >屏柜编辑页面</label></div>
		<div class = "rack">
			<div class = "rack1">
				<label>屏柜示意图-Front</label>
				<div class="rack1-1">
					<c:forEach var="i" begin="1" end="22" step="1">
						<span>${i}</span>
					</c:forEach>
				</div>
				<div class="rack1-2">
					<c:forEach var="i" begin="1" end="22" step="1">
						<div style="height: 25px;width: 100%;border-top:0.5px dashed black;"></div>
					</c:forEach>
				</div>
			</div>
			<div class = "rack2">
				<label>屏柜示意图-Rear</label>
				<div class="rack2-1">
					<c:forEach var="i" begin="1" end="22" step="1">
						<span>${i}</span>
					</c:forEach>
				</div>
				<div class="rack2-2">
					<c:forEach var="i" begin="1" end="22" step="1">
						<div style="height: 25px;width: 100%;border-top:0.5px dashed black;"></div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="centerdiv"></div> 
	<div id = "devicesRackInfo">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="name" class="control-label col-sm-3" >名称:</label>
				<div class ="col-sm-5">
    				<input type="text" class="form-control" id="name" placeholder="请输入名称">
    			</div>
			</div>
			<div class="form-group">
				<label for="code" class="control-label col-sm-3">CODE:</label>
				<div class ="col-sm-5">
    				<input type="text" class="form-control" id="code" placeholder="请输入code">
    			</div>
			</div>
			<div class="form-group">
				<label for="height" class="control-label col-sm-3">高度:</label>
				<div class ="col-sm-3">
    				<input type="text" class="form-control" id="height" placeholder="请输入高度">
    			</div>
    				<label for="height" class="control-label" style="font-size: 16px;">U</label>
			</div>
			<div class="form-group">
				<label for="stationId" class="control-label col-sm-3">地址:</label>
				<div class ="col-sm-5">
	    			<select class="form-control" name= "stationId" id="stationId">
				      <option value="0">请选择地址...</option>
				      <c:forEach var="obj" items="${stationList}" varStatus="i">
				         <option value="${obj.id}">${obj.name}</option>
				      </c:forEach>
				    </select>
			    </div>
			</div>
			<div class="form-group">
				<label for="remark" class="control-label col-sm-3">描述:</label>
				<div class ="col-sm-5">
	    			<textarea class="form-control" rows="3" id="remark"></textarea>
				</div>
			</div>
			<!-- <div class="col-sm-offset-2 col-sm-8">
		      <button type="submit" class="btn btn-default">登录</button>
		    </div> -->
		</form>
		<button onclick="rackHight()">ceshi</button>
	</div>
</div>	

	<div style="display: none;" id="add_device1" align="center">
		<form class="form-horizontal"
			style="margin-top: 15px; padding-right: 10px">
			<div class="form-group col-md-12">
				<label class="col-md-5 control-label front-size"><span
					style="color: red;">*</span>设置添加的位置:</label><br/>
				<div class="col-md-9">
					<input style="width: 90px; height: 30px;" type="text"
						autocomplete="off" id="loca_hight" />
				</div>
			</div>
		</form>
	</div>
	<div style="display: none;" id="add_device2" align="center">
		<form class="form-horizontal"
			style="margin-top: 15px; padding-right: 10px">
			<div class="form-group col-md-12">
				<label class="col-md-5 control-label front-size"><span
					style="color: red;">*</span>设置添加的位置:</label><br/>
				<div class="col-md-9" style="margin-left:25px;">
					<input style="width: 45px; height: 30px;" type="text"
						autocomplete="off" id="loca_hight1" />
					&nbsp;&nbsp;&mdash;&nbsp;&nbsp;
					<input style="width: 45px; height: 30px;" type="text"
						autocomplete="off" id="loca_hight2" />
				</div>
			</div>
		</form>
	</div>
<input id="add_device_name" value="123456" />
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
	<script src="statics/js/devicesRack/devicesRackInfo.js"></script>
</body>
</html>