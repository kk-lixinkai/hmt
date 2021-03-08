package com.mybestcoding.hmt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Role extends BaseModel {
    private static final long serialVersionUID = -1426841259462413187L;

    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色
     */
    private String role;

}