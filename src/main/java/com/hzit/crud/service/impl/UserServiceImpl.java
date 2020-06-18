package com.hzit.crud.service.impl;

import com.hzit.crud.mapper.UserMapper;
import com.hzit.crud.model.User;
import com.hzit.crud.resp.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserInfo> queryUserInfo(String page ,String limit) {
        List<User> userList = userMapper.queryUsers(Integer.parseInt(page),Integer.parseInt(limit));

        List<UserInfo> userInfoList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(userList)){
            for (User u:userList){
                UserInfo userInfo = new UserInfo();
                userInfo.setNickname(u.getNickname());
                userInfo.setEMail(u.geteMail());
                userInfo.setPhone(u.geteMail());
                userInfoList.add(userInfo);
            }
        }
        return userInfoList;
    }

    @Override
    public int countUserNum() {
        return userMapper.countNum();
    }

    @Override
    public List<UserInfo> queryAllUserInfo() {

        List<User> userList = userMapper.queryAllUsers();

        List<UserInfo> userInfoList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(userList)){
            for (User u:userList){
                UserInfo userInfo = new UserInfo();
                userInfo.setNickname(u.getNickname());
                userInfo.setEMail(u.geteMail());
                userInfo.setPhone(u.geteMail());
                userInfoList.add(userInfo);
            }
        }
        return userInfoList;

    }
}
