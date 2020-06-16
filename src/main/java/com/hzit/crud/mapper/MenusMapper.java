package com.hzit.crud.mapper;

import com.hzit.crud.model.Menus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenusMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(Menus record);

    int insertSelective(Menus record);

    Menus selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);

    /**
     * 查询一级菜单
     * @return
     */
    List<Menus> queryByParentId();


    /**
     * 查询一级菜单下面的子菜单
     * @return
     */
    List<Menus> queryChildMenus(@Param("parentId") Long parentId);

}