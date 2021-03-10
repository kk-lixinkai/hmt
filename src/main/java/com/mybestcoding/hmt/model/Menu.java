package com.mybestcoding.hmt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Menu {
    private static final long serialVersionUID = -387615755229881581L;

    /**
     * 菜单id
     */
    private Integer id;

    /**
     * 菜单项
     */
    private String item;

    /**
     * 等级：一级菜单、二级菜单、以此类推
     */
    private Integer level;

    /**
     * 上级菜单
     */
    private String pre;

    /**
     * 跳转连接
     */
    private String url;

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