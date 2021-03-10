package com.mybestcoding.hmt.model.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lixinkai
 * @description: 注册信息
 * @date: 2021/2/14 15:13
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String tel;
    /**
     * 邮件地址
     */
    @ApiModelProperty("电子邮件")
    private String email;

    /**
     * 权限
     */
    @ApiModelProperty("权限")
    private String role;
}
