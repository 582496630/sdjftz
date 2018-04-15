$(function() {
	// base64 加密
	// base = new Base64();
	// 初始化权限分配页面
	$('#show_rolename').val('');
	$('#show_dec').val('');
	initTree();
	// 初始化表格
	var interval = new Interval();
	interval.initInterval();
});

/**
 * 表格初始化 加载用户信息
 */
var Interval = function() {
	var t1 = new Object();
	t1.initInterval = function() {
		$('#userTable')
				.bootstrapTable(
						{
							url : 'role/selectRoleForPage.do',
							method : 'POST',
							dataType : 'json',
							contentType : "application/x-www-form-urlencoded",
							cache : false,
							toolbar : '#interval_toolbar',
							striped : true, // 是否显示行间隔色
							queryParams : t1.intervalQueryParams,
							queryParamsType : '',
							sidePagination : "server",
							paginationLoop : true,
							paginationPreText : '上一页',
							paginationNextText : '下一页',
							pageNumber : 1, // 初始化加载第一页，默认第一页
							pageSize : 10, // 每页的记录行数（*）
							columns : [
									{
										field : '',
										title : '序号',
										align : 'center',
										valign : 'middle',
										formatter : function(value, row, index) {
											return index + 1;
										}
									},
									{
										field : 'rolename',
										title : '角色名',
										align : 'center',
										valign : 'middle',
									},
									{
										field : 'dec',
										title : '描述',
										align : 'center',
										valign : 'middle',
									},
									{
										field : '',
										title : '操作',
										align : 'center',
										valign : 'middle',
										formatter : function(value, row, index) {
											var url = '';
											url += '<span>'
											url += '<a id="#add_fac" title="删除" javascript:void(0) role="button" mce_href="#"  class="glyphicon glyphicon-minus-sign" onclick="delRoleInfo(\''
													+ row.id
													+ '\')"></a>&nbsp;&nbsp;';
											url += '<a id="#add_fac" title="修改角色" javascript:void(0) role="button" mce_href="#"  class="glyphicon glyphicon-pencil" onclick="updRoleInfo(\''
													+ row.id
													+ '\',\''
													+ row.rolename
													+ '\',\''
													+ row.dec
													+ '\',\''
													+ row.authodec
													+ '\')"></a>&nbsp;&nbsp;';
											url += '<a id="#add_fac" title="设置权限" javascript:void(0) role="button" mce_href="#"  class="glyphicon glyphicon-wrench" onclick="grantAuth(\''
													+ row.id
													+ '\',\''
													+ row.rolename
													+ '\',\''
													+ row.dec + '\')"></a>';
											url += '</span>';
											return url;
										}
									} ],
							onClickRow : function(row) {
								grantAuth(row.id, row.rolename, row.dec);
							}
						});
	};
	/**
	 * 获取当前登录用户的id
	 */
	t1.intervalQueryParams = function(params) {
		var temp = {
			pageNumer : this.pageNumer,
			pageSize : this.pageSize,
			_ : $.now()
		};
		return temp;
	};
	return t1;
};

function delRoleInfo(id) {
	layer.open({
		type : 1,
		title : '确定要删除吗',
		anim : 4,
		area : [ '300px' ],
		content : '',
		btn : [ '确定', '取消' ],
		yes : function() {
			$.ajax({
				url : 'role/delRoleInfo.do',
				type : 'POST',
				dataType : 'json',
				data : {
					id : id
				},
				success : function(data) {
					if (data.code == 200) {
						layer.closeAll();
						layer.msg('保存成功', {
							icon : 1,
							time : 1000
						// 2秒关闭（如果不配置，默认是3秒）
						}, function() {
							$('#userTable').bootstrapTable('refresh');
						});
					} else {
						layer.msg('操作失败', {
							icon : 1,
							time : 1000
						// 2秒关闭（如果不配置，默认是3秒）
						});
					}
				},
				error : function(data) {
					layer.alert('操作异常，请联系管理员！', {
						skin : 'layui-layer-lan',
						anim : 4
					// 动画类型
					});
				}
			});
		}
	})
}

