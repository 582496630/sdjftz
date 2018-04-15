package com.youotech.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.youotech.entity.Resources;
import com.youotech.entity.RoleInfo;
import com.youotech.entity.UserInfo;
import com.youotech.entity.dto.UserInfoDTO;

public interface UserInfoMapper extends MyMapper<UserInfo> {

	UserInfo selectByUserName(String userName);

	Set<String> selectForPermissions(String userName);

	List<Resources> selectForResources(int id);

	List<UserInfoDTO> selectAllWithRole();

	int updateUserInfo(UserInfo info);

	int updateForUserRole(@Param("map") Map<String, Object> map);

	int insertForUserRole(@Param("map") Map<String, Object> map);

	int selectUserRoleByUser(int userId);

	int delUserRoleByUserId(int userId);

	RoleInfo selectRoleByUser(int userId);

	List<UserInfo> selectUserByDown(Integer roleId);
}
