package com.mybestcoding.hmt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Permission extends BaseModel {
    private static final long serialVersionUID = -8612285458865046650L;

    /**
     * 权限ID
     */
    private Integer id;

    /**
     * 权限
     */
    private String permission;

}