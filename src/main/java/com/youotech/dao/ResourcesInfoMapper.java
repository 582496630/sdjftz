package com.youotech.dao;

import java.util.List;
import java.util.Map;

import com.youotech.entity.Resources;

public interface ResourcesInfoMapper extends MyMapper<Resources> {
	List<Integer> selectRRByRole(Integer id);

}