/**
 * 刷新验证码
 */
function refreshCode() {
   var captcha = document.getElementById("captcha");
    captcha.src = ctx+"/sys/vcode?t=" + new Date().getTime();
}

/**
 * 登录
 */

layui.use(['form', 'layer'], function () {

    //登录按钮事件
    form.on("submit(login)", function (data) {
        $.ajax({
            type: "POST",
            url: ctx+"/sys/login",
            data:$("#form").serialize(),
            success: function (result) {
                if (result.code == 0) {//登录成功
                    parent.location.href = ctx+'/sys/index'; //跳转到index页面
                } else{
                    layer.msg(result.msg, {icon: 5});
                    refreshCode();
                }
            }
        });
        return false;
    })
})
