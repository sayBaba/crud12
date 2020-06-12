package com.hzit.crud.resp;

import java.util.List;

/**
 * 查询用户返回的java bean
 */
public class QueryResp {

    private int code;

    private String msg;

    private List<QueryData> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<QueryData> getData() {
        return data;
    }

    public void setData(List<QueryData> data) {
        this.data = data;
    }
}
