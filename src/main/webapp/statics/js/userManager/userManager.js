$(function() {
	// base64 加密
	base = new Base64();
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
							url : 'userManager/showUsers.do',
							method : 'POST',
							dataType : 'json',
							contentType : "application/x-www-form-urlencoded",
							cache : false,
							toolbar : '#user_toolbar',
							striped : true, // 是否显示行间隔色
							queryParams : t1.intervalQueryParams,
							queryParamsType : '',
							sidePagination : "server",
							paginationLoop : true,
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
										field : 'userName',
										title : '用户名',
										align : 'center',
										valign : 'middle',
									},
									{
										field : 'roleName',
										title : '角色名',
										align : 'center',
										valign : 'middle',
									},
									{
										field : 'deptName',
										title : '部门名',
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
											url += '<a id="#add_fac" title="删除" javascript:void(0) role="button" mce_href="#"  class="glyphicon glyphicon-minus-sign" onclick="delUserInfo(\''
													+ row.userId
													+ '\')"></a>&nbsp;&nbsp;';
											url += '<a id="#add_fac" title="修改基本信息" javascript:void(0) role="button" mce_href="#"  class="glyphicon glyphicon-pencil" onclick="updUserInfo(\''
													+ row.userId
													+ '\',\''
													+ row.userName
													+ '\',\''
													+ row.deptId
													+ '\')"></a>&nbsp;&nbsp;';
											url += '<a id="#add_fac" title="修改角色信息" javascript:void(0) role="button" mce_href="#"  class="glyphicon glyphicon-user" onclick="grantRole(\''
													+ row.userId
													+ '\',\''
													+ row.userName
													+ '\',\''
													+ row.roleId + '\')"></a>';
											url += '</span>';
											return url;
										}
									} ]
						});
	};
	/**
	 * 获取当前登录用户的id
	 */
	t1.intervalQueryParams = function(params) {
		var temp = {
			pageNum : this.pageNumber,
			pageSize : this.pageSize
		};
		return temp;
	};
	return t1;
};

