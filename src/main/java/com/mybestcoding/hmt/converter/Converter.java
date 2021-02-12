package com.mybestcoding.hmt.converter;

/**
 * 通用转换器
 */
public interface Converter<T,S> {

    /**
     * 将 T 转换为 S
     * @param object 待转换对象
     * @return 转换后对象
     */
    S forward(T object);


    /**
     * 将 S 转换回 T
     * @param object 待转换对象
     * @return 待转换对象
     */
    T backward(S object);
}
