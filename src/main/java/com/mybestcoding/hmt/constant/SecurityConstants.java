package com.mybestcoding.hmt.constant;

/**
 * @author: lixinkai
 * @description: Security 常量类
 * @date: 2021/3/27 17:00
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
public class SecurityConstants {

    /**
     * 登录的地址
     */
    public static final String AUTH_LOGIN_URL = "/auth/login";

    /**
     * 角色的key
     */
    public static final String ROLE_CLAIMS = "rol";

    /**
     * rememberMe 为 false 的时候过期时间是1个小时
     */
    public static final long EXPIRATION = 60L * 60L;


    /**
     * rememberMe 为 true 的时候过期时间是 7 天
     */
    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    /**
     * JWT 签名秘钥硬编码到应用程序中，应该存放在环境变量或.properties 文件中
     */
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";


    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    private SecurityConstants() {
    }

}
