package com.youotech.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.youotech.entity.OperationLogInfo;

public interface LogInfoService {

	public Integer saveLogInfo(OperationLogInfo info);

	public PageInfo<OperationLogInfo> selectForPage(Integer pageNum, Integer pageSize, Map<String, Object> map);
	
	public void saveLog(String crud,String data);
}
