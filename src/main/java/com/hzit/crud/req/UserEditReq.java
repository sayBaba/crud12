package com.hzit.crud.req;

/**
 * 更新用户请求参数
 */
public class UserEditReq extends UserAddReq {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
