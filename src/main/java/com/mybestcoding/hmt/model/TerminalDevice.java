package com.mybestcoding.hmt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TerminalDevice implements Serializable {
    private static final long serialVersionUID = -3953280884598284340L;

    /**
     * 终端设备id
     */
    private Integer id;

    /**
     * 工作状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间(可空)
     */
    private Date updatedTime;

    /**
     * 删除时间(可空)
     */
    private Date deleteTime;

}