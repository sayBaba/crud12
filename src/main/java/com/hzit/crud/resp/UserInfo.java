package com.hzit.crud.resp;

import lombok.Data;

@Data
public class UserInfo {

    private String uid;
    private String eMail;

    private String phone;
    private String nickname;

    private String sex;

}
