package com.hzit.crud.mapper;

import com.hzit.crud.model.Roles;

public interface RolesMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}