package com.mybestcoding.hmt.service;


import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.model.dto.LoginDto;
import com.mybestcoding.hmt.model.dto.RegisterDto;

public interface AccountService {
    /**
     * 执行登录动作
     *
     * @param loginDto
     * @return
     */
    User doLogin(LoginDto loginDto);

    /**
     * 执行注册动作
     *
     * @param registerDto
     * @return
     */
    User doRegister(RegisterDto registerDto);
}
