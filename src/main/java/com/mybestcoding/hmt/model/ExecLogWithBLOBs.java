package com.mybestcoding.hmt.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 异常日志
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ExecLogWithBLOBs extends ExecLog {
    private static final long serialVersionUID = 6828167334938779891L;
    private String requParam;

    private String message;
}