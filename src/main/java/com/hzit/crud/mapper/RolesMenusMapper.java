package com.hzit.crud.mapper;

import com.hzit.crud.model.RolesMenusKey;

public interface RolesMenusMapper {

    int deleteByPrimaryKey(RolesMenusKey key);

    int insert(RolesMenusKey record);

    int insertSelective(RolesMenusKey record);
}