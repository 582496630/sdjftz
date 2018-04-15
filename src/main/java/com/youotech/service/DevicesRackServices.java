package com.youotech.service;

import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;

import com.github.pagehelper.PageInfo;
import com.youotech.entity.DevicesRackModel;
import com.youotech.entity.EngineRoomModel;
import com.youotech.entity.dto.TreeDTO;

public interface DevicesRackServices {

	public PageInfo<DevicesRackModel> getDevicesRack(Integer pageNumber, Integer pageSize, Map<String, Object> map);

	public List<TreeDTO> getDeviceRackLeftTreeInfo();
	
	public List<TreeDTO> getEngineRoomTree();
	
	public EngineRoomModel getEngineRoomOne(Integer roomId);
	
	public Integer insertInfo(EngineRoomModel eModel);
	
	public Integer deleteEngineRooms(Long[] roomIds); 
	public List<DevicesRackModel> getDevicesRack2(Map<String, Object> map);
}
