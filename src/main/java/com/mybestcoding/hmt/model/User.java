package com.mybestcoding.hmt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User extends BaseModel {
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

}