package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.Role;
import com.mybestcoding.hmt.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByUserName(String name);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    Role selectRoleByUserId(Integer id);

    User selectByUsername(String username);

    User selectByEmail(String email);

    List<User> selectAll();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}