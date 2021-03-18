package com.mybestcoding.hmt.model;


import lombok.Getter;
import lombok.Setter;

/**
 * 异常日志
 */
@Getter
@Setter
public class ExecLogWithBLOBs extends ExecLog {
    private String requParam;

    private String message;
}