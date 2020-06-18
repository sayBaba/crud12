package com.hzit.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hzit.crud.resp.PageResult;
import com.hzit.crud.resp.UserInfo;
import com.hzit.crud.service.impl.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 加载管理用户页面
     * @return
     */
    @RequestMapping("/userList")
    public String userList(){

        return "user/userList";
    }

    @ResponseBody
    @RequestMapping("/getUserList")
    public PageResult<UserInfo> queryUserInfo(@RequestParam("page")String page,
                               @RequestParam("limit") String limit){

        PageResult pageResult = new PageResult();

//        int page1 = Integer.parseInt(page) -1;

//        List<UserInfo> userInfoList =userService.queryUserInfo(String.valueOf(page1),limit);

        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(limit));
        List<UserInfo> userInfoList = userService.queryAllUserInfo();

        //前端不是layui，返回这个对象
        PageInfo<UserInfo> pageInfo = new <UserInfo>PageInfo(userInfoList);
        System.out.println("-----"+ pageInfo);


        //封装LAYUI分页需要的
        pageResult.setCount(userService.countUserNum());
        pageResult.setData(pageInfo.getList());
        pageResult.setMsg("成功");
        pageResult.setCode("0");
        return pageResult;
    }



}
