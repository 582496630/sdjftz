var itemData = {};
var main = null;
$(function() {
	main = new Main();
	main.init();

	initTree();
	initSelectpicker();

	var sysOperate = new system_operate();
	main.sysOperate = sysOperate;

	var interval = new Interval();
	interval.initInterval();
	var interval2 = new Interval2();
	interval2.initInterval();

});
var initTree = function(id) {
	$.post('ledger/getDeviceLeftTree.do?', function(result) {
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
		beforeClick : beforeClick,
		onClick : onClick
	}
};
function beforeClick(treeId, treeNode) {
	return (treeNode.click != false);
}
function onClick(event, treeId, treeNode) {
	if (treeNode.flag == 0) {
		$('#equip_div').css('display', 'none');
		$('#system_div').css('display', 'block');
		$('#systemTable').bootstrapTable('refresh');
	} else if (treeNode.flag == 1 || treeNode.flag == 2) {
		$('#equip_div').css('display', 'block');
		$('#system_div').css('display', 'none');
	}
}
function initSelectpicker() {
	$('#device_fq').selectpicker({
		style : 'btn-info',
		width : 160, // 宽度
		size : 10,
	});
}

var Interval = function() {
	var t1 = new Object();
	t1.initInterval = function() {
		$('#ledgerTable').bootstrapTable({
			url : 'ledger/getLedgerTable.do',
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
			columns : [ {
				field : '',
				title : '序号',
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					return index + 1;
				}
			}, {
				field : 'name',
				title : '设备名称',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'typesetName',
				title : '设备类型',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'sbxh',
				title : '设备型号',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'xlh',
				title : '设备序列号',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'ip',
				title : '网口',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'sbzrrName',
				title : '设备责任人',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'sbwhrName',
				title : '设备维护人',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'sccjName',
				title : '生产厂家',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'ywdj',
				title : '运维等级',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'pgId',
				title : '屏柜',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'systemName',
				title : '系统名称',
				align : 'center',
				valign : 'middle',
			}, {
				field : 'fq',
				title : '分区',
				align : 'center',
				valign : 'middle',
				formatter : function(value,row,index){
					var msg ='';
					if(value==1) msg='Ⅰ区';
					else if(value==2) msg='Ⅱ区';
					else if(value==3) msg='Ⅲ区';
					return msg;
				}
			}, {
				field : 'remark',
				title : '备注',
				align : 'center',
				valign : 'middle',
			} ],
			onClickRow : function(row) {
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
			device_fq : $('#device_fq').val(),
			device_name : $('#device_name').val(),
			_ : $.now()
		};
		return temp;
	};
	return t1;
};

var Interval2 = function() {
	var t2 = new Object();
	t2.initInterval = function() {
		$('#systemTable')
				.bootstrapTable(
						{
							url : 'ledger/getSystemTable.do',
							method : 'POST',
							dataType : 'json',
							contentType : "application/x-www-form-urlencoded",
							cache : false,
							toolbar : '',
							striped : true, // 是否显示行间隔色
							queryParams : t2.intervalQueryParams,
							queryParamsType : '',
							sidePagination : "server",
							paginationLoop : true,
							paginationPreText : '上一页',
							paginationNextText : '下一页',
							pageNumber : 1, // 初始化加载第一页，默认第一页
							pageSize : 15, // 每页的记录行数（*）
							columns : [
									{
										field : '',
										title : '序号',
										align : 'center',
										width : 40,
										valign : 'middle',
										formatter : function(value, row, index) {
											return index + 1;
										}
									},
									{
										field : '',
										title : '勾选',
										align : 'center',
										width : 40,
										valign : 'middle',
										formatter : function(value, row, index) {
											var url = '';
											url += '<input name="system_check" type="checkbox" value='
													+ row.id
													+ ','
													+ row.name
													+ ' />';
											return url;
										}
									}, {
										field : 'name',
										title : '系统名称',
										align : 'left',
										valign : 'middle',
									} ],
							onClickRow : function(row) {
							}
						});
	};
	t2.intervalQueryParams = function(params) {
		var temp = {
			pageNumber : this.pageNumber,
			pageSize : this.pageSize,
			_ : $.now()
		};
		return temp;
	};
	return t2;
};

function addEquip() {
	initForm();
	var index = layer.open({
		type : 1,
		title : '添加设备',
		anim : 4,
		area : [ '780px', '740px' ],
		content : $("#add_equip"),
		btn : [ '确认', '关闭' ],
		yes : function() {
			var portArr = JSON.stringify(scene_port.port.ports);
			console.log(portArr);
			$.ajax({
				url : 'ledger/insertDeviceInfo.do',
				type : 'POST',
				data : {
					systemId : $('#systemId').selectpicker('val'),
					fq : $('#fq').selectpicker('val'),
					typesetId : $('#typesetId').selectpicker('val'),
					name : $('#sb_name').val(),
					sbzrrName : $('#sbzrrName').val(),
					sbwhrName : $('#sbwhrName').val(),
					xlh : $('#xlh').val(),
					sbxh : $('#sbxh').val(),
					sccj : $('#sccj').selectpicker('val'),
					ywdj : $('#ywdj').val(),
					pgId : $('#pgId').val(),
					facId : $('#facId').selectpicker('val'),
					remark : $('#remark').val(),
					portArr : portArr,
					scfj : $('#scfj').val(),
					snmpRead : $('#snmpRead').val(),
					snmpPort : $('#snmpPort').val()
				},
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						layer.msg(data.msg, {
							icon : 1,
							time : 1500
						}, function() {
							$('#ledgerTable').bootstrapTable('refresh');
							layer.close(index);
						});
					} else {
						layer.msg(data.msg, {
							icon : 2,
							time : 1500
						});
					}
				}
			});
		}
	});
}

