package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.Role;
import com.mybestcoding.hmt.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    Role selectRoleByUserId(Integer id);

    User selectByUsername(String username);

    User selectByEmail(String email);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}