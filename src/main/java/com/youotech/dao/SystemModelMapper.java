package com.youotech.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.youotech.entity.SystemModel;

public interface SystemModelMapper {

	List<SystemModel> getList();

	Integer saveObject(String name);

	Integer updObject(@Param("queryMap") Map<String, Object> map);

	Integer delObject(Long parseLong);

    
}