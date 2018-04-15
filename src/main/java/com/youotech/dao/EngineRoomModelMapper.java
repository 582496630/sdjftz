package com.youotech.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youotech.entity.EngineRoomModel;

public interface EngineRoomModelMapper {
	
	public List<EngineRoomModel> getEngineRooms();
	public EngineRoomModel getEngineRoomOne(@Param("roomId") Integer roomId);
	public Integer insertInfo(EngineRoomModel eModel);
	
	public Integer deleteEngineRooms(@Param("roomIds") Long[] roomIds);
}