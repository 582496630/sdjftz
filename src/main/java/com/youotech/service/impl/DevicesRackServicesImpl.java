package com.youotech.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youotech.dao.DeviceModelMapper;
import com.youotech.dao.DevicesRackModelMapper;
import com.youotech.dao.EngineRoomModelMapper;
import com.youotech.entity.DeviceModel;
import com.youotech.entity.DevicesRackModel;
import com.youotech.entity.EngineRoomModel;
import com.youotech.entity.dto.TreeDTO;
import com.youotech.entity.dto.TypeDTO;
import com.youotech.service.DevicesRackServices;

@Service("DevicesRackServices")
@Transactional
public class DevicesRackServicesImpl implements DevicesRackServices {

	@Autowired
	private DevicesRackModelMapper rackModelDao;
	
	@Autowired
	private DeviceModelMapper deviceModelDao;
	
	@Autowired
	private EngineRoomModelMapper engineRoomDao;
	
	/**
	 * 获取所有屏柜
	 * 分页
	 */
	@Override
	public PageInfo<DevicesRackModel> getDevicesRack(Integer pageNumber, Integer pageSize, Map<String, Object> map){
		PageHelper.startPage(pageNumber, pageSize);
		List<DevicesRackModel> list = rackModelDao.getDevicesRack(map);
		PageInfo<DevicesRackModel> info = new PageInfo<DevicesRackModel>(list);
		return info;
	}
	/**
	 * 获取所有屏柜
	 * 不分页
	 */
	@Override
	public List<DevicesRackModel> getDevicesRack2(Map<String, Object> map){
		List<DevicesRackModel> list = rackModelDao.getDevicesRack(map);
		return list;
	}
	
	/**
	 * 获取设备树型结构
	 */
	@Override
	public List<TreeDTO> getDeviceRackLeftTreeInfo(){
	        List<TypeDTO> typeList = rackModelDao.getDeviceRackLeftTreeInfo();
	        List<DeviceModel> deviceList = deviceModelDao.getAllDevice();
	        List<TreeDTO> devicesList = new ArrayList<>();
	        List<String> list = new ArrayList<>();
	        list.add("分区");
	        list.add("Ⅰ区");
	        list.add("Ⅱ区");
	        list.add("Ⅲ区");
	        list.add("Ⅳ区");
	        for (long i = 1 ; i <= 4 ;i++) {
				TreeDTO ztree = new TreeDTO();
				ztree.setId(i);
				ztree.setpId((long) 9999);
				ztree.setName(list.get((int)i));
				ztree.setFlag(0);
				devicesList.add(ztree);
			}
	        long i = 10001;
	        //记录二级节点ID
	        Map<String, Long> map = new HashMap<String,Long>();
	        for (TypeDTO devlist : typeList) {
	        	String key = devlist.getDeviceFQ()+"-"+devlist.getTypesetId();
	        	map.put(key, i);
				TreeDTO zztree = new TreeDTO();
				zztree.setId(i);
				zztree.setpId(devlist.getDeviceFQ());
				zztree.setName(devlist.getTypesetName());
				zztree.setFlag(1);
				devicesList.add(zztree);
				i++;
			}
	        
	        for (DeviceModel deviceModel : deviceList) {
	        	String key = deviceModel.getFq()+"-"+deviceModel.getTypesetId();
	        	long pId = map.get(key);
	        	TreeDTO zztree = new TreeDTO();
				zztree.setId(deviceModel.getId());
				zztree.setpId(pId);
				zztree.setName(deviceModel.getName());
				zztree.setFlag(2);
				devicesList.add(zztree);
			}
	        return devicesList;
	}
	
	/**
	 * 获取机房树型结构
	 */
	@Override
	public List<TreeDTO> getEngineRoomTree(){
		List<TreeDTO> engineRoomTree = new ArrayList<>();
		List<EngineRoomModel> engineRoomList = engineRoomDao.getEngineRooms();
		TreeDTO zztree1;
		TreeDTO zztree2;
		long i = 100000;//地区id，如果设备ID超过100000会造成ztree多分枝的id相等，视图产生混乱
		List<String> addressList = new ArrayList<String>();
		Map<String, Long> map = new HashMap<String, Long>();
		for (EngineRoomModel eModel : engineRoomList) {
			if (!StringUtils.isBlank(eModel.getAddress())) {
				String address = (eModel.getAddress().substring(0, 2));
				boolean isNewAddress = false;
				if (addressList.contains(address)) {
					isNewAddress = false;
					i = map.get(address);
				} else {
					isNewAddress = true;
				}
				if (isNewAddress) {
					zztree1 = new TreeDTO();
					address = eModel.getAddress().substring(0, 2);
					System.out.println(address);
					i++;
					zztree1.setId(i);
					zztree1.setpId(99999L);
					zztree1.setName(eModel.getAddress());
					zztree1.setFlag(0);
					addressList.add(address);
					map.put(address, i);
					engineRoomTree.add(zztree1);
				}
			}
			zztree2 = new TreeDTO();
			zztree2.setId(eModel.getId());
			zztree2.setpId(i);
			zztree2.setName(eModel.getRoomName());
			zztree2.setFlag(1);
			engineRoomTree.add(zztree2);
		}
		return engineRoomTree;
	}
	
	/**
	 * 插入机房信息
	 */
	@Override
	public Integer insertInfo(EngineRoomModel eModel) {
		
		Integer s = engineRoomDao.insertInfo(eModel);
		return s;
	}
	
	/**
	 * 获取一个机房的信息
	 */
	@Override
	public EngineRoomModel getEngineRoomOne(Integer roomId) {
		return engineRoomDao.getEngineRoomOne(roomId);
	}
	
	/**
	 * 删除机房数据
	 */
	@Override
	public Integer deleteEngineRooms(Long[] roomIds) {
		Integer s = engineRoomDao.deleteEngineRooms(roomIds);
		return s;
	}
	
}
