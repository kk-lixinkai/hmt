package com.mybestcoding.hmt.controller;

import com.mybestcoding.hmt.constant.ResponseBody;
import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.model.dto.LoginDto;
import com.mybestcoding.hmt.model.dto.RegisterDto;
import com.mybestcoding.hmt.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lixinkai
 * @description: 账号控制器
 * @date: 2021/2/22 15:31
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Api(tags = "账号操作")
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseBody doRegister(@RequestBody RegisterDto registerDto) {
        if (null == registerDto) {
            throw new RuntimeException("注册请求失败，请重试");
        }
        User user = accountService.doRegister(registerDto);
        return ResponseBody.success("注册成功", user);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseBody doLogin(@RequestBody LoginDto loginDto) {
        if (null == loginDto) {
            throw new RuntimeException("用户登录失败");
        }
        User user = accountService.doLogin(loginDto);
        return ResponseBody.success("登录成功", user);
    }

}
