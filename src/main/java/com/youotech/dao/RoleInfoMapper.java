package com.youotech.dao;

import java.util.List;
import java.util.Map;

import com.youotech.entity.RoleInfo;

public interface RoleInfoMapper extends MyMapper<RoleInfo> {

	int deleteRoleResources(Integer roleId);

	int insertRoleResources(List<Map<String, Object>> list);
}