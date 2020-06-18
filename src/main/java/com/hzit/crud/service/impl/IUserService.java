package com.hzit.crud.service.impl;

import com.hzit.crud.resp.UserInfo;

import java.util.List;

public interface IUserService {

    /**
     *
     * @return
     */
    public List<UserInfo> queryUserInfo(String page ,String limit);

    int countUserNum();

    public List<UserInfo> queryAllUserInfo();

}
