$(function() {
	if (top.location != self.location) {
		//当当前窗口url（self.location）连接和父窗口url（top.location）不同时，将当前窗口给父窗口
		top.location = self.location;
	}
	$('#btn').click(login);

	// 回车提交事件
	$("#UserName").keydown(function(event) {
		if (event.keyCode == "13") {// keyCode=13是回车键
			login();
		}
	});
	$("#Password").keydown(function(event) {
		if (event.keyCode == "13") {// keyCode=13是回车键
			login();
		}
	});
});

function login() {
	// base64 加密
	var base = new Base64();
	var username = $('input[type="text"]').val();
	var password = base.encode($('input[type="password"]').val());
	if (username == '' || username == null) {
		layer.alert('请输入用户名！', {
			skin : 'layui-layer-lan',
			anim : 4
		// 动画类型
		});
		return false;
		$('input[type="text"]').focus();
	} else if (password == '' || password == null) {
		layer.alert('请输入密码！', {
			skin : 'layui-layer-lan',
			anim : 4
		// 动画类型
		});
		return false;
		$('input[type="password"]').focus();
	} else {
		$.ajax({
			url : 'login/login.do',
			type : 'post',
			data : {
				username : username,
				password : password,
			},
			dataType : 'json',
			success : function(data) {
				if (data.code == 200) {
					var logUrl = 'login/home.do';
					window.location.href = logUrl;
				} else if (data.code == 600) {
					layer.alert(data.msg, {
						skin : 'layui-layer-lan',
						anim : 4
					// 动画类型
					});
				} else {
					layer.alert('用户名或密码错误，请重新请输入！', {
						skin : 'layui-layer-lan',
						anim : 4
					// 动画类型
					});
				}
			},
			error : function(data) {
				layer.alert('出现未知异常，请重新请输入！', {
					skin : 'layui-layer-lan',
					anim : 4
				// 动画类型
				});
			}
		});
	}
}