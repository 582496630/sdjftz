package com.youotech.dao;

import java.util.List;

import com.youotech.entity.TypesetModel;

public interface TypesetModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TypesetModel record);

    int insertSelective(TypesetModel record);

    TypesetModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TypesetModel record);

    int updateByPrimaryKey(TypesetModel record);

	List<TypesetModel> getMenuList();

	List<TypesetModel> getList();
}