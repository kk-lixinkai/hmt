package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.ActionLog;

public interface ActionLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActionLog record);

    int insertSelective(ActionLog record);

    ActionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActionLog record);

    int updateByPrimaryKey(ActionLog record);
}