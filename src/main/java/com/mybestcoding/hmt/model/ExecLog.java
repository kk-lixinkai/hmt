package com.mybestcoding.hmt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 异常日志
 */
@Getter
@Setter
@ToString
public class ExecLog {
    private Integer id;

    private String name;

    private String userId;

    private String userName;

    private String method;

    private String uri;

    private String ip;

    private String version;

    private Date createTime;

}