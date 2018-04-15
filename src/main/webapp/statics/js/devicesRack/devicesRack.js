var itemData = {};
var main = null;
var ctx = $("#ctxValue").val();
$(function() {
	main = new Main();
	main.init();

	initTree();
	
	var interval = new Interval();
	interval.initInterval();

});

var initTree = function(id) {
	$.post('rack/engineRoomTree.do?', function(result) {
		var t = $("#tree_left");
		t = $.fn.zTree.init(t, setting, result.list);
		var treeObj = $.fn.zTree.getZTreeObj("tree_left");
		treeObj.expandAll(treeObj);
	}, 'json');

}
var setting = {
	check : {
		enable : true,
		chkStyle: "checkbox"
	},
	view : {
		showIcon : true,
		showTitle : false// 2 这个开关也要打开，默认是关闭的
	},
	data : {
		key : {
			url : "myurl",// 更改默认的超链接获取属性,取消超链接
			//title:"id"
		},
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pId",
			rootPId : ""
		}
	},
	edit : {
		enable : true,
		showRemoveBtn : false
	},
	callback : {
		beforeClick : beforeClick,
		onClick : onClick,
		onDblClick: getEnginneRoomInfo,
		onRemove : deleteEngineRoom
	}
};
function beforeClick(treeId, treeNode) {
	return (treeNode.click != false);
}

function onClick(event, treeId, treeNode) {
	var roomId = treeNode.id;
	$("#roomId").attr("value",roomId);
	$('#devicesRackTable').bootstrapTable('refresh');
}
//获取机房详细内容
function getEnginneRoomInfo(event, treeId, treeNode) {
	var roomId = treeNode.id;
	$.ajax({
		url : "rack/engineRoomOne.do",
		type : "POST",
		dataType : "json",
		data : {
			roomId : roomId
		},
		success : function(data) {
			if (!data.roomName) {
				data.roomName = "无";
			}
			if (!data.address) {
				data.address = "无";
			}
			if (!data.description) {
				data.description = "无";
			}
			if (!data.floor) {
				data.floor = "无";
			}
			layer.alert("名称：" +data.roomName +"<br />地址：" + data.address +"<br />楼层：" 
					+ data.floor +"<br />描述："+data.description)
		}
	})
}
//删除机房
function deleteEngineRoomTreeNo() {
}

function deleteEngineRoom() {
	var treeObj = $.fn.zTree.getZTreeObj("tree_left");
	var checkedNodes = treeObj.getCheckedNodes();
	if (checkedNodes.length < 1) {
		layer.msg("请先选择数据",{
			icon : 2,
			time : 1500});
		return;
	}
	var array = new Array();
	for ( var index in checkedNodes) {
		if (index == "del") {
			continue;
		}
		array.push(checkedNodes[index].id);
	}
	$.ajax({
		url : "rack/deledtEngineRoom.do",
		type : "POST",
		dataType : "json",
		data : {
			roomIds : array
		},
		success : function(data) {
			if (data.code == 200) {
				layer.msg(data.msg, {
					icon : 1,
					time : 1500
				});
			} else {
				layer.msg(data.msg, {
					icon : 2,
					time : 1500
				});
			}
			initTree();
			$('#devicesRackTable').bootstrapTable('refresh');
		}
	})
}

function addEngineRoom() {
	var index = layer.open({
		type : 1,
		title : '添加机房',
		anim : 4,
		area : [ '600px', '400px' ],
		content : $("#add_engineroom"),
		btn : [ '确认', '关闭' ],
		yes : function() {
			$.ajax({
				url : "rack/addEngineRoom.do",
				type : "POST",
				dataType : "json",
				data:{
					roomName : $("#roomName").val(),
					decription : $("#decription").val(),
					address : $("#address").val(),
					floor : $("#floor").val()
				},
				success:function(data){
					if (data.code == 200) {
						layer.msg(data.msg, {
							icon : 1,
							time : 1500
						}, function() {
							initTree();
							$('#devicesRackTable').bootstrapTable('refresh');
							layer.close(index);
						});
					} else {
						layer.msg(data.msg, {
							icon : 2,
							time : 1500
						});
					}
				},
			})
			layer.close(index);
		}
	});
}


var Interval = function() {
	var t1 = new Object();
	t1.initInterval = function() {
		$('#devicesRackTable').bootstrapTable({
			url : 'rack/devicesRack.do',
			method : 'POST',
			dataType : 'json',
			contentType : "application/x-www-form-urlencoded",
			cache : false,
			toolbar : '#equip_toolbar',
			striped : true, // 是否显示行间隔色
			showColumns : true,
			queryParams : t1.intervalQueryParams,
			queryParamsType : '',
			sidePagination : "server",
			paginationLoop : true,
			showRefresh : true,
			paginationPreText : '上一页',
			paginationNextText : '下一页',
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 15, // 每页的记录行数（*）
			pageList : [ 15, 30, 100, 200 ],
			showExport : true,
			exportDataType : 'all',
			clickToSelect : true,
			columns : [ {
				checkbox : true,
				field : 'id',
				//title : 'id',
				align : 'center',
				valign : 'middle',
				/*formatter : function(value, row, index) {
					var url = '';
					url += '<input name="rack_check" type="checkbox" value='
							+ row.id
							+ ' />';
					return url;
				}*/
			}, {
				field : '',
				title : '序号',
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					return index + 1;
				}
			}, {
				field : 'name',
				title : '名称',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'stationId',
				title : '地址',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'code',
				title : 'CODE',
				align : 'center',
				valign : 'middle',
			}, {	
				field : 'hight',
				title : '高度',
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					return value + "U";
				}
			}, {
				field : 'remark',
				title : '备注',
				align : 'left',
				valign : 'middle',
			}, {
				field : 'modifyTime',
				title : '修改时间',
				align : 'center',
				valign : 'middle',
				formatter : function(value,row,index) {
					return formatDateTime(value);
				}
			}],
			onClickRow : function(row) {
				$('#devicesRackTable').bootstrapTable('uncheckAll');
			},
			onLoadSuccess : function(row) {
			$('#devicesRackTable').bootstrapTable('uncheckAll');
			$(".fixed-table-container tbody tr").removeAttr("class");
		}
		});
	};
	
	/**
	 * 获取当前登录用户的id
	 */
	t1.intervalQueryParams = function(params) {
		var temp = {
			pageNumer : this.pageNumber,
			pageSize : this.pageSize,
			name : $('#name').val(),
			code : $('#code').val(),
			fq : $('#fq').val(),
			roomId : $('#roomId').val(),
			_ : $.now()
		};
		return temp;
	};
	return t1;
};

function query() {// 查询事件
	$('#devicesRackTable').bootstrapTable('refresh');
}
function addDevicesRack() {//添加屏柜跳转页面
	window.location.href = ctx + "/rack/devicesRackInfo.do";
}


/**
 * 日期转换
 * @param inputTime
 * @returns
 */
function formatDateTime(inputTime) {    
    var date = new Date(inputTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
}; 


// 主操作类
function Main() {
	this.init = function() {

	};
}
