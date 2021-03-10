package com.mybestcoding.hmt.common.exception;

import com.mybestcoding.hmt.constant.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: lixinkai
 * @description: 全局异常处理
 * @date: 2021/3/10 11:14
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseBody runtimeExceptionHandler(Exception e) {
        String exceptionMessage = e.getMessage();
        return ResponseBody.error(exceptionMessage);
    }
}
