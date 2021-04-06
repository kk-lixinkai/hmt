package com.mybestcoding.hmt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybestcoding.hmt.constant.ResponseBody;
import com.mybestcoding.hmt.model.OperLogWithBLOBs;
import com.mybestcoding.hmt.service.OperLogService;
import com.mybestcoding.hmt.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lixinkai
 * @description: 日志控制器
 * @date: 2021/3/24 21:41
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Api(tags = "操作日志查询接口")
@RestController
@RequestMapping(value = "/api/log/operation")
public class OperLogController {
    @Autowired
    private OperLogService operLogService;


    @ApiOperation("查询所有操作日志")
    @GetMapping(value = "/all")
    public ResponseBody getAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OperLogWithBLOBs> allInfo = operLogService.findAll();
        PageInfo<OperLogWithBLOBs> pageInfo = new PageInfo<>(allInfo);
        return ResponseBody.success("操作日志查询成功", CommonUtil.pageConvert(pageInfo));
    }


    @ApiOperation("查询操作日志详情")
    @GetMapping(value = "/get/{id}")
    public ResponseBody getOne(@PathVariable("id") Integer id) {
        OperLogWithBLOBs log = operLogService.findOne(id);
        return ResponseBody.success("日志查询成功", log);
    }
}
