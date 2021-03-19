package com.mybestcoding.hmt.service;

import com.mybestcoding.hmt.model.OperLog;
import com.mybestcoding.hmt.model.OperLogWithBLOBs;

import java.util.List;

/**
 * 操作日志服务
 */
public interface OperLogService {

    int addLog(OperLogWithBLOBs log);

    int delLog(Integer id);

    OperLogWithBLOBs findOne(Integer id);

    List<OperLogWithBLOBs> findAll();
}
