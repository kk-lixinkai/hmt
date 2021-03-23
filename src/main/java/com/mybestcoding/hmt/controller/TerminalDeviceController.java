package com.mybestcoding.hmt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybestcoding.hmt.annotation.LogOperation;
import com.mybestcoding.hmt.constant.LogOperationType;
import com.mybestcoding.hmt.constant.ResponseBody;
import com.mybestcoding.hmt.model.TerminalDevice;
import com.mybestcoding.hmt.service.TerminalDeviceService;
import com.mybestcoding.hmt.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lixinkai
 * @description: 终端控制器
 * @date: 2021/3/22 16:21
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Api(tags = "终端控制接口")
@RestController
@RequestMapping(value = "/terminal")
public class TerminalDeviceController {

    @Autowired
    private TerminalDeviceService terminalDeviceService;

    @ApiOperation("查询终端设备信息")
    @LogOperation(module = "终端控制", type = LogOperationType.SELECT, desc = "查询终端设备信息")
    @GetMapping(value = "/{id}")
    public ResponseBody getOne(@PathVariable("id") Integer id) {
        TerminalDevice terminalDevice = terminalDeviceService.getOne(id);
        return ResponseBody.success("查询成功", terminalDevice);
    }


    @ApiOperation("查询仓库中的终端")
    @LogOperation(module = "终端控制", type = LogOperationType.SELECT, desc = "查询指定仓库下的终端信息")
    @GetMapping(value = "/all")
    public ResponseBody getAll(@RequestParam("wid") Integer wId, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TerminalDevice> allTerminalToWarehouse = terminalDeviceService.getAllByWareHouseId(wId);
        PageInfo<TerminalDevice> info = new PageInfo<>(allTerminalToWarehouse);
        return ResponseBody.success("查询成功", CommonUtil.pageConvert(info));
    }


    @ApiOperation("添加终端")
    @LogOperation(module = "终端控制", type = LogOperationType.ADDED, desc = "给指定仓库添加终端")
    @PostMapping(value = "/add/{wid}")
    public ResponseBody addTerminal(@PathVariable Integer wId, @RequestBody TerminalDevice terminalDevice) {
        int result = terminalDeviceService.addTerminalDevice(terminalDevice, wId);
        return ResponseBody.success("添加成功", result);
    }

    @ApiOperation("修改终端信息")
    @LogOperation(module = "终端控制", type = LogOperationType.UPDATE, desc = "修改终端信息")
    @PutMapping(value = "/modify")
    public ResponseBody modifyTerminal(@RequestBody TerminalDevice terminalDevice) {
        int result = terminalDeviceService.modifyTerminalDevices(terminalDevice);
        return ResponseBody.success("修改成功", result);
    }

    @ApiOperation("移除终端")
    @LogOperation(module = "终端控制", type = LogOperationType.DELETE, desc = "删除终端信息")
    @DeleteMapping(value = "/delete")
    public ResponseBody removeTerminal(@RequestParam("wid") Integer wid, @RequestParam("did") Integer did) {
        int result = terminalDeviceService.removeTerminalDevice(wid, did);
        return ResponseBody.success("删除成功", did);
    }
}
