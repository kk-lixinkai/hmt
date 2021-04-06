package com.mybestcoding.hmt.constant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: lixinkai
 * @description: 响应信息
 * @date: 2021/2/14 15:28
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Getter
@Setter
@ToString
public class ResponseBody implements Serializable {
    private static final long serialVersionUID = -1096772578314048197L;
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object obj;

    /**
     * 令牌
     */
    private String token;


    public ResponseBody(Integer code, String message, Object obj, String token) {
        this.code = code;
        this.message = message;
        this.obj = obj;
        this.token = token;
    }

    public ResponseBody(Integer code, String message, Object obj) {
        this(code, message, obj, null);
    }


    public ResponseBody(Integer code, String message) {
        this(code, message, null);
    }

    public ResponseBody(Integer code) {
        this(code, "");
    }

    public ResponseBody() {
    }

    public static ResponseBody success(String message, Object obj) {
        return new ResponseBody(200, message, obj);
    }

    public static ResponseBody success(String message) {
        return new ResponseBody(200, message, null);
    }

    public static ResponseBody success() {
        return new ResponseBody(200, "OK", null);
    }

    public static ResponseBody error(String message, Object obj) {
        return new ResponseBody(404, message, obj);
    }

    public static ResponseBody error(String message) {
        return new ResponseBody(404, message, null);
    }

    public static ResponseBody error() {
        return new ResponseBody(404, "ERROR", null);
    }

}
