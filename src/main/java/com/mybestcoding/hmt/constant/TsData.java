package com.mybestcoding.hmt.constant;

import com.redislabs.redistimeseries.Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: lixinkai
 * @description: Ts 数据信息  Redis 服务器 ------> 客户端
 * @date: 2021/3/3 11:55
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TsData implements Serializable {

    private static final long serialVersionUID = -46232103044695851L;

    long timestamp;

    double value;

    /**
     * 将 Jedis 的 Value 映射为 TsData
     *
     * @param value {@link Value} 对象
     * @return {@link TsData} 对象
     */
    public static TsData mapToTsData(Value value) {
        return new TsData(value.getTime(), value.getValue());
    }
}