function openPort() {
	var index = layer.open({
		type : 1,
		title : '添加网口信息',
		anim : 4,
		area : [ '380px', '360px' ],
		content : $("#port_view"),
		btn : [ '确认', '关闭' ],
		yes : function() {
			showAddPort();
			layer.close(index);
		}
	});
}

function initForm() {
	$("select[name='form_select']").selectpicker({
		width : 195, // 宽度
		size : 10,
	});
	$("select[name='form_select']").selectpicker('refresh');

	getInitSelect('ledger/getSystemSelect.do', '#systemId');
	getInitSelect('ledger/getTypesetSelect.do', '#typesetId');
	getInitSelect('ledger/getStationSelect.do', '#facId');
	getInitSelect('ledger/getSccjSelect.do', '#sccj');
}

function getInitSelect(url, id) {
	var id_option = id + ' option';
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			if (data) {
				datas = data.success;
				$(id_option).remove();
				$.each(datas, function(i) {
					$(id).append(
							"<option value=" + datas[i].id + ">"
									+ datas[i].name + "</option>");
				});
				$(id).selectpicker('refresh');
			}
			;
		}
	});
}

function initFileUpload() {
	$("#file-up").fileinput({
		resizeImage : true,
		maxImageWidth : 200,
		maxImageHeight : 200,
		resizePreference : 'width',
		language : 'zh', // 设置语言
		uploadUrl : "",
		uploadAsync : true,
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ],// 接收的文件后缀
		showUpload : true, // 是否显示上传按钮
		showCaption : true,// 是否显示标题
		browseClass : "btn btn-success", // 按钮样式
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
		maxFileCount : 3,
		msgFilesTooMany : "选择图片超过了最大数量",
		maxFileSize : 2000,
	});
}

function system_operate() {
	this.add = function() {
		$('#system_name').val('');
		var index = layer.open({
			type : 1,
			title : '添加系统',
			anim : 4,
			area : [ '380px', '160px' ],
			content : $("#sys_add"),
			btn : [ '添加', '取消' ],
			yes : function() {
				var name = $('#system_name').val().trim();
				if (name == null || name == "") {
					layer.msg('系统名称不能为空！', {
						icon : 2,
						time : 2000
					})
				} else {
					$.ajax({
						url : 'ledger/insertSystemInfo.do',
						type : 'POST',
						dataType : 'json',
						data : {
							name : name
						},
						success : function(data) {
							if (data.code == 200) {
								layer.msg(data.msg, {
									icon : 1,
									time : 1500
								}, function() {
									system_refresh();
									layer.close(index);
								});
							} else {
								layer.msg(data.msg, {
									icon : 2,
									time : 1500
								});
							}
						}
					});
				}
			}
		});
	}
	this.upd = function() {
		var obj = $("input[name='system_check']:checked");
		if (obj.length != 1) {
			layer.msg('请勾选一条数据进行修改！', {
				icon : 3,
				time : 1500
			});
			return;
		}
		var id, name;
		$("input[name='system_check']:checked").each(function() {
			var arr = $(this).val().split(',');
			id = arr[0];
			name = arr[1];
		});
		$('#system_name').val(name);
		var index = layer.open({
			type : 1,
			title : '修改系统',
			anim : 4,
			area : [ '380px', '160px' ],
			content : $("#sys_add"),
			btn : [ '修改', '取消' ],
			yes : function() {
				var name = $('#system_name').val().trim();
				if (name == null || name == "") {
					layer.msg('系统名称不能为空！', {
						icon : 2,
						time : 2000
					})
				} else {
					$.ajax({
						url : 'ledger/updSystemInfo.do',
						type : 'POST',
						dataType : 'json',
						data : {
							id : id,
							name : name
						},
						success : function(data) {
							if (data.code == 200) {
								layer.msg(data.msg, {
									icon : 1,
									time : 1500
								}, function() {
									system_refresh();
									layer.close(index);
								});
							} else {
								layer.msg(data.msg, {
									icon : 2,
									time : 1500
								});
							}
						}
					});
				}
			}
		});
	}
	this.del = function() {
		var id = "";
		var num = 0;
		$("input[name='system_check']:checked").each(function() {
			if ($(this) != null) {
				var arr = $(this).val().split(',');
				id += arr[0] + ",";
				num += 1;
			}
		});
		if (id == "") {
			layer.msg('请勾选至少一条数据进行删除！', {
				icon : 2,
				time : 2000
			})
			return;
		} else {
			var index = layer.confirm('你确认删除这' + num + '条数据吗？',
					function(index) {
						$.ajax({
							url : 'ledger/delSystemInfo.do',
							type : 'POST',
							dataType : 'json',
							data : {
								id : id,
							},
							success : function(data) {
								if (data.code == 200) {
									layer.msg(data.msg, {
										icon : 1,
										time : 1500
									}, function() {
										system_refresh();
										layer.close(index);
									});
								} else {
									layer.msg(data.msg, {
										icon : 2,
										time : 1500
									});
								}
							}
						});

					});
		}
	}
	this.refresh = function() {
		initTree();
		$('#systemTable').bootstrapTable('refresh');
	}
}

