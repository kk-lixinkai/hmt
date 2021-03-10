package com.mybestcoding.hmt.controller;

import com.mybestcoding.hmt.constant.ResponseBody;
import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.model.dto.LoginDto;
import com.mybestcoding.hmt.model.dto.RegisterDto;
import com.mybestcoding.hmt.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: lixinkai
 * @description: 账号控制器
 * @date: 2021/3/10 11:03
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Api(tags = "账号操作")
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @ApiOperation("登录")
    @PostMapping(value = "/login")
    public ResponseBody doLogin(@RequestBody LoginDto loginDto) {
        User user = accountService.doLogin(loginDto);
        return ResponseBody.success("用户登录成功", user);
    }

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    public ResponseBody doRegister(@RequestBody RegisterDto registerDto) {
        User user = accountService.doRegistry(registerDto);
        return ResponseBody.success("新用户注册成功", user);
    }
}
