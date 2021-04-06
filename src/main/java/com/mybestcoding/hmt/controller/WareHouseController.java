package com.mybestcoding.hmt.controller;

import cn.hutool.crypto.Mode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybestcoding.hmt.annotation.LogOperation;
import com.mybestcoding.hmt.constant.LogOperationType;
import com.mybestcoding.hmt.constant.ResponseBody;
import com.mybestcoding.hmt.model.WareHouse;
import com.mybestcoding.hmt.service.WarehouseService;
import com.sun.mail.iap.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lixinkai
 * @description: 仓库控制器
 * @date: 2021/3/23 10:35
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Api(tags = "仓库操作接口")
@RestController
@RequestMapping(value = "/api/warehouse")
public class WareHouseController {

    @Autowired
    private WarehouseService warehouseService;


    @ApiOperation(value = "查询仓库信息")
    @LogOperation(module = "仓控控制", type = LogOperationType.SELECT, desc = "查询指定仓库信息")
    @GetMapping(value = "/get/{id}")
    public ResponseBody getOne(@PathVariable("id") Integer id) {
        WareHouse result = warehouseService.getOne(id);
        return ResponseBody.success("查询成功", result);
    }

    @ApiOperation(value = "查询所有仓库信息")
    @LogOperation(module = "仓库控制", type = LogOperationType.SELECT, desc = "查询所有仓库信息")
    @GetMapping(value = "/all")
    public ResponseBody getAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<WareHouse> all = warehouseService.getAll();
        PageInfo<WareHouse> pageInfo = new PageInfo<>(all);
        return ResponseBody.success("查询成功", pageInfo);
    }

    @ApiOperation(value = "添加新的仓库信息")
    @LogOperation(module = "仓库控制", type = LogOperationType.ADDED, desc = "添加新的仓库")
    @PostMapping(value = "/add")
    public ResponseBody add(@RequestBody WareHouse wareHouse) {
        int result = warehouseService.addNewWareHouse(wareHouse);
        return ResponseBody.success("添加成功", result);
    }

    @ApiOperation(value = "修改仓库信息")
    @LogOperation(module = "仓库控制", type = LogOperationType.UPDATE, desc = "修改仓库信息")
    @PutMapping(value = "/modify")
    public ResponseBody modify(@RequestBody WareHouse wareHouse) {
        int result = warehouseService.modifyWareHouse(wareHouse);
        return ResponseBody.success("修改信息成功", result);
    }


    @ApiOperation(value = "删除仓库信息")
    @LogOperation(module = "仓库控制", type = LogOperationType.DELETE, desc = "删除仓库信息")
    @DeleteMapping(value = "/remove/{id}")
    public ResponseBody remove(@PathVariable("id") Integer id) {
        int result = warehouseService.removeWareHouse(id);
        return ResponseBody.success("删除仓库信息成功", result);
    }


}