function system_add() {
	main.sysOperate.add();
}

function system_upd() {
	main.sysOperate.upd();
}

function system_del() {
	main.sysOperate.del();
}

function system_refresh() {
	main.sysOperate.refresh();
}

$(function() {// 初始化canvas
	var canvas_2 = document.getElementById('canvas_port');
	var stage_1 = new JTopo.Stage(canvas_2); // 创建一个舞台对象
	scene_port = new JTopo.Scene(stage_1); // 创建一个场景对象
	scene_port.mode = "select";
	scene_port.background = "statics/images/linkBg_port.gif";
	scene_port.clear = function() {
		while (this.childs.length > 0) {
			scene_port.remove(this.childs[0]);
		}
		scene_port.stage.repaint();

	};
	scene_port.port = {
		ports : [],
		xx : 0,
		yy : 0,
		ww : canvas_2.width,
		hh : canvas_2.height,
		dealNode : function(ele) {
			ele.addEventListener("mouseup", function(event) {
				if (event.button == 2) {
					hideTooltip();

					$("#portMenu_jianshi,#portMenu_quxiaojianshi").hide();
					if (this.entity && this.entity.star == 1) {
						$("#portMenu_quxiaojianshi").show();
					} else {
						$("#portMenu_jianshi").show();
					}

					$("#portMenu").menu('show', {
						left : event.pageX - 5,
						top : event.pageY - 5
					}).data("curEle", this);

				}
			});
			ele.dbclick(function() {

			});

		}
	};
});

function Port(name, ip, mac, pm, star) {
	this.name = name;
	this.ip = ip;
	this.mac = mac;
	this.pm = pm;
	this.star = star;
}
// 网口
function Dlg_port() {
	this.dlg = $("#dlg_port");
	this.url = "ledger";
	this.init = function() {

	};
	this.mustSelectOne = function() {
		return true;
	}
	this.getSelectedRecord = function() {
		var ele = $("#portMenu").data("curEle");
		return ele.entity;
	};
	this.showAdd = function() {
		var name = $('#port_name').val();
		var ip = $('#port_ip').val();
		var mac = $('#port_mac').val();
		var pm = "pm01";
		var star = 1;
		var portObj = new Port(name, ip, mac, pm, star);
		var port = scene_port.port;
		port.ports.push(portObj);
		var node = new JTopo.Node();
		node.setImage("statics/images/bz_up_offline.png");
		var i = port.ports.length - 1;
		node.x = 60 + 60 * i;
		node.y = port.hh / 2 - 15;
		node.dragable = false;
		node.text = port.ports[i].name;
		scene_port.add(node);
		node.addEventListener("mouseover", function() {
			var msg = '<span>名称：' + port.ports[i].name + '</span><br>';
			msg += '<span>IP地址：' + port.ports[i].ip + '</span><br>';
			msg += '<span>MAC地址：' + port.ports[i].mac + '</span><br>';
			msg += '<span>平面：' + port.ports[i].pm + '</span><br>';
			msg += '<span>状态：' + port.ports[i].star + '</span><br>';
			$("#tooltip").html(msg).css({
				left : -20 + node.width - 5,
				top : 420 + +node.height / 2 - 5
			}).fadeIn(400);
		});
		node.addEventListener("mouseout", function() {
			$("#tooltip").hide();
		});
		node.addEventListener("mouseup", function(event) {
			if (event.button == 2) {
				alert("右键");
			}
		});
	}
	this.showModify = function() {

	}
	this.submit = function() {

	}

}

$(function() {
	var dlg_port = new Dlg_port();
	dlg_port.init();
	main.dlg_port = dlg_port;

})
function showAddPort() {
	main.dlg_port.showAdd();
}
function showModifyPort() {
	main.dlg_port.showModify();
}
function showRemovePort() {
	$.messager.confirm("确认", "确认要删除该网卡吗?", function(r) {
		if (r) {
			var curEle = $("#portMenu").data('curEle');
			for (var i = 0; i < scene_port.port.ports.length; i++) {
				var port = scene_port.port.ports[i];
				if (port.name == curEle.entity.name) {
					scene_port.port.ports.splice(i, 1);
					break;
				}
			}
			scene_port.clear();
			scene_updatePort(scene_port);
		}
	})
}

// 主操作类
function Main() {
	this.init = function() {

	};
}
