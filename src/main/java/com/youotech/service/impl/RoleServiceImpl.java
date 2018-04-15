package com.youotech.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youotech.dao.RoleInfoMapper;
import com.youotech.entity.RoleInfo;
import com.youotech.service.LogInfoService;
import com.youotech.service.RoleService;
import com.youotech.util.LogAnnotation;

@Service("RoleService")
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleInfo> implements RoleService {

	@Autowired
	private RoleInfoMapper roleInfoMapper;
	@Autowired
	private LogInfoService logInfoService;

	@Override
	public List<RoleInfo> selectRoleAll() {
		List<RoleInfo> list = roleInfoMapper.selectAll();
		return list;
	}

	@Override
	public PageInfo<RoleInfo> selectRoleForPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<RoleInfo> list = roleInfoMapper.selectAll();
		PageInfo<RoleInfo> info = new PageInfo<>(list);
		return info;
	}

	@Override
	public int deleteRoleResources(Integer roleId) {
		int i = roleInfoMapper.deleteRoleResources(roleId);
		String crud = "批量删除权限";
		String data = "";
		logInfoService.saveLog(crud,data);
		return i;
	}

	@Override
	public int insertRoleResources(List<Map<String, Object>> list) {
		int i = roleInfoMapper.insertRoleResources(list);
		String crud = "批量新增权限";
		String data = "";
		logInfoService.saveLog(crud,data);
		return i;
	}

	@Override
	public int updateRoleInfo(RoleInfo roleInfo) {

		int i = roleInfoMapper.updateByPrimaryKeySelective(roleInfo);
		String crud = "修改角色";
		String data = "";
		logInfoService.saveLog(crud,data);
		return i;
	}
	
	@Override
	public int delRoleInfo(Integer id) {
		int i = roleInfoMapper.deleteByPrimaryKey(id);
		String crud = "删除角色";
		String data = "";
		logInfoService.saveLog(crud,data);
		return i;
	}

	@Override
	public int addRoleInfo(RoleInfo roleInfo) {
		int i = roleInfoMapper.insertSelective(roleInfo);
		String crud = "新增角色";
		String data = "";
		logInfoService.saveLog(crud,data);
		return i;
	}

}
