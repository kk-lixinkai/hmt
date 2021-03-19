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
    private String requParam;

    private String respParam;
}