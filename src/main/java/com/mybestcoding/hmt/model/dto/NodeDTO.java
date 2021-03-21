package com.mybestcoding.hmt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lixinkai
 * @description: 前端传来的数据
 * @date: 2021/3/16 10:57
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeDTO {
    /**
     * 节点类型
     */
    private String type;

    /**
     * 节点状态
     */
    private String status;

    /**
     * 仓库 id
     */
    private Integer wid;

    /**
     * 终端 id
     */
    private Integer tid;
}
