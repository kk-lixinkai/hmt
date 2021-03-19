package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.WareHouse;

import java.util.List;

public interface WareHouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WareHouse record);

    int insertSelective(WareHouse record);

    WareHouse selectByPrimaryKey(Integer id);

    List<WareHouse> selectAll();

    int updateByPrimaryKeySelective(WareHouse record);

    int updateByPrimaryKey(WareHouse record);
}