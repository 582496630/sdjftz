package com.youotech.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.youotech.entity.DevicesRackModel;
import com.youotech.entity.dto.TypeDTO;

public interface DevicesRackModelMapper {

	public List<DevicesRackModel> getDevicesRack(@Param("map") Map<String, Object> map);

	public List<TypeDTO> getDeviceRackLeftTreeInfo();
}