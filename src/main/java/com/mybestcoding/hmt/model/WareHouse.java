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
public class WareHouse implements Serializable {
    private static final long serialVersionUID = -9033247340698606590L;

    /**
     * 仓库id
     */
    private Integer id;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

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