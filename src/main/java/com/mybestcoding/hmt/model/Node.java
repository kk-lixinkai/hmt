package com.mybestcoding.hmt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Node implements Serializable {
    private static final long serialVersionUID = -563905881939898810L;
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

    /**
     * 仓库 id
     */
    private Integer wid;

    /**
     * 终端 id
     */
    private Integer tid;

    private Date createdTime;

    private Date updatedTime;

    private Date deleteTime;

}