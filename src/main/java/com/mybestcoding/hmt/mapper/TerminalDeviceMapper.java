package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.TerminalDevice;

public interface TerminalDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TerminalDevice record);

    int insertSelective(TerminalDevice record);

    TerminalDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TerminalDevice record);

    int updateByPrimaryKey(TerminalDevice record);
}