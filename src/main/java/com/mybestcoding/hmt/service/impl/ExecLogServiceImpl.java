package com.mybestcoding.hmt.service.impl;

import com.mybestcoding.hmt.mapper.ExecLogMapper;
import com.mybestcoding.hmt.model.ExecLogWithBLOBs;
import com.mybestcoding.hmt.service.ExecLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lixinkai
 * @description: 异常日志服务实现类
 * @date: 2021/3/18 17:23
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class ExecLogServiceImpl implements ExecLogService {

    @Autowired
    private ExecLogMapper execLogMapper;

    @Override
    public int addLog(ExecLogWithBLOBs log) {
        return execLogMapper.insertSelective(log);
    }

    @Override
    public int delLog(Integer id) {
        return execLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ExecLogWithBLOBs findOne(Integer id) {
        return execLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ExecLogWithBLOBs> findAll() {
        return execLogMapper.selectAll();
    }
}
