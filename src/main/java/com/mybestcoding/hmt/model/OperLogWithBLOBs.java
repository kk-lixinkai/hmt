package com.mybestcoding.hmt.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 操作日志
 */
@Getter
@Setter
@ToString(callSuper = true)
public class OperLogWithBLOBs extends OperLog {
    private static final long serialVersionUID = 1018324626652553806L;
    private String requParam;

    private String respParam;
}