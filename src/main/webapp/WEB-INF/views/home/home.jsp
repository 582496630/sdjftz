<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
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
<title>首页</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="statics/bootstrap/css/bootstrap.min.css">
<!-- table style -->
<link rel="stylesheet"
	href="statics/plugins/bootstrap-table/bootstrap-editable.css">
<link rel="stylesheet"
	href="statics/plugins/bootstrap-table/bootstrap-table.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="statics/dist/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="statics/dist/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="statics/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="statics/dist/css/skins/all-skins.min.css">
<style type="text/css">
html {
	overflow: hidden;
}
</style>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="statics/plugins/ie9/html5shiv.min.js"></script>
    <script src="statics/plugins/ie9/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
	<div class="wrapper">
		<header class="main-header">
			<!-- Logo -->
			<a href="javascript:void(0)" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini">省调</span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>省调机房</b>精细化台账</span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li><a href="javascript:void(0);" data-toggle="push-menu"
							data-toggle="push-menu" role="button"><b>登陆时间：<span
									id="loginTime"></span></b> </a></li>
					</ul>
					<ul class="nav navbar-nav">
						<li><a href="javascript:void(0);" data-toggle="push-menu"
							data-toggle="push-menu" role="button"><b>欢迎您：<span
									id="RoleName"></span></b></a></li>
					</ul>
					<ul class="nav navbar-nav">
						<!-- User Account: style can be found in dropdown.less -->
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <img
								src="statics/dist/img/user2-160x160.jpg" class="user-image"
								alt="User Image"> <span class="hidden-xs"><span
									class="userName"></span></span>
						</a>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><img
									src="statics/dist/img/user2-160x160.jpg" class="img-circle"
									alt="User Image">
									<p>
										<span class="userName"></span>
									</p></li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<!-- <div class="pull-left">
                                    <a href="javascript:void(0)" class="btn btn-default btn-flat">Profile</a>
                                </div> -->
									<div class="pull-right">
										<a href="javascript:void(0)" class="btn btn-default btn-flat"
											onclick="updPassword()">修改密码</a> <a href="javascript:void(0)"
											class="btn btn-default btn-flat" onclick="signOut()">退出登陆</a>
									</div>
								</li>
							</ul></li>
						<!-- Control Sidebar Toggle Button -->
						<!--  <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li> -->
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- search form -->
				<!-- <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="Search...">
                    <span class="input-group-btn">
                <button type="button" name="search" id="search-btn" class="btn btn-flat" onclick="search_menu()"><i class="fa fa-search"></i>
                </button>
              </span>
                </div>
            </form> -->
				<!-- /.search form -->
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
				</ul>
			</section>
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper" id="content-wrapper"
			style="min-height: 421px;">
			<!--bootstrap tab风格 多标签页-->
			<div class="content-tabs">
				<button class="roll-nav roll-left tabLeft" onclick="scrollTabLeft()">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs menuTabs tab-ui-menu" id="tab-menu">
					<div class="page-tabs-content" style="margin-left: 0px;"></div>
				</nav>
				<button class="roll-nav roll-right tabRight"
					onclick="scrollTabRight()">
					<i class="fa fa-forward" style="margin-left: 3px;"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown tabClose" data-toggle="dropdown">
						页签操作<i class="fa fa-caret-down" style="padding-left: 3px;"></i>
					</button>
					<ul class="dropdown-menu dropdown-menu-right"
						style="min-width: 128px;">
						<li><a class="tabReload" href="javascript:refreshTab();">刷新当前</a></li>
						<li><a class="tabCloseCurrent"
							href="javascript:closeCurrentTab();">关闭当前</a></li>
						<li><a class="tabCloseAll"
							href="javascript:closeOtherTabs(true);">全部关闭</a></li>
						<li><a class="tabCloseOther"
							href="javascript:closeOtherTabs();">除此之外全部关闭</a></li>
					</ul>
				</div>
				<button class="roll-nav roll-right fullscreen"
					onclick="App.handleFullScreen()">
					<i class="fa fa-arrows-alt"></i>
				</button>
			</div>
			<div class="content-iframe " style="background-color: #ffffff;">
				<div class="tab-content " id="tab-content"></div>
			</div>
		</div>
		<!-- /.content-wrapper -->

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li><a href="#control-sidebar-home-tab" data-toggle="tab"><i
						class="fa fa-home"></i></a></li>
				<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
						class="fa fa-gears"></i></a></li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-user bg-yellow"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Frodo Updated His
										Profile</h4>

									<p>New phone +1(800)555-1234</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-envelope-o bg-light-blue"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Nora Joined Mailing
										List</h4>

									<p>nora@example.com</p>
								</div>
						</a></li>
						<li><a href="javascript:void(0)"> <i
								class="menu-icon fa fa-file-code-o bg-green"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Cron Job 254
										Executed</h4>

									<p>Execution time 5 seconds</p>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Custom Template Design <span
										class="label label-danger pull-right">70%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger"
										style="width: 70%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Update Resume <span class="label label-success pull-right">95%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-success"
										style="width: 95%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Laravel Integration <span
										class="label label-warning pull-right">50%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-warning"
										style="width: 50%"></div>
								</div>
						</a></li>
						<li><a href="javascript:void(0)">
								<h4 class="control-sidebar-subheading">
									Back End Framework <span class="label label-primary pull-right">68%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-primary"
										style="width: 68%"></div>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Report panel
								usage <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Some information about this general settings option</p>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Allow mail
								redirect <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Other sets of options are available</p>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Expose author
								name in posts <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Allow the user to show his name in blog posts</p>
						</div>
						<!-- /.form-group -->

						<h3 class="control-sidebar-heading">Chat Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Show me as
								online <input type="checkbox" class="pull-right" checked>
							</label>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Turn off
								notifications <input type="checkbox" class="pull-right">
							</label>
						</div>
						<!-- /.form-group -->

						<div class="form-group">
							<label class="control-sidebar-subheading"> Delete chat
								history <a href="javascript:void(0)" class="text-red pull-right"><i
									class="fa fa-trash-o"></i></a>
							</label>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- 隐藏域 -->
	<input id="userName" type="hidden" value="${user.userName }">
	<input id="userId" type="hidden" value="${user.id }">
	<input id="role_name" type="hidden" value="${role.rolename }">
	<input id="login_time" type="hidden" value="${loginTime }">
	<input id="level" type="hidden" value="">

	<div style="display: none;" id="updPassword" align="center">
		<form class="form-horizontal"
			style="margin-top: 15px; margin-right: 40px;">
			<div class="from-group col-md-12 col-sm-12">
				<label class="col-md-6 col-sm-6 control-label"><span
					style="color: red;">*</span>输入密码：</label>
				<div class="input-group">
					<input type="password" id="oldPwd"
						class="form-control col-md-6 col-sm-6" placeholder="输入密码" />
				</div>
			</div>
			<br /> <br />
			<div class="from-group col-md-12">
				<label class="col-md-6 col-sm-6 control-label"><span
					style="color: red;">*</span>输入新密码：</label>
				<div class="input-group">
					<input type="password" id="newPwd"
						class="form-control col-md-6 col-sm-6" placeholder="输入新密码" />
				</div>
			</div>
			<br /> <br />
			<div class="from-group col-md-12 col-sm-12">
				<label class="col-md-6 col-sm-6 control-label"><span
					style="color: red;">*</span>确认新密码：</label>
				<div class="input-group">
					<input type="password" id="checkPwd"
						class="form-control col-md-6 col-sm-6" placeholder="确认新密码" />
				</div>
			</div>
		</form>
	</div>

	<!-- ./wrapper -->
	<!-- jQuery 2.2.3 -->
	<script src="statics/plugins/jQuery/jquery-2.2.4.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="statics/bootstrap/js/bootstrap.js"></script>
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
	<!-- AdminLTE App -->
	<script src="statics/dist/js/app.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="statics/dist/js/demo.js"></script>
	<!--tabs-->
	<script src="statics/dist/js/app_iframe.js"></script>
	<!-- layer -->
	<script src="statics/plugins/layer/layer.js"></script>

	<script src="statics/js/home.js"></script>

</body>
</html>