package com.mybestcoding.hmt.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: lixinkai
 * @description: 邮件信息实体
 * @date: 2021/2/21 15:05
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToEmail implements Serializable {

    private static final long serialVersionUID = -7620823553764944864L;

    /**
     * 收件人
     */
    @ApiModelProperty(value = "收件人")
    private String[] receivers;

    /**
     * 邮件主题
     */
    @ApiModelProperty(value = "邮件主题")
    private String subject;

    /**
     * 邮件内容
     */
    @ApiModelProperty(value = "邮件内容")
    private String content;
}
