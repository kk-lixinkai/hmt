package com.mybestcoding.hmt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Log extends BaseModel {
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

}