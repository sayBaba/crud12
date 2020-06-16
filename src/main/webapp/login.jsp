<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>登录--后台管理</title>
    <link rel="stylesheet" href="${ctx }/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctx }/css/login.css" media="all" />
    <script>  
        var ctx = "${ctx}";  
    </script>

    <script type="text/javascript">
        if(window !=top){
            top.location.href=location.href;
        }
    </script>
</head>
<body>


<div class="video_mask"></div>
<div class="login">
    <h1>管理员登录</h1>
    <form class="layui-form" id="form">
        <div class="layui-form-item">
            <input class="layui-input" name="username" placeholder="用户名" value="admin" lay-verify="required" type="text" autocomplete="off">
        </div>
        <div class="layui-form-item">
            <input class="layui-input" name="password" placeholder="密码" value="123456"  lay-verify="required" type="password" autocomplete="off">
        </div>
        <div class="layui-form-item form_code">
            <input class="layui-input" style="width: 140px;" name="vcode" placeholder="验证码" lay-verify="required" type="text" autocomplete="off" maxlength="4">
            <div class="code"><img id="captcha" src="${ctx }/sys/vcode" width="100" height="36" onclick="refreshCode(this)"></div>
        </div>
        <button class="layui-btn login_btn" lay-submit="" lay-filter="login" id="btn">登录</button>
    </form>
</div>
<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
<script type="text/javascript" >

    layui.use(['form', 'layer'], function () {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : parent.layer,
            $ = layui.jquery;

        layer.alert('测试账号：admin 密码：123456', {
            skin: 'layui-layer-molv' //样式类名
            ,closeBtn: 0,
            offset: 't',
            anim: 6
        })

        //登录按钮事件
        form.on("submit(login)", function (data) {
            $.ajax({
                type: "get",
                url: ctx+"/sys/login",
                data:$("#form").serialize(),
                success: function (result) {
                    if (result.code == 0) {//登录成功
                        parent.location.href = ctx+'/sys/index';
                    } else{
                        layer.msg(result.msg, {icon: 5});
                        refreshCode();
                    }
                }
            });
            return false;
        })
    });
    function refreshCode(){
        var captcha = document.getElementById("captcha");
        captcha.src = ctx+"/sys/vcode?t=" + new Date().getTime();
    }


</script>
</body>
</html>