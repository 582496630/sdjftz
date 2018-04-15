package com.youotech.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.youotech.entity.CjModel;
import com.youotech.entity.DeviceModel;
import com.youotech.entity.StationModel;
import com.youotech.entity.SystemModel;
import com.youotech.entity.TypesetModel;
import com.youotech.entity.dto.AddDeviceDTO;
import com.youotech.entity.dto.DeviceModelDTO;
import com.youotech.entity.dto.TreeDTO;
import com.youotech.service.LedgerService;
import com.youotech.util.Constant;

@Controller
@RequestMapping("ledger")
public class LedgerController {

	@Autowired
	private LedgerService ledgerService;
	
	@RequestMapping("getDeviceLeftTree.do")
	@ResponseBody
	public Object getDeviceLeftTree(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<TreeDTO> list = ledgerService.getDeviceLeftTree();
		map.put("list", list);
		return map;
	}
	
	
	@RequestMapping("getLedgerTable.do")
	@ResponseBody
	public Map<String, Object> getLedgerTable(Integer pageNumer, Integer pageSize ,Integer device_fq, String device_name){
		Map<String, Object> map = new HashMap<String, Object>();
		if(pageNumer==null){
			pageNumer=1;
		}
		if(pageSize==null){
			pageSize=15;
		}
		map.put("fq", device_fq);
		map.put("name", device_name);
		PageInfo<DeviceModelDTO> info= ledgerService.getLedgerTable(pageNumer,pageSize,map);
		map.clear();
		map.put(Constant.DEFAULT_TOTAL, info.getTotal());
		map.put(Constant.DEFAULT_ROWS,info.getList());
		return map;
	}
	
	@RequestMapping("getSystemTable.do")
	@ResponseBody
	public Map<String, Object> getSystemTable(Integer pageNumber, Integer pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		if(pageNumber==null){
			pageNumber=1;
		}
		if(pageSize==null){
			pageSize=15;
		}
		PageInfo<SystemModel> info= ledgerService.getSystemTable(pageNumber,pageSize);
		map.clear();
		map.put(Constant.DEFAULT_TOTAL, info.getTotal());
		map.put(Constant.DEFAULT_ROWS,info.getList());
		return map;
	}
	
	@RequestMapping("insertSystemInfo.do")
	@ResponseBody
	public Map<String, Object> insertSystemInfo(String name){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer i = ledgerService.insertSystemInfo(name);
		if(i>0){
			map.put(Constant.DEFAULT_CODE, 200);
			map.put(Constant.DEFAULT_MSG, "操作成功！");
		}else{
			map.put(Constant.DEFAULT_CODE, 600);
			map.put(Constant.DEFAULT_MSG, "操作失败！");
		}
		return map;
	}
	
	@RequestMapping("updSystemInfo.do")
	@ResponseBody
	public Map<String, Object> updSystemInfo(Integer id,String name){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		Integer i = ledgerService.updSystemInfo(map);
		map.clear();
		if(i>0){
			map.put(Constant.DEFAULT_CODE, 200);
			map.put(Constant.DEFAULT_MSG, "操作成功！");
		}else{
			map.put(Constant.DEFAULT_CODE, 600);
			map.put(Constant.DEFAULT_MSG, "操作失败！");
		}
		return map;
	}
	
	@RequestMapping("delSystemInfo.do")
	@ResponseBody
	public Map<String, Object> delSystemInfo(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		String [] idArr = id.split(",");
		Integer i = ledgerService.delSystemInfo(idArr);
		map.clear();
		if(i>0){
			map.put(Constant.DEFAULT_CODE, 200);
			map.put(Constant.DEFAULT_MSG, "操作成功！");
		}else{
			map.put(Constant.DEFAULT_CODE, 600);
			map.put(Constant.DEFAULT_MSG, "操作失败！");
		}
		return map;
	}
	
	@RequestMapping("getSystemSelect.do")
	@ResponseBody
	public Map<String, Object> getSystemSelect(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<SystemModel> list = ledgerService.getSystemSelect();
		map.clear();
		map.put(Constant.DEFAULT_SUCCESS_MSG, list);
		return map;
	}
	
	@RequestMapping("getTypesetSelect.do")
	@ResponseBody
	public Map<String, Object> getTypesetSelect(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<TypesetModel> list = ledgerService.getTypesetSelect();
		map.clear();
		map.put(Constant.DEFAULT_SUCCESS_MSG, list);
		return map;
	}
	
	@RequestMapping("getStationSelect.do")
	@ResponseBody
	public Map<String, Object> getStationSelect(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<StationModel> list = ledgerService.getStationSelect();
		map.clear();
		map.put(Constant.DEFAULT_SUCCESS_MSG, list);
		return map;
	}
	
	@RequestMapping("getSccjSelect.do")
	@ResponseBody
	public Map<String, Object> getSccjSelect(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CjModel> list = ledgerService.getSccjSelect();
		map.clear();
		map.put(Constant.DEFAULT_SUCCESS_MSG, list);
		return map;
	}
	
	@RequestMapping("insertDeviceInfo.do")
	@ResponseBody
	public Map<String, Object> insertDeviceInfo(AddDeviceDTO device){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer i = ledgerService.insertDeviceInfo(device);
		map.clear();
		if(i>0){
			map.put(Constant.DEFAULT_CODE, 200);
			map.put(Constant.DEFAULT_MSG, "操作成功！");
		}else{
			map.put(Constant.DEFAULT_CODE, 600);
			map.put(Constant.DEFAULT_MSG, "操作失败！");
		}
		return map;
	}
	
	
}
