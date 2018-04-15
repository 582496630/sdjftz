package com.youotech.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.youotech.entity.OperationLogInfo;

public interface OperationLogMapper {
	public int insert(OperationLogInfo info);
	
	List<OperationLogInfo> selectAll(@Param("map") Map<String, Object> map);
}
