package com.hzit.crud.resp;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 对应数据库里面的menu表
 */
@Data
@ToString
public class MenuData {

    private Long menuId;

    private String title;

    private String icon;

    private String href;

    private String perms;

    private String spread;

    private Long parentId;

    private Long sorting;

    //子菜单
    private List<MenuData> list;

}
