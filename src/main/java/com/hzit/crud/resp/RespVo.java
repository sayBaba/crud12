package com.hzit.crud.resp;

import lombok.Data;
import lombok.ToString;

/**
 * 返回前端公共对
 */
@Data
@ToString
public class RespVo {

    private int code = 0;

    private String msg = "请求成功";


    /**
     * 成功返回
     * @return
     */
    public static RespVo getSucess(){
        return new RespVo();
    }

    /**
     * 失败返回
     * @param msg
     * @return
     */
    public static RespVo getFail(String msg){
        RespVo respVo =  new RespVo();

        respVo.setCode(-1);
        respVo.setMsg(msg);
        return  respVo;
    }

}
