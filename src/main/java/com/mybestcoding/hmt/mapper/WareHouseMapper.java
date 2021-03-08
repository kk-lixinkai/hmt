package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.WareHouse;

public interface WareHouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WareHouse record);

    int insertSelective(WareHouse record);

    WareHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WareHouse record);

    int updateByPrimaryKey(WareHouse record);
}