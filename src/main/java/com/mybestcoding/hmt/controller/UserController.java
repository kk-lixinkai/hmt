package com.mybestcoding.hmt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybestcoding.hmt.annotation.LogOperation;
import com.mybestcoding.hmt.constant.LogOperationType;
import com.mybestcoding.hmt.constant.ResponseBody;
import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.service.UserService;
import com.mybestcoding.hmt.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lixinkai
 * @description: 用户类控制器
 * @date: 2021/3/23 11:42
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Api(tags = "用户操作接口")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation("查询所有用户信息")
    @LogOperation(module = "用户信息", type = LogOperationType.SELECT, desc = "查询所有用户信息")
    @GetMapping(value = "/all")
    public ResponseBody getAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> allInfo = userService.getAll();
        PageInfo<User> pageInfo = new PageInfo<>(allInfo);
        return ResponseBody.success("查询成功", CommonUtil.pageConvert(pageInfo));
    }


    @ApiOperation("查询指定用户信息")
    @LogOperation(module = "用户信息", type = LogOperationType.SELECT, desc = "查询指定用户信息")
    @GetMapping(value = "/get/{id}")
    public ResponseBody getOne(@PathVariable("id") Integer id) {
        User user = userService.getOne(id);
        return ResponseBody.success("查询成功", user);
    }

    @ApiOperation("修改用户信息")
    @LogOperation(module = "用户信息", type = LogOperationType.UPDATE, desc = "修改用户信息")
    @PutMapping(value = "/modify")
    public ResponseBody modify(@RequestBody User user) {
        int result = userService.modify(user);
        return ResponseBody.success("信息修改成功", result);
    }


    @ApiOperation("添加用户信息，暂不可用")
    @LogOperation(module = "用户信息", type = LogOperationType.ADDED, desc = "添加用户信息")
    @PostMapping(value = "/add")
    public ResponseBody add(@RequestBody User user) {
        int result = userService.add(user);
        return ResponseBody.success("添加成功", result);
    }


    @ApiOperation("删除用户信息")
    @LogOperation(module = "用户信息", type = LogOperationType.DELETE, desc = "删除用户信息")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseBody remove(@PathVariable("id") Integer id) {
        int result = userService.delete(id);
        return ResponseBody.success("删除成功", result);
    }
}
