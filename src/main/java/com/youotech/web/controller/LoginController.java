package com.youotech.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youotech.entity.Resources;
import com.youotech.entity.RoleInfo;
import com.youotech.shiro.ShiroSessionUtils;
import com.youotech.shiro.utils.Const;
import com.youotech.entity.UserInfo;
import com.youotech.service.LoginService;
import com.youotech.service.UserInfoService;
import com.youotech.util.Common;
import com.youotech.util.Constant;

@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private LoginService loginService;

	@RequestMapping("login.do")
	@ResponseBody
	public Map<String, Object> checkLogin(String username, String password) {
		if (password != null) {
			password = Common.getFromBase64(password);
		}
		username = username.trim();
		password = password.trim();
		Map<String, Object> map = loginService.checkLogin(username,password);
		return map;
	}

	@RequestMapping("loginOut.do")
	@ResponseBody
	public Map<String, Object> loginOut(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put(Constant.DEFAULT_MSG, "退出成功！");
		return map;
	}

	@RequestMapping("getMenuData.do")
	@ResponseBody
	public Map<String, Object> getMenuData(HttpServletRequest request) {
		return loginService.getMenuDate();
	}

	@RequestMapping("home.do")
	public String getHome(Model model) {
		try {
			Date date = new Date();  
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String loginTime = df.format(date);
			UserInfo a = ShiroSessionUtils.getLoginAccount();
			RoleInfo b =  (RoleInfo) ShiroSessionUtils.getAttribute("roleInfo");
			ShiroSessionUtils.setAttribute(Const.SHIRO_PERMISSIONS, null);
			model.addAttribute("user", a);
			model.addAttribute("role", b);
			model.addAttribute("loginTime",loginTime);
			List<Resources> list = userInfoService.selectForResources(a.getId());
			model.addAttribute("resources", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "home/home";
	}

	@RequestMapping("demo.do")
	public String getDemo() {
		return "area_info/demo";
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("jump.do")
	public String openPage(String pageName) {
		return pageName;
	}

}
