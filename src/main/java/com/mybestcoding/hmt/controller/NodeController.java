package com.mybestcoding.hmt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybestcoding.hmt.annotation.LogOperation;
import com.mybestcoding.hmt.constant.LogOperationType;
import com.mybestcoding.hmt.constant.ResponseBody;
import com.mybestcoding.hmt.model.Node;
import com.mybestcoding.hmt.model.TerminalDevice;
import com.mybestcoding.hmt.model.WareHouse;
import com.mybestcoding.hmt.model.dto.NodeDTO;
import com.mybestcoding.hmt.service.NodeService;
import com.mybestcoding.hmt.service.TerminalDeviceService;
import com.mybestcoding.hmt.service.WarehouseService;
import com.mybestcoding.hmt.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author: lixinkai
 * @description: 硬件节点控制器
 * @date: 2021/3/20 9:33
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Api(tags = "节点控制")
@RestController
@RequestMapping(value = "/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;


    @Autowired
    private TerminalDeviceService terminalDeviceService;

    @Autowired
    private WarehouseService warehouseService;


    @ApiOperation("获取所有节点")
    @LogOperation(module = "节点控制", type = LogOperationType.SELECT, desc = "查询现有的的所有节点")
    @GetMapping(value = "/all")
    public ResponseBody getAllNodeInfo(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Node> nodes = nodeService.findAll();
        PageInfo<Node> nodesinfo = new PageInfo<>(nodes);
        return ResponseBody.success("success", CommonUtil.pageConvert(nodesinfo));
    }


    @ApiOperation("获取指定节点")
    @LogOperation(module = "节点控制", type = LogOperationType.SELECT, desc = "查询当前节点")
    @GetMapping(value = "/get/{id}")
    public ResponseBody getNodeInfo(@PathVariable("id") Integer id) {
        Node result = nodeService.findOne(id);
        if (result == null || result.getId() == 0) {
            throw new RuntimeException("该节点不存在");
        }
        return ResponseBody.success("success", result);
    }


    @ApiOperation("添加新节点")
    @LogOperation(module = "节点控制", type = LogOperationType.ADDED, desc = "添加新节点")
    @PostMapping(value = "/add")
    public ResponseBody addNode(@RequestBody NodeDTO nodeDTO) {
        if (!check(nodeDTO.getWid(), nodeDTO.getTid())) {
            throw new RuntimeException("信息错误");
        }
        // 构建新节点
        Node newNode = createNewNode(nodeDTO);
        Node result = nodeService.add(newNode);
        return ResponseBody.success("success", result);
    }

    @ApiOperation("修改节点")
    @LogOperation(module = "节点控制", type = LogOperationType.UPDATE, desc = "修改节点信息")
    @PutMapping(value = "/modify")
    public ResponseBody modifyNode(@RequestBody Node node) {
        int modifyResult = nodeService.modify(node);
        return ResponseBody.success("修改成功", modifyResult);
    }


    @ApiOperation("删除节点")
    @LogOperation(module = "节点控制", type = LogOperationType.DELETE, desc = "删除节点")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseBody removeNode(@PathVariable("id") Integer id) {
        int removeResult = nodeService.remove(id);
        return ResponseBody.success("节点移除成功", removeResult);
    }


    /**
     * 检查仓库和终端是否正确
     *
     * @param wid 仓库ID
     * @param did 终端ID
     * @return true 正确： false 目标不存在
     */
    private boolean check(Integer wid, Integer did) {
        WareHouse wareHouse = warehouseService.getOne(wid);
        TerminalDevice terminalDevice = terminalDeviceService.getOne(did);
        if (wareHouse == null || terminalDevice == null) {
            return false;
        }
        return true;
    }


    /**
     * 构建新节点
     *
     * @param nodeDTO
     * @return
     */
    private Node createNewNode(NodeDTO nodeDTO) {
        return new Node().setStatus(nodeDTO.getStatus())
                .setType(nodeDTO.getType())
                .setWid(nodeDTO.getWid())
                .setTid(nodeDTO.getTid())
                .setCreatedTime(new Date())
                .setUpdatedTime(null)
                .setDeleteTime(null);
    }
}
