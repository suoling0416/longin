<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.5.4.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.5.4.2/themes/icon.css">
    <script type="text/javascript" src="/jquery-easyui-1.5.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.5.4.2/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.5.4.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<!--背景-->
<div class="wel" id="background-4"></div>
<!--左右两边云-->
<div class="wel" id="box">
    <div class="box-1 lefp"></div>
    <div class="box-1">
        <div class="righp"></div>
    </div>
</div>
<!--荧光点点-->
<div class="wel" id="git"></div>
<!--登录注册表-->
<div class="wel" id="from">
    <div class="box-2 le-1">
        <form id="loginForm" method="get">
            <div class="flrg">
                <h3>登录</h3>
                <div class="a">
                    <!--<label>账号：</label>-->
                    <input name="uname" type="text" class="in-1" placeholder="请输入账号">
                </div>
                <div class="a">
                    <!--<label>密码：</label>-->
                    <input name="upass" type="password" class="in-1" placeholder="请输入密码">
                </div>
                <div class="a">
                    <!--<label>密码：</label>-->
                    <input type="text" onblur="codeMethhod(this)"  name="checkCode"  class="in-2" placeholder="请输入验证码">
                    <img class="in-3" onclick="sxCheck(this)" alt="刷新验证码" src="readCheckImg"/>
                </div>
                <div class="a">
                    <button type="button" onclick="loginInfo()">登录</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="button" onclick="toRegister()">注册</button>
                </div>
                <div class="a">
                    <div class="hr"></div>
                </div>
                <div class="a">
                    <a href="#">忘记密码？</a>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    //刷新验证码
    function sxCheck(obj){
        obj.src="<%=path%>/user/readCheckImg.do?"+(Math.random()*9000+1000);
    }

    //验证验证码
    function codeMethhod(obj) {
        $.ajax({
            url:"checkCodeMethod",
            data:{"checkCode":obj.value},
            dataType:'json',
            type:"post",
            success:function (data) {
                if (data.errorCode==200){
                    alert("验证失败");
                }
                if (data.errorCode==201){
                    alert("验证失败");
                }
            },
            error:function () {
                alert("验证验证码失败");
            }
        })
    }

    //点击登录
    function loginInfo() {
        $.ajax({
            url:"loginInfo",
            data:$("#loginForm").serializeArray(),
            type:"post",
            dataType:"json",
            success:function (data) {
                if (data.errorCode==201){
                    alert("验证码错误");
                    return;
                }
                if(data.errorCode==202||data.errorCode==203){
                    alert("账号或者密码错误");
                    return;
                }
                if(data.errorCode==200){
                    alert("登陆成功");
                    location.href="indexs";
                    return;
                }
            },
            error:function(){
                alert("登陆失败，请管理联系员")
            }
        })
    }

    //跳转注册页面
    function toRegister() {
        location.href="goRegister";
    }


    //跳转登陆页面
   /* function toLogin() {
        location.href="goLogin";
    }*/

</script>

</body>
</html>