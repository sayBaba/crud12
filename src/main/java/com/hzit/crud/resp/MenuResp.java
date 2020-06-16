package com.hzit.crud.resp;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 菜单返回数据
 */
@Data
@ToString
public class MenuResp {

    private int code;

    private String msg;

    private List<MenuData> data; //一级菜单
}
