package com.hzit.crud.req;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 添加用户请求参数
 */
public class UserAddReq {

    private String account;
    private String password;
    private String name;
    private String info;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
