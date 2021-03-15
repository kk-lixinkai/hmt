package com.mybestcoding.hmt.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lixinkai
 * @description: 接收环境数据  硬件 --- > 服务器
 * @date: 2021/3/10 18:13
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TsNodeDataPackage {

    private String wid;

    private String did;

    private String nid;

    private String type;

    private long timestamp;

    private double value;


    /**
     * 获取键
     *
     * @return
     */
    public String getKey() {
        String key = this.type + ":" + this.wid + ":" + this.did + ":" + this.nid;
        return key;
    }


    /**
     * 获取类型
     *
     * @return
     */
    public String getType() {
        return type;
    }


    /**
     * 获取值
     *
     * @return
     */
    public double getValue() {
        return value;
    }
}
