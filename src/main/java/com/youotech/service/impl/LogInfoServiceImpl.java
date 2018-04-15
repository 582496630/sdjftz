package com.youotech.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youotech.dao.OperationLogMapper;
import com.youotech.entity.OperationLogInfo;
import com.youotech.entity.UserInfo;
import com.youotech.service.LogInfoService;
import com.youotech.shiro.ShiroSessionUtils;

@Service("LogInfoService")
@Transactional
public class LogInfoServiceImpl implements LogInfoService{
	@Autowired
	private OperationLogMapper operationLogMapper;
	
	@Override
	public Integer saveLogInfo(OperationLogInfo info) {
		int row = operationLogMapper.insert(info);
		return row;
	}

	@Override
	public PageInfo<OperationLogInfo> selectForPage(Integer pageNum, Integer pageSize, Map<String, Object> map) {
		PageHelper.startPage(pageNum, pageSize);
		List<OperationLogInfo> info = operationLogMapper.selectAll(map);
		PageInfo<OperationLogInfo> pageInfo = new PageInfo<>(info);
		return pageInfo;
	}

	@Override
	public void saveLog(String crud, String data) {
		String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
		OperationLogInfo LogInfo = new OperationLogInfo();
		UserInfo user = ShiroSessionUtils.getLoginAccount();
		if (user != null) {
			LogInfo.setUserId(user.getId());
			LogInfo.setUserName(user.getUserName());
		}
		LogInfo.setLogDate(time);
		LogInfo.setLogCrud(crud);
		LogInfo.setLogData(data);
		saveLogInfo(LogInfo);
	}

}
