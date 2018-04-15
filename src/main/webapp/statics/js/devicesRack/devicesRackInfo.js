var itemData = {};
var main = null;
var ctx = $("#ctxValue").val();
$(function() {
	main = new Main();
	main.init();
	
	initTree();
	initSelectpicker();

});

var initTree = function(id) {
	$.post('rack/deviceRackLeftTreeInfo.do?', function(result) {
		var t = $("#tree_left");
		t = $.fn.zTree.init(t, setting, result.list);
		var treeObj = $.fn.zTree.getZTreeObj("tree_left");
		treeObj.expandAll(treeObj);
	}, 'json');

}
var setting = {
	check : {
		enable : false
	},
	view : {
		showIcon : true
	},
	data : {
		key : {
			url : "myurl"// 更改默认的超链接获取属性,取消超链接
		},
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "pId",
			rootPId : ""
		}
	},
	callback : {
		//beforeClick : beforeClick,
		onClick : onClick
	}
};
function beforeClick(treeId, treeNode) {
	return (treeNode.click != false);
}
function onClick(event, treeId, treeNode) {
	var height = $("#height").val();
	if (!height) {
		layer.msg("请先设置屏柜高度",{
			icon : 2,
			time : 2000
		});
		return;
	}
	var heightInt = parseInt(height);
	if (isNaN(heightInt)) {
		layer.msg("请输入正确的屏柜高度（提示：数字）",{
			icon : 2,
			time : 2000
		});
		return;
	}
	//获取设备信息，根据设备高度判断弹出框
	var deviceName = treeNode.name;
	$("#add_device_name").attr("value",deviceName);
	
/*	var index = layer.open({
		type : 1,
		title : '添加设备',
		anim : 4,
		area : [ '380px', '160px' ],
		content : $("#add_device1"),
		btn : [ '确认', '关闭' ],
		yes : function() {
			deviceAdd1();
			layer.close(index);
		}
	});*/
	var index = layer.open({
		type : 1,
		title : '添加设备',
		anim : 4,
		area : [ '380px', '180px' ],
		content : $("#add_device2"),
		btn : [ '确认', '关闭' ],
		yes : function() {
			deviceAdd2();
			layer.close(index);
		}
	});
}

function initSelectpicker() {
	$('#fq').selectpicker({
		style : 'btn-info',
		width : 160, // 宽度
		size : 10,
	});
}

//根据屏柜设定的高度来改变屏柜
function rackHight() {
	var hight = parseInt($("#height").val());
	var array = new Array();
	var s;
	if (isNaN(hight)) {
		layer.msg("请输入正确的屏柜高度（提示：数字）",{
			icon : 2,
			time : 2000
		});
		return;
	}
	if (hight > 100) {
		layer.msg("屏柜高度超出（提示：数字1-100）",{
			icon : 2,
			time : 2000
		});
		return;
	}
	$(".rack2-1 span").remove();
	$(".rack2-2 div").remove();
	for (var i = 1; i <= hight; i++) {
		array.unshift(i)
		s = hight - i + 1;
		
		$(".rack2-1").append("<span>"+ s +"</span><br/>");
		$(".rack2-2").append("<div id='num" + s + "' style='height: 25px;width: 100%;border-top:0.5px dashed black;'></div>");
	}
	/*$(".rack2-2").append("<c:forEach var='list' items='${" + array + "}' varStatus='i'><hr value='${list}' style='border: 0.5px dashed black;' /></c:forEach>")
	$(".rack2-1").append("<c:forEach var='list' items='${" + array + "}' varStatus='i'><span>${list}</span></c:forEach>")
	*/
	
}
//向屏柜中添加设备
function deviceAdd1(h) {
	var addDeviceName = $("#add_device_name").val();
	var loca_hight = parseInt($("#loca_hight").val());//从页面获取设备位置
	if (h) {//方法参数获取设备位置
		loca_hight = h;
	}
	if (isNaN(loca_hight)) {
		layer.msg("请输入数字",{
			icon : 2,
			time : 2000
		});
		return;
	}
	$("#num"+ loca_hight).css("border-bottom","1px solid black");
	$("#num"+ loca_hight).css("border-top","1px solid black");
	$("#num"+ loca_hight).css("background-color","red");
	//文字居中
	$("#num"+ loca_hight).css("text-align","center");
	$("#num"+ loca_hight).css("line-height","50%");
	$("#num"+ loca_hight).append("<span>" + addDeviceName + "</span>")
	
}
function deviceAdd2() {
	var deviceH = 2;//设备高度
	var addDeviceName = $("#add_device_name").val();
	var loca_hight1 = parseInt($("#loca_hight1").val());
	var loca_hight2 = parseInt($("#loca_hight2").val());
	
	if (isNaN(loca_hight1) || isNaN(loca_hight2)) {
		layer.msg("请输入数字",{
			icon : 2,
			time : 2000
		});
		return;
	}
	var max;
	var min;
	var h;
	if (loca_hight1 < loca_hight2) {
		max = loca_hight2;
		min = loca_hight1;
	} else if (loca_hight1 > loca_hight2){
		max = loca_hight1;
		min = loca_hight2;
	} else {
		h = loca_hight1;
		deviceAdd1(h);//如果两值相等则跳转至方法1
		return;
	}
	if (deviceH != (max - min + 1)) {
		layer.alert("屏柜高度为" + deviceH +"U",{
			icon : 2
		});
		return;
	}
	var array = new Array();
	for (var i = min; i <= max; i++) {
		array.push("#num" + i);
	}
	$(array.toString()).wrapAll("<div id='add_device"+loca_hight1+"' style = 'background-color: red;border-top: 1px solid black;border-bottom:1px solid black; text-align:center;line-height:50%;'></div>");
	$(array.toString()).remove();
	//文字居中
	$("#add_device"+ loca_hight1).append("<span>" + addDeviceName + "</span>")
	
}

function back() {//返回上一页面
	window.history.back(-1);
}

// 主操作类
function Main() {
	this.init = function() {

	};
}
