package com.mybestcoding.hmt.service;


import java.util.List;

/**
 * 角色服务
 */
public interface RoleService {

    /**
     * 添加角色
     *
     * @param role
     */
    void add(String role);

    /**
     * 绑定角色
     *
     * @param userId
     * @param roleId
     */
    void bindRole(int userId, int roleId);

    /**
     * 获取用户的角色列表
     *
     * @param userId
     * @return
     */
    List<String> getAllByUserId(int userId);

    /**
     * 获取全部角色
     *
     * @return
     */
    List<String> getAll();
}
