package com.hzit.crud.service.impl;

import com.hzit.crud.mapper.MenusMapper;
import com.hzit.crud.model.Menus;
import com.hzit.crud.resp.MenuData;
import com.hzit.crud.resp.MenuResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class IMenuServiceImpl implements  IMenuService{

    @Autowired
    private MenusMapper menusMapper;


    @Override
    public MenuResp getMenus() {
        MenuResp menuResp = new MenuResp();

        //查询一级菜单
       List<Menus> menusList = menusMapper.queryByParentId();
       if (CollectionUtils.isEmpty(menusList)){
           return menuResp;
       }

       List<MenuData> data = new ArrayList<MenuData>();

       //循环menusList，放到MenuResp中的data。
       for(Menus menus:menusList){
           MenuData menuData = new MenuData();
           //两个对象里面的字段一模一样,通过下面的方法copy,不一样的就要手动set
           BeanUtils.copyProperties(menus,menuData);


           //查询子菜单
           Long menuId = menus.getMenuId();
           List<Menus>  childMenus =  menusMapper.queryChildMenus(menuId);

           List<MenuData> list = new ArrayList<MenuData>();
           for(Menus m:childMenus){

               MenuData childMenu = new MenuData();

               BeanUtils.copyProperties(m,childMenu);

               list.add(childMenu);

           }

           menuData.setList(list);


           data.add(menuData);


       }
        menuResp.setData(data);


        menuResp.setCode(0);
        menuResp.setMsg("查询成功");
        return menuResp;
    }
}
