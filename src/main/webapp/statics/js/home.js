$(function() {
	// 获取实时更新数据每十秒执行一次
	var userName = $("#userName").val();
	var roleName = $('#role_name').val();
	var loginTime = $('#login_time').val();
	if (userName == "") {
		userName = "userName";
	}
	$('#RoleName').text(roleName);
	$('#loginTime').text(loginTime);
	$(".userName").text(userName);
	App.setbasePath("statics/");
	App.setGlobalImgPath("dist/img/");
	addTabs({
		id : '10008',
		title : '首页',
		close : false,
		url : 'jump.do?pageName=equipmentRecord/list',
		urlType : "relative"
	});
	App.fixIframeCotent();
	$.post('login/getMenuData.do', function(r) {
		$('.sidebar-menu').sidebarMenu({
			data : r.success
		});
	}, 'json');
});

/**
 * 本地搜索菜单
 */
function search_menu() {
	// 要搜索的值
	var text = $('input[name=q]').val();

	var $ul = $('.sidebar-menu');
	$ul.find("a.nav-link").each(function() {
		var $a = $(this).css("border", "");
		// 判断是否含有要搜索的字符串
		if ($a.children("span.menu-text").text().indexOf(text) >= 0) {
			// 如果a标签的父级是隐藏的就展开
			$ul = $a.parents("ul");
			if ($ul.is(":hidden")) {
				$a.parents("ul").prev().click();
			}
			// 点击该菜单
			$a.click().css("border", "1px solid");
		}
	});
}

// 退出登录
function signOut() {
	$.ajax({
		url : 'login/loginOut.do',
		type : 'post',
		data : {},
		dataType : 'json',
		success : function(data) {
			if (data.code == 200) {
				var logUrl = '';
				window.location.href = logUrl;
			} else {
				layer.alert('退出失败，请重新请退出！', {
					skin : 'layui-layer-lan',
					anim : 4
				// 动画类型
				});
			}
		},
		error : function(data) {
			layer.alert('出现未知异常，请重新请退出！', {
				skin : 'layui-layer-lan',
				anim : 4
			// 动画类型
			});
		}
	});
}

function updPassword() {
	$('#oldPwd').val('');
	$('#newPwd').val('');
	$('#checkPwd').val('');
	layer.open({
		type : 1,
		title : '修改密码',
		anim : 4,
		area : [ '350px', '250px' ],
		content : $("#updPassword"),
		btn : [ '修改', '关闭' ],
		yes : function() {
			var oldPwd = $('#oldPwd').val().trim();
			var newPwd = $('#newPwd').val().trim();
			var checkPwd = $('#checkPwd').val().trim();
			if (oldPwd == null || oldPwd == '') {
				layer.msg('密码不能为空！', {
					icon : 2,
					time : 2000
				});
				return;
			} else if (newPwd == null || newPwd == '') {
				layer.msg('新密码不能为空！', {
					icon : 2,
					time : 2000
				});
				return;
			} else if (checkPwd == null || checkPwd == '') {
				layer.msg('确认密码不能为空！', {
					icon : 2,
					time : 2000
				});
				return;
			} else if (newPwd != checkPwd) {
				layer.msg('新密码输入不一致！', {
					icon : 2,
					time : 2000
				});
				return;
			} else if (oldPwd.length < 6 || newPwd.length < 6
					|| checkPwd.length < 6) {
				layer.msg('密码长度需大于6！', {
					icon : 2,
					time : 2000
				});
				return;
			} else {
				$.ajax({
					url : 'userManager/updPasswordById.do',
					type : 'POST',
					data : {
						'id' : $('#userId').val(),
						'oldPwd' : oldPwd,
						'newPwd' : newPwd
					},
					dataType : 'json',
					success : function(data) {
						if (data.code == 200) {
							layer.msg(data.msg, {
								icon : 1,
								time : 2000
							}, function() {
								layer.closeAll();
							});
						} else if (data.code == 600) {
							layer.msg(data.msg, {
								icon : 2,
								time : 2000
							});
						}
					}
				});
			}

		}
	});
}
