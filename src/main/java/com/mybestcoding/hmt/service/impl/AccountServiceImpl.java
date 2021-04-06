package com.mybestcoding.hmt.service.impl;

import com.mybestcoding.hmt.mapper.RoleMapper;
import com.mybestcoding.hmt.mapper.UserMapper;
import com.mybestcoding.hmt.model.Role;
import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.model.dto.LoginDto;
import com.mybestcoding.hmt.model.dto.RegisterDto;
import com.mybestcoding.hmt.service.AccountService;
import com.mybestcoding.hmt.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author: lixinkai
 * @description: 账号服务实现类
 * @date: 2021/3/10 10:42
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public User doLogin(LoginDto loginDto) {
        User user = null;
        // 判断账号类型
        String account = loginDto.getAccount();
        if (account.contains("@")) {
            user = userMapper.selectByEmail(account);
        } else {
            user = userMapper.selectByUserName(account);
        }

        // 判断用户是否存在
        if (null == user) {
            throw new RuntimeException("该用户不存在");
        }
        // 判断密码是否正确
        String password = bCryptPasswordEncoder.encode(loginDto.getPassword());
        log.info("加密后的密码{}， 数据库内的密码{}", password, user.getPassword());
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("用户密码密码错误");
        }
        return user;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public User doRegistry(RegisterDto registerDto) {
        checkReRegister(registerDto.getUsername(), registerDto.getEmail());
        // 获取加密盐
        String salt = CommonUtil.getSalt();
        // 生成密码密文
        String password = bCryptPasswordEncoder.encode(registerDto.getPassword());
        // 构建新用户
        User user = new User()
                .setNickname(registerDto.getUsername())
                .setPassword(password)
                .setSalt(salt)
                .setTel(registerDto.getTel())
                .setEmail(registerDto.getEmail())
                .setCreatedTime(new Date())
                .setUpdatedTime(null)
                .setDeleteTime(null);
        // 添加权限
        userMapper.insertSelective(user);
        log.info("返回的用户数据:{}", user);
        // 添加权限
        String roleName = registerDto.getRole();
        Role role = roleMapper.selectByRoleName(roleName);
        // 检查角色的合法性
        checkRoleLegality(role);
        // 绑定角色
        int bindResult = roleMapper.bindRole(user.getId(), role.getId());
        // 检查用户绑定权限的结果
        if (!(bindResult > 0)) {
            throw new RuntimeException("权限绑定失败");
        }
        return user;
    }

    /**
     * 检查是否被重注册
     *
     * @param username 用户名
     * @param email    邮箱
     */
    private void checkReRegister(String username, String email) {
        if (null != username) {
            User user = userMapper.selectByUserName(username);
            if (user != null) {
                throw new RuntimeException("该用户名已经被使用");
            }
        }
        if (null != email) {
            User user = userMapper.selectByEmail(email);
            if (user != null) {
                throw new RuntimeException("该邮箱已经被使用");
            }
        }
    }

    /**
     * 检查角色合法性
     *
     * @param role
     */
    private void checkRoleLegality(Role role) {
        if (null == role) {
            throw new RuntimeException("非法角色，重新选择");
        }
    }
}
