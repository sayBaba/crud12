package com.hzit.crud.controller;

import com.google.code.kaptcha.Producer;
import com.hzit.crud.req.LoginReq;
import com.hzit.crud.resp.MenuResp;
import com.hzit.crud.resp.RespVo;
import com.hzit.crud.service.impl.IMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 用户管理控制器
 */
@Controller
@RequestMapping("/sys")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IMenuService iMenuService;


    /**
     * 验证码
     */
    @Autowired
    private Producer captchaProducer = null;

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public RespVo login(@RequestBody LoginReq loginReq,HttpServletRequest request){
        logger.info("用户登录请求参数loginReq:{}",loginReq);


        if (StringUtils.isEmpty(loginReq.getVcode())){
            logger.info("验证码为空");
            return RespVo.getFail("验证码为空");
        }

       String sessionCode = (String)request.getSession().getAttribute("kaptcha");

        if(!loginReq.getVcode().equals(sessionCode)){
            return RespVo.getFail("输入验证码不正确");
        }


        //shiro 登录逻辑 TODO

        return RespVo.getSucess();
    }


    /**
     * 生成验证码
     */
    @RequestMapping("/vcode")
    public void createKaptcha(HttpServletRequest request, HttpServletResponse response){
        logger.info("生成验证码....");
        String text = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(text);
        //放到session中,登录的时候要验证
        request.getSession().setAttribute("kaptcha",text);
        //返回验证码给前端
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            logger.error("IOException",e);
        }
    }

    /**
     * 加载左侧菜单
     */
    @ResponseBody
    @RequestMapping("/getMenus")
    public MenuResp loadLeftMenu(){
        logger.info("查询菜单列表.....");
        MenuResp menuResp = iMenuService.getMenus();
        return menuResp;
    }

    @RequestMapping("/main")
    public String loadMain(){
        return "main";
    }

}
