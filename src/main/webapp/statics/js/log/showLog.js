$(function() {
	// 设置本月第一天
	var date = new Date();
	date.setDate(1);
	$('#startTime').val(date.Format("yyyy-MM-dd hh:mm"));
	$('#endTime').val(new Date().Format("yyyy-MM-dd hh:mm"));

	var interval = new Interval();
	interval.initInterval();

});

function initDropDown() {
	getFacDropDown("fac", "aa");
	$("#fac").selectpicker({
		// style : 'btn-info',// 显示样式
		width : 160, // 宽度
		size : 10
	});
	$("#fac").selectpicker('refresh');
	$("#mon_flaw_lev").selectpicker({
		// style : 'btn-info',// 显示样式
		width : 160, // 宽度
		size : 10
	});
	$("#mon_flaw_lev").selectpicker('refresh');
	$("#check").selectpicker({
		// style : 'btn-info',// 显示样式
		width : 160, // 宽度
		size : 10
	});
	$("#check").selectpicker('refresh');
}

/**
 * 表格初始化 加载调度台区数据
 */
var Interval = function() {
	var t1 = new Object();
	var startTime = $('#startTime').val();
	var endTime = $('#endTime').val();
	var patrn = /^[1-2][0-9]{3}-((0[0-9])|[0-9]|(1[1-2]))-(([0-2][0-9])|[0-9]|(3[0-1]))(\s+(([0-1][0-9])|[0-9]|(2[0-3]))(:|：)(([0-5][0-9])|[0-9])){0,1}/;
	if (!patrn.exec(startTime) || !patrn.exec(endTime)) {
		layer.alert('时间格式错误！！正确格式如下：<br/>2017-01-01或2017-01-01 00:00', {
			icon : 2
		});
		return;
	}
	t1.initInterval = function() {
		$('#logTable').bootstrapTable({
			url : 'Log/selectForPage.do',
			method : 'POST',
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			toolbar : 'defect_toolbar',
			striped : true, // 是否显示行间隔色
			// showColumns : true,
			pagination : true,
			queryParams : t1.intervalQueryParams,
			queryParamsType : '',
			sidePagination : "server",
			paginationLoop : true,
			// showRefresh : true,
			paginationPreText : '上一页',
			paginationNextText : '下一页',
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10 ], // 可供选择的每页的行数（*）
			// showExport : true,
			exportDataType : 'all',
			clickToSelect : true, // 设置true
			singleSelect : true, // 设置True 将禁止多选
			columns : [ {
				field : '',
				title : '序号',
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					return index + 1;
				}
			}, {
				field : 'userName',
				title : '操作用户',
				valign : 'middle',
				align : 'center'
			}, {
				field : 'logDate',
				title : '操作时间',
				valign : 'middle',
				align : 'center'
			}, {
				field : 'logCrud',
				title : '操作描述',
				valign : 'middle',
				align : 'center'
			}, {
				field : 'logData',
				title : '操作内容',
				valign : 'middle',
				align : 'center'
			} ]
		});
	};
	/**
	 * 得到厂站名称查询的参数
	 */
	t1.intervalQueryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			pageNum : this.pageNumber,
			pageSize : this.pageSize,
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val(),
			_ : $.now(),
		};
		return temp;
	};
	return t1;
};

function query() {
	$('#logTable').bootstrapTable('refresh');
}

//日期转换
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}