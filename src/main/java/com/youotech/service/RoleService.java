package com.youotech.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.youotech.entity.RoleInfo;

public interface RoleService extends BaseService<RoleInfo> {
	List<RoleInfo> selectRoleAll();

	PageInfo<RoleInfo> selectRoleForPage(Integer pageNum, Integer pageSize);

	int deleteRoleResources(Integer roleId);

	int insertRoleResources(List<Map<String, Object>> list);

	int addRoleInfo(RoleInfo roleInfo);

	int updateRoleInfo(RoleInfo roleInfo);

	int delRoleInfo(Integer id);
}
