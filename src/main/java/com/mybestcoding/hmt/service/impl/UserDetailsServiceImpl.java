package com.mybestcoding.hmt.service.impl;

import com.mybestcoding.hmt.entity.JwtUser;
import com.mybestcoding.hmt.model.User;
import com.mybestcoding.hmt.service.RoleService;
import com.mybestcoding.hmt.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lixinkai
 * @description: Security 服务实现类
 * @date: 2021/3/27 17:18
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    private RoleService roleService;

    public UserDetailsServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.getByUserName(name);
        if (user == null && user.getId() <= 0) {
            throw new RuntimeException("该用户不存在");
        }
        List<String> roleList = roleService.getAllByUserId(user.getId());
        List<SimpleGrantedAuthority> authorities = roleList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        JwtUser jwtUser = new JwtUser();
        jwtUser.setId(user.getId());
        jwtUser.setUsername(user.getNickname());
        jwtUser.setPassword(user.getPassword());
        jwtUser.setAuthorities(authorities);
        return jwtUser;
    }
}
