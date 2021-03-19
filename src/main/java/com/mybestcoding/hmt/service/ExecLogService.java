package com.mybestcoding.hmt.service;

import com.mybestcoding.hmt.model.ExecLogWithBLOBs;
import com.mybestcoding.hmt.model.OperLogWithBLOBs;

import java.util.List;

/**
 * 异常日志服务
 */
public interface ExecLogService {

    int addLog(ExecLogWithBLOBs log);

    int delLog(Integer id);

    ExecLogWithBLOBs findOne(Integer id);

    List<ExecLogWithBLOBs> findAll();
}
