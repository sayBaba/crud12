package com.hzit.crud.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 登录请求对象
 */
@Data //不要写，get set方法
@ToString //toString 方法
//@AllArgsConstructor //所有构造方法
public class LoginReq {

    private String username;
    private String password;
    private String vcode;


}
