package com.mybestcoding.hmt.mapper;

import com.mybestcoding.hmt.model.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectAll();

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}