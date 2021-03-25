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
public class Role implements Serializable {
    private static final long serialVersionUID = -1426841259462413187L;

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色
     */
    private String role;

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