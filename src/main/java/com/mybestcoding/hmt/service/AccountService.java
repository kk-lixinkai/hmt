package com.mybestcoding.hmt.service;

import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.model.dto.LoginDto;
import com.mybestcoding.hmt.model.dto.RegisterDto;

/**
 * @author: lixinkai
 * @description: 账号服务
 * @date: 2021/3/10 10:35
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
public interface AccountService {

    /**
     * 登录操作
     *
     * @param loginDto
     * @return
     */
    User doLogin(LoginDto loginDto);

    /**
     * 注册操作
     *
     * @param registerDto
     * @return
     */
    User doRegistry(RegisterDto registerDto);
}
