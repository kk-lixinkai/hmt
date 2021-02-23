package com.mybestcoding.hmt.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lixinkai
 * @description: 登录信息
 * @date: 2021/2/14 15:13
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @ApiModelProperty("用户名或者邮箱")
    private String account;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("记住我")
    private boolean remember;

}
