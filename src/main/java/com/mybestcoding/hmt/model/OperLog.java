package com.mybestcoding.hmt.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 操作日志
 */
@Setter
@Getter
public class OperLog {
    private Integer id;

    private String userId;

    private String userName;

    private String method;

    private String uri;

    private String ip;

    private Date createTime;

    private String version;
}