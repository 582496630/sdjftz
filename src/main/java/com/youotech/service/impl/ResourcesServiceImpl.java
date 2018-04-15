package com.youotech.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youotech.dao.ResourcesInfoMapper;
import com.youotech.entity.Resources;
import com.youotech.service.ResourcesService;

@Service("ResourcesService")
@Transactional
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements ResourcesService {

	@Autowired
	private ResourcesInfoMapper resourcesInfoMapper;
	
	@Override
	public List<Resources> selectAll() {
		List<Resources> list = resourcesInfoMapper.selectAll();
		return list;
	}

	@Override
	public List<Integer> selectRRByRole(Integer roleId) {
		List<Integer> list = resourcesInfoMapper.selectRRByRole(roleId);
		return list;
	}

}
