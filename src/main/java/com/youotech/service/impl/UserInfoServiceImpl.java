package com.youotech.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youotech.dao.UserInfoMapper;
import com.youotech.entity.Resources;
import com.youotech.entity.RoleInfo;
import com.youotech.entity.UserInfo;
import com.youotech.entity.dto.UserInfoDTO;
import com.youotech.service.LogInfoService;
import com.youotech.service.UserInfoService;
import com.youotech.util.Constant;

@Service("UserInfoService")
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private LogInfoService logInfoService;

	/**
	 * 通过姓名查询找用户是否存在
	 */
	@Override
	public UserInfo selectByUserName(String userName) {
		UserInfo userInfo = userInfoMapper.selectByUserName(userName);
		return userInfo;
	}

	/**
	 * 通过用户名查找该用户对应的权限关键词
	 */
	@Override
	public Set<String> selectForPermissions(String userName) {
		Set<String> set = userInfoMapper.selectForPermissions(userName);
		return set;
	}

	/**
	 * 通过用户id查找该用户可以访问的页面
	 */
	@Override
	public List<Resources> selectForResources(int id) {
		List<Resources> list = userInfoMapper.selectForResources(id);
		return list;
	}

	@Override
	public PageInfo<UserInfoDTO> selectAllWithRole(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserInfoDTO> list = userInfoMapper.selectAllWithRole();
		PageInfo<UserInfoDTO> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public int addUserInfo(UserInfo info) {
		info.setcTime(new Date());
		info.setmTime(new Date());
		info.setDescription("");
		info.setPassword(new Md5Hash(info.getPassword()).toString());
		int i = userInfoMapper.insert(info);
		
		String crud = "新增用户";
		String data = "";
		logInfoService.saveLog(crud,data);
		
		return i;
	}

	@Override
	public int updateUserInfo(UserInfo info) {
		info.setmTime(new Date());
		info.setPassword(new Md5Hash(info.getPassword()).toString());
		int i = userInfoMapper.updateUserInfo(info);
		
		String crud = "修改用户";
		String data = "";
		logInfoService.saveLog(crud,data);
		return i;
	}

	@Override
	public int updateForRole(Map<String, Object> map) {
		int i = 0;
		if (userInfoMapper.selectUserRoleByUser(((int) map.get("id"))) > 0) {
			i = userInfoMapper.updateForUserRole(map);
		} else {
			i = userInfoMapper.insertForUserRole(map);
		}
		String crud = "更新用户关联角色";
		String data = "";
		logInfoService.saveLog(crud,data);
		return i;
	}
	
	@Override
	public int delUserInfo(Integer id) {// 删除用户及和用户关联的角色
		// 删除user
		int m = userInfoMapper.deleteByPrimaryKey(id);

		// 删除user-role
		if (m > 0) {
			userInfoMapper.delUserRoleByUserId(id);
		}
		String crud = "删除用户及和用户关联的角色";
		String data = "";
		logInfoService.saveLog(crud,data);
		return m;
	}

	@Override
	public RoleInfo selectRoleByUser(Integer id) {
		RoleInfo info = userInfoMapper.selectRoleByUser(id);
		return info;
	}

	@Override
	public List<UserInfo> selectUserByDown(Integer roleId) {
		List<UserInfo> list = userInfoMapper.selectUserByDown(roleId);
		return list;
	}
	
	@Override
	public Map<String, Object> updPasswordById(Integer id, String oldPwd, String newPwd) {
		Map<String, Object> map = new HashMap<>();
		UserInfo user = userInfoMapper.selectByPrimaryKey(id);
		oldPwd = new Md5Hash(oldPwd).toString();
		newPwd = new Md5Hash(newPwd).toString();
		if (!oldPwd.equals(user.getPassword())) {
			map.put(Constant.DEFAULT_CODE, 600);
			map.put(Constant.DEFAULT_MSG, "密码输入错误！请重新输入");
			return map;
		}
		user.setPassword(newPwd);
		int i = userInfoMapper.updateUserInfo(user);
		if (i > 0) {
			map.put(Constant.DEFAULT_CODE, 200);
			map.put(Constant.DEFAULT_MSG, "密码修改成功！");
			String crud = "修改用户密码";
			String data = "";
			logInfoService.saveLog(crud,data);
		} else {
			map.put(Constant.DEFAULT_CODE, 600);
			map.put(Constant.DEFAULT_MSG, "密码修改操作失败！");
		}
		return map;
	}
}
