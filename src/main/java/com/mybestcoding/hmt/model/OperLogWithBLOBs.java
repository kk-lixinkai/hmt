package com.mybestcoding.hmt.model;


import lombok.Getter;
import lombok.Setter;


/**
 * 操作日志
 */
@Getter
@Setter
public class OperLogWithBLOBs extends OperLog {
    private String requParam;

    private String respParam;
}