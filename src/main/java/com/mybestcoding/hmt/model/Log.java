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
public class Log implements Serializable {
    private static final long serialVersionUID = -4398366672474765528L;

    /**
     * 日志id
     */
    private Integer id;

    /**
     * 用户
     */
    private String user;

    /**
     * 动作
     */
    private String action;

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