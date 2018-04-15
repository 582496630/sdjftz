package com.youotech.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.youotech.entity.PortModel;
import com.youotech.entity.dto.PortDTO;

public interface PortModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PortModel record);

    int insertSelective(PortModel record);

    PortModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PortModel record);

    int updateByPrimaryKey(PortModel record);

	Integer insertAll(@Param("queryMap") Map<String, Object> map);
}