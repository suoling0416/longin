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

    <style>
        .disabled {
            pointer-events: none;
            filter: alpha(opacity=50); /*IE滤镜，透明度50%*/
            -moz-opacity: 0.5; /*Firefox私有，透明度50%*/
            opacity: 0.5; /*其他，透明度50%*/
        }
    </style>
</head>
<body>
<!--背景-->
<div class="wel" id="background-3"></div>
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
    <div class="box-2 le-2">
        <form id="registerForm">
            <div class="flrg-1">
                <h3>注册</h3>
                <div class="a">
                    <input onblur="checkName(this)" name="uname" id="uname" type="text" class="in-1" placeholder="用户名"><span id="nameSpan"></span>
                </div>
                <div class="a">
                    <input onblur="checkPass(this)" id="pass" name="upass" type="password" class="in-1" placeholder="密码"><span id="passSpan"></span>
                </div>
                <div class="a">
                    <input onblur="checkOkPass(this)" id="dbPassword" type="password" class="in-1" placeholder="确认密码"><span id="dbPassSpan"></span>
                </div>
                <div class="a">
                    <input type="text" name="userPhone" id="userPhone" onblur="checkPhone(this)" class="in-1" placeholder="手机号" id="phone"/>
                    <span id="phoneSpan"></span>
                </div>
                <div class="a">
                    <input type="text" name="msgCode" id="msgCode" onblur="checkMsgCode(this)" class="in-2" placeholder="验证验证码" />
                    <button type="button" class="butt disabled" id="sendCheck" onclick="getCheckCode()">发送验证码</button>
                </div>
                <div class="a">
                    <button type="button" id="submit" onclick="checkInfo()" class="button">注册</button>
                </div>
                <div class="a">
                    <div class="hr"></div>
                </div>
                <div class="a">
                    <a href="login">已有账号？点击登录</a>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    var submitStatus = false;
    //注册
    function checkInfo() {
        alert(submitStatus);
        if(submitStatus===true){
            $.ajax({
                url:"toLogin",
                data:$("#registerForm").serializeArray(),
                dataType:"json",
                type:"post",
                success:function(data){
                    if(data.errorCode>=1){
                        alert("注册成功");
                        location.href="goLogin";
                        return;
                    }
                    if(data.errorCode<1){
                        alert("注册失败");
                        return;
                    }
                },
                error:function () {
                    alert("注册失败，请及时联系管理员");
                }
            })
        }
    }


    //验证账号是否存在
    function checkName(obj){
        var test1 = /^[\w]{1,6}$/;
        if(test1.test($(obj).val())){
            $.ajax({
                url:"findByName",
                data:{"uname":obj.value},
                type:"post",
                dataType:"json",
                success:function (data) {
                    if(data.errorCode===200){
                        $("#nameSpan").html("<font color='green'>该账号可注册</font>");
                        submitStatus=true;
                    }
                    if(data.errorCode===201){
                        $("#nameSpan").html("<font color='red'>账号已存在</font>");
                        submitStatus=false;
                    }
                }
            })
        }else{
            $("#nameSpan").html("<font color='red'>×</font>");
            submitStatus=false;
        }
    }
    //验证密码
    function checkPass(obj) {
        var test2 = /^[\w@#*]{1,6}$/;
        if(test2.test($(obj).val())){
            $("#passSpan").html("<font color='green'>密码输入正确</font>");
            submitStatus=true;
        }else{
            $("#passSpan").html("<font color='red'>密码错误</font>");
            submitStatus=false;
        }
    }

    //确认密码验证
    function checkOkPass(obj){
        var test2 = $("#pass").val();
        if(test2===''||test2===null){
            $("#dbPassSpan").html("<font color='red'>请先输入密码</font>");
            submitStatus=false;
        }else{
            if(test2!==obj.value){
                $("#dbPassSpan").html("<font color='red'>密码确认失败</font>");
                submitStatus=false;
            }else{
                $("#dbPassSpan").html("<font color='green'>确认密码正确</font>");
                submitStatus=true;
            }
        }
    }

    //手机号
    function checkPhone(obj){
        // 验证手机号码是否ok
        var test = /^1[3456789]\d{9}$/;
        if (test.test($(obj).val())){
            // 将获取手机验证码的A标签放开
            $("#sendCheck").removeClass("disabled");
        }else{
            $("#sendCheck").addClass("disabled");
        }
    }

    //获取验证码（发送验证码
    function getCheckCode() {
        var phoneNum = $("#userPhone").val();
        $.ajax({
            url:"getCheckCode",
            data:{"phoneNum":phoneNum},
            dataType:"json",
            type:"post",
            success:function (data) {
                if(data.error==0){
                    alert("信息发送成功");
                    return;
                }
                if(data.error!=0){
                    alert(data.msg);
                    return;
                }
            },
            error:function (data) {
                alert("消息失败 请联系管理员");
            }
        })
    }

    //点击失去焦点 验证验证码是否正确
    function checkMsgCode(obj){
        $.ajax({
            url:"checkMsgCode",
            data:{"msgCode":$(obj).val()},
            dataType:"json",
            type:"post",
            success:function (data) {
                if(data.error==0){
                    alert("验证码成功");
                    return;
                }
                if(data.error!=0){
                    alert(data.msg);
                    return;
                }
            },
            error:function (data) {
                alert("验证码失败 请联系管理员");
            }
        })
    }

</script>

</body>
</html>