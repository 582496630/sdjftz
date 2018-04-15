package com.youotech.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.youotech.entity.UserInfo;
import com.youotech.entity.dto.UserInfoDTO;
import com.youotech.service.UserInfoService;
import com.youotech.util.Constant;

@Controller
@RequestMapping("userManager")
public class UserManagerController {

	@Autowired
	private UserInfoService userInfoService;

	// 获取所有用户信息
	@RequestMapping("showUsers.do")
	@ResponseBody
	public Map<String, Object> getUserInfo(Integer pageNum, Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		if (pageNum == null || "".equals(pageNum)) {
			pageNum = 1;
		}
		if (pageSize == null || "".equals(pageSize)) {
			pageSize = 10;
		}
		PageInfo<UserInfoDTO> info = userInfoService.selectAllWithRole(pageNum, pageSize);
		map.put(Constant.DEFAULT_ROWS, info.getList());
		map.put(Constant.DEFAULT_TOTAL, info.getTotal());
		return map;
	}

	// 添加用户
	@RequestMapping("addUserInfo.do")
	@ResponseBody
	public Map<String, Object> addUserInfo(String username, String password, Integer deptId) {
		Map<String, Object> map = new HashMap<>();
		UserInfo userInfo = userInfoService.selectByUserName(username);
		if (userInfo != null) {
			map.put("code", 400);
			return map;
		}

		UserInfo info = new UserInfo();
		info.setUserName(username);
		info.setPassword(password);
		info.setDepid(deptId);
		info.setIsmanager(0);
		int i = userInfoService.addUserInfo(info);
		map.clear();
		if (i > 0) {
			map.put("code", 200);
		} else {
			map.put("code", 300);
		}
		return map;
	}

	// 修改用户
	@RequestMapping("updateUserInfo.do")
	@ResponseBody
	public Map<String, Object> updateUserInfo(Integer id, String userName, String password, Integer deptId) {
		Map<String, Object> map = new HashMap<>();
		if (id == null || id.equals("")) {
			map.put("code", 400);
			return map;
		}
		UserInfo info = new UserInfo();
		info.setId(id);
		info.setUserName(userName);
		info.setPassword(password);
		info.setDepid(deptId);
		info.setIsmanager(0);
		int i = userInfoService.updateUserInfo(info);
		map.clear();
		if (i > 0) {
			map.put("code", 200);
		} else {
			map.put("code", 300);
		}
		return map;
	}

	// 修改用户角色
	@RequestMapping("updateForRole.do")
	@ResponseBody
	public Map<String, Object> updateForRole(Integer id, Integer roleIds) {
		Map<String, Object> map = new HashMap<>();
		if (id == null || id.equals("")) {
			map.put("code", 400);
			return map;
		}
		map.put("id", id);
		map.put("roleId", roleIds);
		int i = userInfoService.updateForRole(map);
		map.clear();
		if (i > 0) {
			map.put("code", 200);
		} else {
			map.put("code", 300);
		}
		return map;
	}

	@RequestMapping("delUserInfo.do")
	@ResponseBody
	public Map<String, Object> delUserInfo(Integer userId) {
		Map<String, Object> map = new HashMap<>();
		if (userId == null || userId.equals("")) {
			map.put("code", 400);
			return map;
		}
		int i = userInfoService.delUserInfo(userId);
		map.clear();
		if (i > 0) {
			map.put("code", 200);
		} else {
			map.put("code", 300);
		}
		return map;
	}

	// 获取所有用户信息
	@RequestMapping("getUserFroDown.do")
	@ResponseBody
	public Map<String, Object> getUserFroDown(Integer roleId) {
		Map<String, Object> map = new HashMap<>();
		List<UserInfo> info = userInfoService.selectUserByDown(roleId);
		map.put(Constant.DEFAULT_SUCCESS_MSG, info);
		return map;
	}
	
	@RequestMapping("updPasswordById.do")
	@ResponseBody
	public Map<String, Object> updPasswordById(Integer id,String oldPwd,String newPwd) {
		Map<String, Object> map = new HashMap<>();
		map = userInfoService.updPasswordById(id,oldPwd,newPwd);
		return map;
	}
}
