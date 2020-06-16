<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8"%>
<%--
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
--%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<%--
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
--%>
<!DOCTYPE html>
<html>
    <head>
            <meta charset="UTF-8">
            <title>管理平台</title>
            <meta name="renderer" content="webkit|ie-comp|ie-stand">
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
            <meta http-equiv="Cache-Control" content="no-siteapp" />
            <link rel="stylesheet" href="layui/css/layui.css" />
            <style>
                iframe{width: 100%;height: 100%;}
            </style>

    <script>
        var ctx = "${ctx }";
    </script>
        </head>
    <body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <ul class="layui-nav layui-layout-left">
          
        </ul>
        <ul class="layui-nav layui-layout-right">
          <li class="layui-nav-item">
            <a href="javascript:;">
              <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                               贤心
            </a>
            <dl class="layui-nav-child">
              <dd><a href="">基本资料</a></dd>
              <dd><a href="">安全设置</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
      </div>
      
      <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
          <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
          <ul id="left_nav" class="layui-nav layui-nav-tree"  lay-filter="test">
            <!--<li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">系统管理</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">用户管理</a></dd>
            <dd><a href="javascript:;">角色管理</a></dd>
            <dd><a href="javascript:;">权限管理</a></dd>
            <dd><a href="">菜单管理</a></dd>
          </dl>
        </li>-->
          </ul>
        </div>
      </div>
      
      <div class="layui-body" style="overflow-y: hidden;">
        <!-- 内容主体区域 -->
        <div class="layui-tab" lay-filter="demo" lay-allowClose="true">
          <ul class="layui-tab-title">
            <!--<li class="layui-this" lay-id="111" >系统管理</li>
        <li lay-id="222">角色管理</li>
        <li lay-id="333">权限分配</li>
        <li lay-id="444">用户管理</li>
        <li lay-id="555">订单管理</li>-->
          </ul>
          <div class="layui-tab-content" >
            <!--<div class="layui-tab-item layui-show">1</div>
        <div class="layui-tab-item">
            <iframe src="pages/sys/role.html"></iframe>
        </div>
        <div class="layui-tab-item">3</div>
        <div class="layui-tab-item">
            <iframe src="pages/sys/user.html"></iframe>
        </div>
        <div class="layui-tab-item">5</div>-->
          </div>
        </div>
      </div>
      
      <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
      </div>
</div>

<script type="text/javascript" src="${ctx }/js/jquery-3.3.1.min.js" ></script>

<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
<script>
    // var base="http://localhost:8080";
    // var get_nav=base+"/sys/getMenus";
    var layer;


    network();
    /*
     * @todo 重新计算iframe高度
     */
    function FrameWH() {
        var h = $(window).height() - 164;
        //console.log("高度问题=="+h);
        $("iframe").css("height", h + "px");
    }


    function network(){
        $.ajax({
            url:ctx + "/sys/getMenus",
            type: "post",
            contentType: "application/json",
            dataType: 'json',
            async: true,
            success: function(res) {
                //console.log("首页请求结果=="+JSON.stringify(res));
                if (res.code===0) {
                    console.log("请求数据成功");
                    loadnav(res.data);
                } else{
                    layer.msg('暂无数据');
                }
            },
            error:function() {
                layer.msg("请求失败！");
            }
        });
    }

    function loadnav (navdata) {
        for (var i=0;i<navdata.length;i++) {
            var first_menu = navdata[i];
            var childMenus= first_menu.list;
            //console.log("子菜单=="+JSON.stringify(childMenus));
            if(childMenus==null||childMenus==undefined||childMenus==""){
                //console.log("没有子菜单=="+first_menu.name);
                loadTopMenu(first_menu);
            }else{
                console.log("有子菜单=="+first_menu.title);
                loadHasChildMenu(first_menu);
            }
        }
        //列表添加完后再次执行渲染
        xuanran();
    }


    //加载带有子菜单的
    function loadHasChildMenu(data){
        var fragment = document.getElementById("left_nav");
        var list = document.createElement('li');

        list.className="layui-nav-item layui-nav-itemed";

        var child_one_Html='<a href="javascript:;">'+data.title+'</a>';

        var childmenus= data.list;

        var child_two_Html='<dl class="layui-nav-child">';
        var childHtmls="";
        for (var i=0;i<childmenus.length;i++) {
            var childmenu = childmenus[i];
            console.log("childmenu=" + JSON.stringify(childmenu));

            console.log("title=" + childmenu.title);
            console.log("href=" + childmenu.href);

            var childHtml = '<dd><a class="site-demo-active"  data-title='+childmenu.title+' data-menuId='+childmenu.menuId+' data-src='+childmenu.href+'>'+childmenu.title+'</a></dd>';

            childHtmls +=childHtml;
        }
        child_two_Html +=childHtmls+'</dl>';
        list.innerHTML=child_one_Html+child_two_Html;
        fragment.appendChild(list);
        console.log("2.=多级菜单列表加载完毕");
    }
    //加载顶级菜单(没有子菜单)
    function loadTopMenu(data){
        var fragment = document.getElementById("left_nav");
        var list = document.createElement('li');
        list.className="layui-nav-item";
        list.innerHTML='<a class="site-demo-active" data-title='+data.title+' data-menuId='+data.menuId+' data-src='+data.href+'>'+data.title+'</a>';
        fragment.appendChild(list);
        console.log("1.=顶级菜单列表加载完毕");
    }

    function xuanran() {
        layui.use(['element','layer'], function(){
            var element = layui.element;
            layer=layui.layer;

            var layFilter = $("#left_nav").attr('lay-filter');
            element.render('nav', layFilter);

            var active = {
                //在这里给active绑定几项事件，后面可通过active调用这些事件
                tabAdd: function (url, id, name) {
                    console.log("url="+url+",id =" + id+",name =" + name);

                    //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                    var body_url=  "${ctx }"+ url;
                    console.log("要切换的页面地址="+body_url);
                    element.tabAdd('demo', {
                        title: name,
                        content: '<iframe data-frameid="' + id + '" scrolling="auto"  src="' + body_url + '" style="width:100%;height:99%;"></iframe>',
                        id: id //规定好的id
                    })
                    FrameWH();  //计算ifram层的大小
                },
                tabChange: function (id) {
                    //切换到指定Tab项
                    element.tabChange('demo', id); //根据传入的id传入到指定的tab项
                },
                tabDelete: function (id) {
                    element.tabDelete("demo", id);//删除
                }
            };
            // //当点击有site-demo-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
            $('.site-demo-active').on('click', function () {
                var dataid = $(this);
                if ($(".layui-tab-title li[lay-id]").length <= 0) {
                    //如果比零小，则直接打开新的tab项
                    console.log("没有tab页，新建tab页");
                    active.tabAdd(dataid.attr("data-src"), dataid.attr("data-menuId"),dataid.attr("data-title"));
                } else {
                    //否则判断该tab项是否以及存在
                    var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                    $.each($(".layui-tab-title li[lay-id]"), function () {
                        //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                        if ($(this).attr("lay-id") == dataid.attr("data-menuId")) {
                            console.log("如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开");
                            isData = true;
                        }
                    })
                    if (isData == false) {
                        //标志为false 新增一个tab项
                        console.log("新增一个tab项")
                        active.tabAdd(dataid.attr("data-src"), dataid.attr("data-menuId"),dataid.attr("data-title"));
                    }
                }
                //最后不管是否新增tab，最后都转到要打开的选项页面上
                active.tabChange(dataid.attr("data-menuId"));
            });
        })
        console.log("==列表添加完后再次执行渲染");
    }
</script>        
    </body>
</html>
