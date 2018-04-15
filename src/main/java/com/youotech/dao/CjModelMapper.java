package com.youotech.dao;

import java.util.List;

import com.youotech.entity.CjModel;

public interface CjModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CjModel record);

    int insertSelective(CjModel record);

    CjModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CjModel record);

    int updateByPrimaryKey(CjModel record);

	List<CjModel> getList();
}