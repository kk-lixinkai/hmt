package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.OperLog;
import com.mybestcoding.hmt.model.OperLogWithBLOBs;

import java.util.List;

public interface OperLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OperLogWithBLOBs record);

    int insertSelective(OperLogWithBLOBs record);

    OperLogWithBLOBs selectByPrimaryKey(Integer id);

    List<OperLogWithBLOBs> selectAll();

    int updateByPrimaryKeySelective(OperLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OperLogWithBLOBs record);

    int updateByPrimaryKey(OperLog record);
}