function addUserInfo() {
	initialForm();
	layer.open({
		type : 1,
		title : '添加用户',
		anim : 4,
		area : [ '450px', '400px' ],
		content : $("#add_UserInfo_Form"),
		btn : [ '保存', '取消' ],
		yes : function() {
			var userName = $('#add_user').val();
			var deptId = $('#add_dept').selectpicker('val');
			var password = $('#add_password').val();
			if (userName == null || userName == '') {
				layer.alert('请输入用户名！', {
					skin : 'layui-layer-lan',
					anim : 4
				});
				return false;
				$('#add_userName').focus();
			} else if (password == null || password == '') {
				layer.alert('请输入密码！', {
					skin : 'layui-layer-lan',
					anim : 4
				});
				return false;
				$('#add_password').focus();
			} else if (deptId == 0) {
				layer.alert('请选择所属部门！', {
					skin : 'layui-layer-lan',
					anim : 4
				});
				return false;
			} else if (password.length < 6) {
				layer.alert('密码至少为6位！', {
					skin : 'layui-layer-lan',
					anim : 4
				});
				return false;
				$('#add_password').focus();
			} else {
				$.ajax({
					url : 'userManager/addUserInfo.do',
					type : 'POST',
					dataType : 'json',
					data : {
						username : userName,
						password : password,
						deptId : deptId
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
						} else if (data.code == 400) {
							layer.msg('用户名已存在！', {
								icon : 1,
								time : 3000
							// 2秒关闭（如果不配置，默认是3秒）
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

function initialForm() {
	$('#add_user').val('');
	$('#add_password').val('');
	$('#password_label').text('输入密码:');
	$('#add_password').removeAttr("placeholder");
	initDept();
}

function initDept() {
	$.ajax({// 初始下拉菜单
		url : 'department/getDept.do',
		type : 'POST',
		dataType : 'json',
		async : false,
		success : function(data) {
			if (data) {
				datas = data.success;
				$("#add_d option").remove();
				$("#add_d").append("<option value=0>请选择</option>");
				$.each(datas, function(i) {
					$("#add_d").append(
							"<option value=" + datas[i].id + ">"
									+ datas[i].name + "</option>");
				});
				$("#add_dept").selectpicker({
					style : 'btn-info',// 显示样式
					width : 195, // 宽度
				});
				$("#add_dept").selectpicker('refresh');
			}
			;
		},
		error : function(data) {
			layer.alert('加载区域出现异常，请联系管理员！', {
				skin : 'layui-layer-lan',
				anim : 4
			// 动画类型
			});
		}
	});
}

function grantRole(id, username, roleId) {
	initRoleSelect(id, username, roleId);
	layer.open({
		type : 1,
		title : '角色分配',
		anim : 4,
		area : [ '400px', '350px' ],
		content : $("#grantRole"),
		btn : [ '确定', '取消' ],
		yes : function() {
			var roleIds = $('#grant_role').selectpicker('val');
			if (roleIds == 0) {
				layer.alert('请选择分配角色！', {
					skin : 'layui-layer-lan',
					anim : 4
				// 动画类型
				});
				return false;
				$('#grant_role').focus();
			} else {
				$.ajax({
					url : 'userManager/updateForRole.do',
					type : 'POST',
					dataType : 'json',
					data : {
						id : id,
						roleIds : roleIds
					},
					success : function(data) {
						if (data.code == 200) {
							layer.msg('保存成功', {
								icon : 1,
								time : 1000
							// 2秒关闭（如果不配置，默认是3秒）
							}, function() {
								$('#userTable').bootstrapTable('refresh');
								layer.closeAll();
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
function initRoleSelect(id, username, roleId) {
	$("#grant_username").val(username);
	$.ajax({
		url : 'role/selectRoleAll.do',
		type : 'POST',
		data : {},
		dataType : 'json',
		success : function(data) {
			if (data) {
				roles = data.success;
				$("#aa option").remove();
				$("#aa").append("<option value=0>请选择:</option>");
				$.each(roles, function(i) {
					if (roles[i].id == roleId) {
						$("#aa").append(
								"<option selected='selected' value="
										+ roles[i].id + ">" + roles[i].rolename
										+ "</option>");
					} else
						$("#aa").append(
								"<option value=" + roles[i].id + ">"
										+ roles[i].rolename + "</option>");
				});
				// 初始化下拉菜单
				$("#grant_role").selectpicker({
					style : 'btn-info',// 显示样式
					width : 195, // 宽度
				});
				$("#grant_role").selectpicker('refresh');
			}
			;
		},
		error : function(data) {
			layer.alert('加载区域出现异常，请联系管理员！', {
				skin : 'layui-layer-lan',
				anim : 4
			// 动画类型
			});
		}
	});
}

function initDisabled() {
	$("#operate_disabled").selectpicker({
		style : 'btn-info',// 显示样式
		width : 195, // 宽度
	});
	$("#operate_disabled").selectpicker('refresh');
}

function updUserInfo(id, username, deptId) {
	initialForm();
	$('#add_user').val(username);
	$('#add_dept').selectpicker('val', deptId);
	$('#add_dept').selectpicker('refresh');
	$('#password_label').text('输入新密码:');
	$('#add_password').val();
	$('#add_password').attr("placeholder", "如不修改密码，可不填");
	layer.open({
		type : 1,
		title : '修改用户基本信息',
		anim : 4,
		area : [ '450px', '400px' ],
		content : $("#add_UserInfo_Form"),
		btn : [ '保存', '取消' ],
		yes : function() {
			var userName = $('#add_user').val();
			var deptId = $('#add_dept').val();
			var password = $('#add_password').val();
			if (userName == null || userName == '') {
				layer.alert('请输入用户名！', {
					skin : 'layui-layer-lan',
					anim : 4
				});
				return false;
				$('#add_userName').focus();
			} else if (deptId == 0) {
				layer.alert('请选择所属部门！', {
					skin : 'layui-layer-lan',
					anim : 4
				});
				return false;
			} else if (password != null && password != ''
					&& password.length < 6) {
				layer.alert('密码至少为6位！', {
					skin : 'layui-layer-lan',
					anim : 4
				});
				return false;
				$('#add_password').focus();
			} else {
				$.ajax({
					url : 'userManager/updateUserInfo.do',
					type : 'POST',
					dataType : 'json',
					data : {
						id : id,
						userName : userName,
						password : password,
						deptId : deptId
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

function delUserInfo(userId) {
	layer.confirm('确认删除该用户？',{icon:2,anim : 4},function(index){
		$.ajax({
			url : 'userManager/delUserInfo.do',
			type : 'POST',
			dataType : 'json',
			data : {
				userId : userId
			},
			success : function(data) {
				if (data.code == 200) {
					layer.closeAll();
					layer.msg('删除成功', {
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
	});
	
}
