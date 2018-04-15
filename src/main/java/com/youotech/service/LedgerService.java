package com.youotech.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.youotech.entity.CjModel;
import com.youotech.entity.DeviceModel;
import com.youotech.entity.StationModel;
import com.youotech.entity.SystemModel;
import com.youotech.entity.TypesetModel;
import com.youotech.entity.dto.AddDeviceDTO;
import com.youotech.entity.dto.DeviceModelDTO;
import com.youotech.entity.dto.TreeDTO;

public interface LedgerService {

	List<TreeDTO> getDeviceLeftTree();

	PageInfo<DeviceModelDTO> getLedgerTable(Integer pageNumber, Integer pageSize, Map<String, Object> map);

	PageInfo<SystemModel> getSystemTable(Integer pageNumber, Integer pageSize);

	Integer insertSystemInfo(String name);

	Integer updSystemInfo(Map<String, Object> map);

	Integer delSystemInfo(String[] idArr);

	List<SystemModel> getSystemSelect();

	List<TypesetModel> getTypesetSelect();

	List<StationModel> getStationSelect();

	List<CjModel> getSccjSelect();

	Integer insertDeviceInfo(AddDeviceDTO device);


}
