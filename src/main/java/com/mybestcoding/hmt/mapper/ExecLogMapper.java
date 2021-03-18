package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.ExecLog;
import com.mybestcoding.hmt.model.ExecLogWithBLOBs;

import java.util.List;

public interface ExecLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExecLogWithBLOBs record);

    int insertSelective(ExecLogWithBLOBs record);

    ExecLogWithBLOBs selectByPrimaryKey(Integer id);

    List<ExecLogWithBLOBs> selectAll();

    int updateByPrimaryKeySelective(ExecLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ExecLogWithBLOBs record);

    int updateByPrimaryKey(ExecLog record);
}