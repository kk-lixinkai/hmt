package com.mybestcoding.hmt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybestcoding.hmt.constant.ResponseBody;
import com.mybestcoding.hmt.model.ExecLogWithBLOBs;
import com.mybestcoding.hmt.service.ExecLogService;
import com.mybestcoding.hmt.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: lixinkai
 * @description: 异常日志类
 * @date: 2021/3/24 21:50
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Api(tags = "异常日志查询")
@RestController
@RequestMapping(value = "/api/log/exception")
public class ExceLogController {

    @Autowired
    private ExecLogService execLogService;

    @ApiOperation("查询所有异常日志")
    @GetMapping(value = "/all")
    public ResponseBody getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ExecLogWithBLOBs> allLog = execLogService.findAll();
        PageInfo<ExecLogWithBLOBs> execLogWithBLOBsPageInfo = new PageInfo<>(allLog);
        return ResponseBody.success("查询成功", CommonUtil.pageConvert(execLogWithBLOBsPageInfo));
    }

    @ApiOperation("查询指定异常日志")
    @GetMapping(value = "/get/{id}")
    public ResponseBody getOne(@PathVariable("id") Integer id) {
        ExecLogWithBLOBs log = execLogService.findOne(id);
        return ResponseBody.success("查询成功", log);
    }
}
