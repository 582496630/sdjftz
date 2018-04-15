package com.youotech.dao;

import java.util.List;

import com.youotech.entity.StationModel;

public interface StationModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StationModel record);

    int insertSelective(StationModel record);

    StationModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StationModel record);

    int updateByPrimaryKey(StationModel record);

	List<StationModel> getList();
}