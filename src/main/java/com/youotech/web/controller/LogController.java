package com.youotech.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.youotech.entity.OperationLogInfo;
import com.youotech.service.LogInfoService;
import com.youotech.util.Constant;

@Controller
@RequestMapping("Log")
public class LogController {

	@Autowired
	private LogInfoService logInfoService;

	@RequestMapping("selectForPage.do")
	@ResponseBody
	public Map<String, Object> selectForPage(Integer pageNum, Integer pageSize, String startTime, String endTime) {
		Map<String, Object> map = new HashMap<>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageInfo<OperationLogInfo> info = logInfoService.selectForPage(pageNum, pageSize, map);
		map.clear();
		map.put(Constant.DEFAULT_TOTAL, info.getTotal());
		map.put(Constant.DEFAULT_ROWS, info.getList());
		return map;
	}

}
