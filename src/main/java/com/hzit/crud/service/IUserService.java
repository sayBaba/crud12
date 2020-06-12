package com.hzit.crud.service;

import com.hzit.crud.model.User;
import com.hzit.crud.req.UserAddReq;

import java.util.List;

/**
 * 用户相关
 */
public interface IUserService {

    /**
     * 添加用户
     */
    public boolean addUser(UserAddReq userAddReq);

    /**
     * 查询所有用户
     * @return
     */
    public List<User> queryAllUser();

}
