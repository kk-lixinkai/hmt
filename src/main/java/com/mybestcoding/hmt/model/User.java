package com.mybestcoding.hmt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -2359243381270633037L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间(可空)
     */
    private Date updatedTime;

    /**
     * 删除时间(可空)
     */
    private Date deleteTime;

}