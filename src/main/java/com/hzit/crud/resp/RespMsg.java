package com.hzit.crud.resp;

public class RespMsg {

    private int status; //1-代表成功，其他失败

    private String msg; //错误提示

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
