package com.hzit.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 加载管理用户页面
     * @return
     */
    @RequestMapping("/userList")
    public String userList(){

        return "user/userList";
    }
}
