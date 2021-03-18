package com.mybestcoding.hmt.annotation;

import java.lang.annotation.*;

/**
 * 自定义操作注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogOperation {
    /**
     * 模块名
     *
     * @return
     */
    String module() default "";

    /**
     * 类型名
     *
     * @return
     */
    String type() default "";

    /**
     * 操作说明
     *
     * @return
     */
    String desc() default "";
}
