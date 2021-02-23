package com.mybestcoding.hmt.service.impl;


import java.util.Date;

import com.mybestcoding.hmt.mapper.UserMapper;
import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.model.dto.LoginDto;
import com.mybestcoding.hmt.model.dto.RegisterDto;
import com.mybestcoding.hmt.service.AccountService;
import com.mybestcoding.hmt.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lixinkai
 * @description: 账号服务
 * @date: 2021/2/22 15:32
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User doLogin(LoginDto loginDto) {
        User user;
        if (!loginDto.getAccount().contains("@")) {
            user = userMapper.selectByUsername(loginDto.getAccount());
        } else {
            user = userMapper.selectByEmail(loginDto.getAccount());
        }
        if (null == user) {
            throw new RuntimeException("此账号不存在");
        }
        // 获取密码
        String md5Passwrod = CommonUtil.encryption(loginDto.getPassword(), user.getSalt());
        if (md5Passwrod.equals(user.getPassword())) {
            Date date = new Date();
            // 更新登录时间
            userMapper.updateByPrimaryKeySelective(user.setLastLoginTime(date));
            log.info("当前的登录信息:{}", user);
            return user;
        }
        throw new RuntimeException("密码错误!");
    }

    @Override
    public User doRegister(RegisterDto registerDto) {
        //判断邮箱是否已注册
        User user = userMapper.selectByEmail(registerDto.getEmail());
        if (null != user) {
            throw new RuntimeException("邮箱已注册!");
        }
        // 获取现有的用户信息
        User user1 = userMapper.selectByUsername(registerDto.getUsername());
        // 判断用户是否存在
        if (null != user1) {
            throw new RuntimeException("用户名已存在！");
        }
        // 注册新用户
        User newUser = buildUser(registerDto);
        int result = userMapper.insertSelective(newUser);
        if (result > 0) {
            return newUser;
        }
        throw new RuntimeException("注册操作失败");
    }

    /**
     * 将 DTO 转化为 Model
     *
     * @param registerDto DTO 对象
     * @return
     */
    private User buildUser(RegisterDto registerDto) {
        String salt = CommonUtil.getSalt();
        String key = CommonUtil.getKey();
        String md5Password = CommonUtil.encryption(registerDto.getPassword(), salt);
        User newUser = new User();
        newUser.setUsername(registerDto.getUsername());
        newUser.setPassword(md5Password);
        newUser.setPrefix(registerDto.getPrefix());
        newUser.setTel(registerDto.getTel());
        newUser.setEmail(registerDto.getEmail());
        newUser.setSalt(salt);
        newUser.setUserKey(key);
        newUser.setCreatedTime(new Date());
        newUser.setLastLoginTime(new Date());
        newUser.setUpdatedTime(new Date());
        return newUser;
    }
}
