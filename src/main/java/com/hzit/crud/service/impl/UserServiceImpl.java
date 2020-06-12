package com.hzit.crud.service.impl;

import com.hzit.crud.controller.UserController;
import com.hzit.crud.mapper.UserMapper;
import com.hzit.crud.model.User;
import com.hzit.crud.req.UserAddReq;
import com.hzit.crud.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户接口实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger loger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(UserAddReq userAddReq) {

        User user = new User();
        user.setMsg(userAddReq.getInfo());
        user.setPassword(userAddReq.getPassword());
        user.setRealName(userAddReq.getName());
        user.setUserName(userAddReq.getAccount());

        int rl = userMapper.insert(user);
        loger.info("受影响的记录：{}",rl);

        if(rl >0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<User> queryAllUser() {

        return userMapper.queryAllUser();
    }
}
