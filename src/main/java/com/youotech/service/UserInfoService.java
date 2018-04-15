package com.youotech.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.youotech.entity.Resources;
import com.youotech.entity.RoleInfo;
import com.youotech.entity.UserInfo;
import com.youotech.entity.dto.UserInfoDTO;

public interface UserInfoService extends BaseService<UserInfo> {
	/**
	 * 通过姓名查询找用户是否存在
	 * 
	 * @param userName
	 * @return
	 */
	UserInfo selectByUserName(String userName);

	/**
	 * 通过用户名查找该用户对应的权限关键词
	 * 
	 * @param userName
	 * @return
	 */
	Set<String> selectForPermissions(String userName);

	/**
	 * 通过用户id查找该用户可以访问的页面
	 * 
	 * @param userName
	 * @return
	 */
	List<Resources> selectForResources(int id);

	/**
	 * 查询并展示所有用户信息
	 */
	PageInfo<UserInfoDTO> selectAllWithRole(int pageNum, int pageSize);

	int addUserInfo(UserInfo info);

	int updateUserInfo(UserInfo info);

	int updateForRole(Map<String, Object> map);

	int delUserInfo(Integer id);

	RoleInfo selectRoleByUser(Integer id);
	
	List<UserInfo> selectUserByDown(Integer roleId);

	Map<String, Object> updPasswordById(Integer id, String oldPwd, String newPwd);
}
