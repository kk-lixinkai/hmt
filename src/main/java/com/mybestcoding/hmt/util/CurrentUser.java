package com.mybestcoding.hmt.util;

import com.mybestcoding.hmt.entity.JwtUser;
import com.mybestcoding.hmt.service.impl.UserDetailsServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author: lixinkai
 * @description: 获取当前用户
 * @date: 2021/3/28 15:44
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Component
public class CurrentUser {

    private final UserDetailsServiceImpl userDetailsService;


    public CurrentUser(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public JwtUser getCurrentUser() {
        return (JwtUser) userDetailsService.loadUserByUsername(getCurrentUserName());
    }

    /**
     * TODO: 由于在 JWTAuthorizationFilter这个类注入UserDetailsServiceImpl 一致失败
     * 导致无法正确查找到用户，所以存入Authentication的Principal为从token中取出的当前用户的姓名
     */
    private static String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}
