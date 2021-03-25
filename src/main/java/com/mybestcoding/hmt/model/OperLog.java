package com.mybestcoding.hmt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 */
@Setter
@Getter
@ToString
public class OperLog implements Serializable {
    private static final long serialVersionUID = -4869578326424826523L;
    private Integer id;

    private String module;

    private String description;

    private String userId;

    private String userName;

    private String type;

    private String method;

    private String uri;

    private String ip;

    private Date createTime;

    private String version;
}