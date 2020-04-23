<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.5.4.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/jquery-easyui-1.5.4.2/themes/icon.css">
    <script type="text/javascript" src="/jquery-easyui-1.5.4.2/jquery.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.5.4.2/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/jquery-easyui-1.5.4.2/locale/easyui-lang-zh_CN.js"></script>

</head>
<body class="easyui-layout">
    <table class="easyui-datagrid" style="width:400px;height:250px"
           data-options="fit:true,url:'findLog',method:'get',singleSelect:true">
        <thead>
        <tr>
            <th data-options="field:'name',width:100,align:'name'">用户名</th>
            <th data-options="field:'password',width:100,align:'password'">密码</th>
            <th data-options="field:'time',width:100,align:'center',formatter:time">登录时间</th>
        </tr>
        </thead>
    </table>

</body>
<script>
    function time(value,rows,index){
        debugger
        if(value==null){
            return value;
        }else{
            return formatDate(new Date(value));
        }
    }
    function formatDate(date){
        var da = new Date(date);
        var y = da.getFullYear();
        var m = da.getMonth()+1;
        var d = da.getDate();
        return y+'-'+m+'-'+d;
    }
</script>
</html>