function updRoleInfo(id, rolename, dec, authodec) {
	initDisabled();
	$('#update_rolename').val(rolename);
	$('#update_dec').val(dec);
	$('#update_authodec').val(authodec);
	layer.open({
		type : 1,
		title : '修改角色',
		anim : 4,
		area : [ '550px', '450px' ],
		content : $("#updRoleInfo"),
		btn : [ '修改', '取消' ],
		yes : function() {
			var rolename = $('#update_rolename').val().trim();
			var dec = $('#update_dec').val().trim();
			var authodec = $('#update_authodec').val().trim();
			if (rolename == '' || rolename == null) {
				layer.alert('请输入角色名！', {
					skin : 'layui-layer-lan',
					anim : 4
				// 动画类型
				});
				return false;
				$('#update_rolename').focus();
			} else {
				$.ajax({
					url : 'role/updateRoleInfo.do',
					type : 'POST',
					dataType : 'json',
					data : {
						id : id,
						rolename : rolename,
						dec : dec,
						authodec : authodec
					},
					success : function(data) {
						if (data.code == 200) {
							layer.closeAll();
							layer.msg('修改成功', {
								icon : 1,
								time : 1000
							// 2秒关闭（如果不配置，默认是3秒）
							}, function() {
								$('#userTable').bootstrapTable('refresh');
							});
						} else {
							layer.msg('操作失败', {
								icon : 1,
								time : 1000
							// 2秒关闭（如果不配置，默认是3秒）
							});
						}
					},
					error : function(data) {
						layer.alert('操作异常，请联系管理员！', {
							skin : 'layui-layer-lan',
							anim : 4
						// 动画类型
						});
					}
				});
			}
		}
	})
}

function addRoleInfo() {
	initDisabled();
	layer.open({
		type : 1,
		title : '新增角色',
		anim : 4,
		area : [ '550px', '450px' ],
		content : $("#updRoleInfo"),
		btn : [ '保存', '取消' ],
		yes : function() {
			var rolename = $('#update_rolename').val().trim();
			var dec = $('#update_dec').val().trim();
			var authodec = $('#update_authodec').val().trim();
			if (rolename == '' || rolename == null) {
				layer.alert('请输入角色名！', {
					skin : 'layui-layer-lan',
					anim : 4
				// 动画类型
				});
				return false;
				$('#update_rolename').focus();
			} else {
				$.ajax({
					url : 'role/addRoleInfo.do',
					type : 'POST',
					dataType : 'json',
					data : {
						rolename : rolename,
						dec : dec,
						authodec : authodec
					},
					success : function(data) {
						if (data.code == 200) {
							layer.closeAll();
							layer.msg('保存成功', {
								icon : 1,
								time : 1000
							// 2秒关闭（如果不配置，默认是3秒）
							}, function() {
								$('#userTable').bootstrapTable('refresh');
							});
						} else {
							layer.msg('操作失败', {
								icon : 1,
								time : 1000
							// 2秒关闭（如果不配置，默认是3秒）
							});
						}
					},
					error : function(data) {
						layer.alert('操作异常，请联系管理员！', {
							skin : 'layui-layer-lan',
							anim : 4
						// 动画类型
						});
					}
				});
			}
		}
	})
}

//权限分配
var roleId = "";
function grantAuth(id, rolename, dec) {
	$('#show_rolename').val(rolename);
	$('#show_dec').val(dec);
	initTree(id);
	roleId = id;
}
function updAuth() {
	if (roleId == "") {
		layer.msg('请先选择角色！', {
			icon : 4,
			time : 2000
		}, function(index) {
			return;
		});
	} else {
		var $ztree = $.fn.zTree.getZTreeObj("treeDemo");
		var selectNodes = $ztree.getCheckedNodes(true);
		var nodes = [];
		for (var i = 0; i < selectNodes.length; i++) {
			var selectNode = selectNodes[i];
			nodes.push(selectNode.id);
		}
		nodes = JSON.stringify(nodes);
		nodes = nodes.substr(1, nodes.length - 2);
		$.getJSON("role/updateRoleResources.do", {
			"id" : roleId,
			"nodes" : nodes
		}, function(data) {
			if (data.code == 200) {
				layer.closeAll();
				layer.msg('操作成功', {
					icon : 1,
					time : 1000
				// 2秒关闭（如果不配置，默认是3秒）
				});
			} else {
				layer.msg('操作失败', {
					icon : 1,
					time : 3000
				// 2秒关闭（如果不配置，默认是3秒）
				});
			}
		});
	}
}

var initTree = function(id) {
	$.post('role/rolePermission.do?', {
		id : id
	}, function(result) {
		var t = $("#treeDemo");
		t = $.fn.zTree.init(t, setting, result.list);
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		treeObj.expandAll(treeObj);
	}, 'json');
}
var setting = {
	check : {
		enable : true
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
			pIdKey : "pid",
			rootPId : ""
		}
	},
	callback : {
		beforeClick : beforeClick,
		onClick : onClick
	}
};
function beforeClick(treeId, treeNode, clickFlag) {
	return (treeNode.click != false);
}
function onClick(event, treeId, treeNode, clickFlag) {
}
function initDisabled() {
	$('#update_rolename').val('');
	$('#update_dec').val('');
	$('#update_authodec').val('');
}
