package com.youotech.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youotech.entity.OperationLogInfo;
import com.youotech.entity.Resources;
import com.youotech.entity.UserInfo;
import com.youotech.entity.dto.MenuDTO;
import com.youotech.filter.WebContext;
import com.youotech.service.LogInfoService;
import com.youotech.service.LoginService;
import com.youotech.service.UserInfoService;
import com.youotech.shiro.ShiroSessionUtils;
import com.youotech.util.Constant;
import com.youotech.util.LogAnnotation;

import net.sf.json.JSONArray;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService{
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private LogInfoService logInfoService;
	
	@Override
	public Map<String, Object> checkLogin(String username, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		HttpServletRequest request = WebContext.currentRequest();
		String rememberme = request.getParameter("rememberme");
		if (StringUtils.isNotBlank(rememberme)) {
			token.setRememberMe(true);
		} else {
			token.setRememberMe(false);
		}
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			if (subject.isAuthenticated()) {
				
				String data = "用户名："+username;
				String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
				OperationLogInfo info = new OperationLogInfo();
				UserInfo user = ShiroSessionUtils.getLoginAccount();
				if (user != null) {
					info.setUserId(user.getId());
				}
				info.setUserName(username);
				info.setLogDate(time);
				info.setLogCrud("用户登陆操作");
				info.setLogData(data);
				logInfoService.saveLogInfo(info);
				
				map.put("status", 0);
				map.put("code", 200);
				map.put("msg", "登录成功！");
				return map;
			}
		} catch (UnknownAccountException uae) {
			map.put("code", 600);
			map.put("msg", "账号不存在!");
		}catch (IncorrectCredentialsException ice) {
			// 密码不正确
			token.clear();
			map.put("code", 600);
			map.put("msg", "用户名或密码错误");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 600);
			map.put("msg", "未知错误,请联系管理员.");
		}
		return map;
	}

	@Override
	public Map<String, Object> getMenuDate() {
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo a = ShiroSessionUtils.getLoginAccount();
		List<Resources> list = userInfoService.selectForResources(a.getId());
		List<MenuDTO> menulist = new ArrayList<>();
		for (Resources rlist : list) {
			if (rlist.getPid() == 0) {
				MenuDTO data = new MenuDTO(rlist.getId(), rlist.getName(), rlist.getIcon(), rlist.getDescription(),
						rlist.getUrl(), null, true);
				menulist.add(data);
			}
			if (rlist.getPid() == 9000) {
				MenuDTO data = new MenuDTO(rlist.getId(), rlist.getName(), rlist.getIcon(), rlist.getDescription(),
						rlist.getUrl(), null, false);
				List<MenuDTO> cmenulist = new ArrayList<>();
				for (Resources clist : list) {
					int ab = clist.getPid();
					int b = rlist.getId();
					if (ab == b) {
						MenuDTO cdata = new MenuDTO(clist.getId(), clist.getName(), clist.getIcon(),
								clist.getDescription(), clist.getUrl(), null, false);
						cmenulist.add(cdata);
					}
				}
				data.setChildren(cmenulist);
				menulist.add(data);
			}
		}
		JSONArray menuData = JSONArray.fromObject(menulist);
		map.put(Constant.DEFAULT_SUCCESS_MSG, menuData);
		return map;
	}

}
