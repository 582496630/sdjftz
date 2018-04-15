package com.youotech.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youotech.dao.CjModelMapper;
import com.youotech.dao.DeviceModelMapper;
import com.youotech.dao.PortModelMapper;
import com.youotech.dao.StationModelMapper;
import com.youotech.dao.SystemModelMapper;
import com.youotech.dao.TypesetModelMapper;
import com.youotech.entity.CjModel;
import com.youotech.entity.DeviceModel;
import com.youotech.entity.StationModel;
import com.youotech.entity.SystemModel;
import com.youotech.entity.TypesetModel;
import com.youotech.entity.dto.AddDeviceDTO;
import com.youotech.entity.dto.DeviceModelDTO;
import com.youotech.entity.dto.PortDTO;
import com.youotech.entity.dto.TreeDTO;
import com.youotech.entity.dto.TypeDTO;
import com.youotech.service.LedgerService;

import net.sf.json.JSONArray;

@Service("ledgerService")
public class LedgerServiceImpl implements LedgerService{
	@Autowired
	private SystemModelMapper systemModelDao;
	@Autowired
	private TypesetModelMapper typesetModelDao;
	@Autowired
	private DeviceModelMapper deviceModelDao;
	@Autowired
	private StationModelMapper stationModelDao;
	@Autowired
	private CjModelMapper cjModelDao;
	@Autowired
	private PortModelMapper portModelDao;
	
	@Override
	public List<TreeDTO> getDeviceLeftTree() {
		
        List<SystemModel> systemList = systemModelDao.getList();
        List<TypeDTO> typeList = deviceModelDao.getMenuList();
        List<TreeDTO> list = new ArrayList<>();
        TreeDTO tree = new TreeDTO("省调机房精细化台账",null,(long) 9999,null,0);
        list.add(tree);
        for (SystemModel syslist : systemList) {
			TreeDTO ztree = new TreeDTO();
			ztree.setId(syslist.getId());
			ztree.setpId((long) 9999);
			ztree.setName(syslist.getName());
			ztree.setFlag(1);
			list.add(ztree);
		}
        for (TypeDTO devlist : typeList) {
			TreeDTO zztree = new TreeDTO();
			zztree.setId(devlist.getTypesetId());
			zztree.setpId(devlist.getSystemId());
			zztree.setName(devlist.getTypesetName());
//			zztree.setIcon(devlist.getTypesetIcon());
			zztree.setFlag(2);
			list.add(zztree);
		}
        return list;
	}

	@Override
	public PageInfo<DeviceModelDTO> getLedgerTable(Integer pageNumber, Integer pageSize, Map<String, Object> map) {
		PageHelper.startPage(pageNumber, pageSize);
		List<DeviceModelDTO> list = deviceModelDao.getLedgerTable(map);
		PageInfo<DeviceModelDTO> info = new PageInfo<DeviceModelDTO>(list);
		return info;
	}

	@Override
	public PageInfo<SystemModel> getSystemTable(Integer pageNumber, Integer pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<SystemModel> list = systemModelDao.getList();
		PageInfo<SystemModel> info = new PageInfo<SystemModel>(list);
		return info;
	}

	@Override
	public Integer insertSystemInfo(String name) {
		Integer i = systemModelDao.saveObject(name);
		return i;
	}

	@Override
	public Integer updSystemInfo(Map<String, Object> map) {
		Integer i = systemModelDao.updObject(map);
		return i;
	}

	@Override
	public Integer delSystemInfo(String[] idArr) {
		Integer j = 0;
		for (int i = 0; i < idArr.length; i++) {
			j = systemModelDao.delObject(Long.parseLong(idArr[i]));
		}
		return j;
	}

	@Override
	public List<SystemModel> getSystemSelect() {
		return systemModelDao.getList();
	}

	@Override
	public List<TypesetModel> getTypesetSelect() {
		return typesetModelDao.getList();
	}

	@Override
	public List<StationModel> getStationSelect() {
		return stationModelDao.getList();
	}

	@Override
	public List<CjModel> getSccjSelect() {
		return cjModelDao.getList();
	}

	@Override
	public Integer insertDeviceInfo(AddDeviceDTO device) {
		List<PortDTO> portList = new ArrayList<PortDTO>();
		JSONArray arr = JSONArray.fromObject(device.getPortArr());
		portList = JSONArray.toList(arr,PortDTO.class);
		
		DeviceModel model = new DeviceModel();
		model.setSystemId(device.getSystemId());
		model.setFq(device.getFq());
		model.setTypesetId(device.getTypesetId());
		model.setName(device.getName());
		model.setSbzrrName(device.getSbzrrName());
		model.setSbwhrName(device.getSbwhrName());
		model.setXlh(device.getXlh());
		model.setSbxh(device.getSbxh());
		model.setSccj(device.getSccj());
		model.setYwdj(device.getYwdj());
		model.setPgId(device.getPgId());
		model.setFacId(device.getFacId());
		model.setRemark(device.getRemark());
		model.setScfj(device.getScfj());
		model.setSnmpRead(device.getSnmpRead());
		model.setSnmpPort(device.getSnmpPort());
		model.setDel(new BigDecimal(1));
		Integer i = deviceModelDao.insertInfo(model);
		if(portList.size()>0){
			Long deviceId = model.getId();
			Map<String, Object> map = new HashMap<>();
			map.put("portList", portList);
			map.put("deviceId", deviceId);
			Integer j = portModelDao.insertAll(map);
			map.clear();
		}
		return i;
	}

	
	
	
}
