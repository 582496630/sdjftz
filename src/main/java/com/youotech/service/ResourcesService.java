package com.youotech.service;

import java.util.List;
import java.util.Map;

import com.youotech.entity.Resources;

public interface ResourcesService extends BaseService<Resources> {

	List<Resources> selectAll();

	List<Integer> selectRRByRole(Integer roleId);

}
