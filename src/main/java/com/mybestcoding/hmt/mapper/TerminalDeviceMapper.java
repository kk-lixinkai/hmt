package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.TerminalDevice;

import java.util.List;

public interface TerminalDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteTdWithWareHouse(Integer wid);

    int insert(TerminalDevice record);

    int insertSelective(TerminalDevice record);

    int insertTdToWareHouse(Integer did, Integer wid);

    TerminalDevice selectByPrimaryKey(Integer id);

    List<TerminalDevice> selectByWarehouseId(Integer id);

    int updateByPrimaryKeySelective(TerminalDevice record);

    int updateByPrimaryKey(TerminalDevice record);
}