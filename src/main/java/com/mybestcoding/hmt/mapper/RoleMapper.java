package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    Role selectByRoleName(String roleName);

    List<String> selectAllRoles();

    List<String> selectRoleByUserId(int uid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int bindRole(int userId, int roleId);
}