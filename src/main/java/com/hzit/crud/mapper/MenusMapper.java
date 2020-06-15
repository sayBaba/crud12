package com.hzit.crud.mapper;

import com.hzit.crud.model.Menus;

public interface MenusMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(Menus record);

    int insertSelective(Menus record);

    Menus selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);
}