package com.youotech.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.youotech.entity.DeviceModel;
import com.youotech.entity.SystemModel;
import com.youotech.entity.dto.AddDeviceDTO;
import com.youotech.entity.dto.DeviceModelDTO;
import com.youotech.entity.dto.TypeDTO;

public interface DeviceModelMapper {

	List<TypeDTO> getMenuList();

	List<DeviceModelDTO> getLedgerTable(@Param("queryMap") Map<String, Object> map);

	Integer insertInfo(DeviceModel device);

	List<DeviceModel> getAllDevice();
}