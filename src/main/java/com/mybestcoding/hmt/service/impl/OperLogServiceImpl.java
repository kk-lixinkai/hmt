package com.mybestcoding.hmt.service.impl;

import com.mybestcoding.hmt.mapper.OperLogMapper;
import com.mybestcoding.hmt.model.OperLog;
import com.mybestcoding.hmt.model.OperLogWithBLOBs;
import com.mybestcoding.hmt.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lixinkai
 * @description: 操作日志服务实现类
 * @date: 2021/3/18 17:17
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class OperLogServiceImpl implements OperLogService {

    @Autowired
    private OperLogMapper operLogMapper;


    @Override
    public int addLog(OperLogWithBLOBs log) {
        return operLogMapper.insertSelective(log);
    }

    @Override
    public int delLog(Integer id) {
        return operLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OperLogWithBLOBs findOne(Integer id) {
        return operLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OperLogWithBLOBs> findAll() {
        return operLogMapper.selectAll();
    }
}
