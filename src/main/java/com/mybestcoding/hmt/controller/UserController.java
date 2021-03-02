package com.mybestcoding.hmt.controller;

import com.mybestcoding.hmt.constant.ResponseBody;
import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.service.UserManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lixinkai
 * @description: 用户接口
 * @date: 2021/2/24 15:46
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Api(tags = "用户操作API")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserManageService userService;

    @PostMapping(value = "/add")
    public ResponseBody doAddUser(User user) {
        return ResponseBody.success("这是一个空接口，不执行任何操作!");
    }

    @GetMapping(value = "/get/id/{id}")
    public ResponseBody getById(@PathVariable("id") Integer id) {
        User user = userService.selectOneById(id);
        return ResponseBody.success("查询用户成功", user);
    }

    @GetMapping(value = "/get/name/{username}")
    public ResponseBody getByName(@PathVariable("username") String username) {
        User user = userService.selectOneByUsername(username);
        return ResponseBody.success("查询用户信息成功", user);
    }

    @DeleteMapping(value = "/delete/id/{id}")
    public ResponseBody deleteById(@PathVariable("id") Integer id) {
        int result = userService.delete(id);
        return ResponseBody.success("删除操作成功", result);
    }
}
