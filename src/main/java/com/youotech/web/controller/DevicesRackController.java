package com.youotech.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.youotech.entity.DevicesRackModel;
import com.youotech.entity.EngineRoomModel;
import com.youotech.entity.StationModel;
import com.youotech.entity.dto.TreeDTO;
import com.youotech.service.DevicesRackServices;
import com.youotech.service.LedgerService;
import com.youotech.util.Constant;

/**
 * 屏柜
 * @author zhouyou
 * 2018-4-8 13:30:59
 */
@Controller
@RequestMapping("rack")
public class DevicesRackController {

	@Autowired
	private DevicesRackServices rackServices;
	
	@Autowired
	private LedgerService ledgerService;
	
	@RequestMapping("devicesRack.do")
	@ResponseBody
	public Object getDeviceLeftTree(Integer pageNumer, Integer pageSize, String name,
			String code, Integer fq, Integer roomId, String floor){
		Map<String, Object> map = new HashMap<String, Object>();
		if(pageNumer==null){
			pageNumer=1;
		}
		if(pageSize==null){
			pageSize=15;
		}
		map.put("name", name);
		map.put("code", code);
		map.put("fq", fq);
		map.put("roomId", roomId);
		map.put("floor", floor);
		PageInfo<DevicesRackModel> info= rackServices.getDevicesRack(pageNumer,pageSize,map);
		map.clear();
		map.put(Constant.DEFAULT_TOTAL, info.getTotal());
		map.put(Constant.DEFAULT_ROWS,info.getList());
		return map;
	}
	@RequestMapping("devicesRackInfo.do")
	public String  getDevicesRackInfo(Integer id,Model model){
		List<StationModel> list = ledgerService.getStationSelect();
		model.addAttribute("stationList", list);
		return "devicesRack/devicesRackInfo";
	}
	
	@RequestMapping("deviceRackLeftTreeInfo.do")
	@ResponseBody
	public Object  getDeviceRackLeftTreeInfo(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<TreeDTO> list = rackServices.getDeviceRackLeftTreeInfo();
		map.put("list", list);
		return map;
	}
	
	@RequestMapping("engineRoomTree.do")
	@ResponseBody
	public Object  getEngineRoomTree(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<TreeDTO> list = rackServices.getEngineRoomTree();
		map.put("list", list);
		return map;
	}
	@RequestMapping("engineRoomOne.do")
	@ResponseBody
	public Object  getEngineRoomOne(Integer roomId){
		EngineRoomModel engineRoomModel = rackServices.getEngineRoomOne(roomId);
		return engineRoomModel;
	}
	
	@RequestMapping("deledtEngineRoom.do")
	@ResponseBody
	public Object  deledtEngineRooms(@RequestParam("roomIds[]") Long[] roomIds){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (roomIds == null || roomIds.length < 1) {
			map.put(Constant.DEFAULT_CODE, 600);
			map.put(Constant.DEFAULT_MSG, "请选择需要删除的数据！");
			return map;
		}
		List<DevicesRackModel> list;
		for (Long long1 : roomIds) {
			map.put("roomId", long1);
			list = rackServices.getDevicesRack2(map);
			if ( list.size() >= 1) {
				map.put(Constant.DEFAULT_CODE, 600);
				map.put(Constant.DEFAULT_MSG, "只可以删除无屏柜的机房！");
				return map;
			}
		}
		
		Integer i = rackServices.deleteEngineRooms(roomIds);
		if(i>0){
			map.put(Constant.DEFAULT_MSG, "操作成功！");
			map.put(Constant.DEFAULT_CODE, 200);
		}else{
			map.put(Constant.DEFAULT_CODE, 600);
			map.put(Constant.DEFAULT_MSG, "操作失败！");
		}
		return map;
	}
	
	@RequestMapping("addEngineRoom.do")
	@ResponseBody
	public Map<String, Object> addEngineRoom(EngineRoomModel eModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer i = rackServices.insertInfo(eModel);
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
