package com.mybestcoding.hmt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Node extends BaseModel {

    private static final long serialVersionUID = 6990724034092315537L;

    /**
     * 节点id
     */
    private Integer id;

    /**
     * 节点类型
     */
    private String type;

    /**
     * 节点状态
     */
    private String status;

}