package com.hzit.crud.controller;

import com.hzit.crud.model.User;
import com.hzit.crud.req.UserAddReq;
import com.hzit.crud.req.UserEditReq;
import com.hzit.crud.resp.QueryData;
import com.hzit.crud.resp.QueryResp;
import com.hzit.crud.resp.RespMsg;
import com.hzit.crud.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息控制器
 */
@Controller //返回 json，但是不能返回页面 = @Controller + @ResponeseBody
@RequestMapping("/user")
public class UserController {

    //通过spring 获取对象
    @Autowired
    private IUserService iUserService;

    private static final Logger loger = LoggerFactory.getLogger(UserController.class);

    /**
     * 加载用户页面
     * @return
     */
    @RequestMapping("/show")
    public String showUser(){
        loger.info("----加载user页面--");
        return "user";
    }

    /**
     * 添加用户
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public RespMsg addUser(@RequestBody UserAddReq userAddReq){
        loger.info("请求参数：account:{},password:{},name:{},info:{}",userAddReq.getAccount(),
                userAddReq.getPassword(),userAddReq.getName(),userAddReq.getInfo());
        //userAddReq 非空判断
        RespMsg respMsg = new RespMsg();

        if(StringUtils.isEmpty(userAddReq.getAccount())){
            respMsg.setStatus(-1);
            respMsg.setMsg("用户名不能为空");
            loger.info("用户名不能为空");
            return respMsg;

        }

        boolean flag = iUserService.addUser(userAddReq);

        if(flag == true){
            respMsg.setStatus(1);
            respMsg.setMsg("添加成功");
        }else {
            respMsg.setStatus(-1);
            respMsg.setMsg("添加失败，请重试");
        }

        loger.info("返回前端的数据：{}",respMsg);
        //返回json数据
        return respMsg;
    }

    /**
     * 查询用户列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryAll")
    public QueryResp queryAllUser(){
        loger.info("接受到查询所有用户请求.....");
        QueryResp queryResp = new QueryResp();

        //数据库查出来的
        List<User> userList = iUserService.queryAllUser();
        queryResp.setCode(0);
        queryResp.setMsg("查询成功");


        if(CollectionUtils.isEmpty(userList)){
            return queryResp;
        }
        //返回给前端的数据
        List<QueryData> dataList = new ArrayList<QueryData>();

        for(User u:userList){
            QueryData data = new QueryData();

            data.setId(u.getId());
            data.setAccount(u.getUserName());
            data.setPassword(u.getPassword());
            data.setInfo(u.getMsg());
            data.setName(u.getRealName());
            dataList.add(data);
        }
        queryResp.setData(dataList);

        return queryResp;
    }

    /**
     * 更新用户信息
     * @param userEditReq
     */
    @RequestMapping(value = "/edit" )
    public void updateUser(@RequestBody UserEditReq userEditReq){
        loger.info("更新请求参数：account:{},password:{},name:{},info:{}",userEditReq.getAccount(),
                userEditReq.getPassword(),userEditReq.getName(),userEditReq.getInfo());



    }

    /**
     * 根据删除用户信息
     */
    @RequestMapping(value = "/del/{id}",method = RequestMethod.GET)
    public void delUser(@PathVariable("id") String id){
        loger.info("根据id删除用户信息 id：{}",id);


    }





}
