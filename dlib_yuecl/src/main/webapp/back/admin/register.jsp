<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: center;margin-top:5px">
    <h2 style="text-align: center">欢迎注册管理员</h2>

    <form method="post" id="adminInput">

        <div style="margin-top:30px">
            <input name="username" class="easyui-textbox"
                   data-options="width:300,height:35,required:true,iconAlign:'left',iconCls:'icon-man',prompt:'请输入用户名'">
        </div>
        <div style="margin-top:10px">

            <input name="realname" class="easyui-textbox"
                   data-options="width:300,height:35,required:true,iconAlign:'left',iconCls:'icon-man',prompt:'请输入真实名'">

        </div>
        <div style="margin-top:10px">

            <input id="pwd" name="password" class="easyui-passwordbox"
                   data-options="width:300,height:35,required:true,iconAlign:'left',prompt:'请设置您的登录密码'">

        </div>
        <div style="margin-top:10px">

            <input class="easyui-passwordbox"
                   data-options="width:300,height:35,required:true,iconAlign:'left',prompt:'再次确认密码'" required="required"
                   validType="equalTo['#pwd']">

        </div>
        <div style="margin-top:10px">

            <input name="bir" class="easyui-datebox"
                   data-options="width:300,height:35,required:true,iconAlign:'left',prompt:'输入出生年月'">

        </div>
        <div style="margin-top:10px;margin-left:24%">
            <table>
                <tr>
                    <td><input name="code" class="easyui-textbox"
                               data-options="width:180,height:35,required:true,prompt:'输入验证码'"></td>
                    <td><img src="/dlib/admin/getImage"></td>
                </tr>
            </table>
        </div>


    </form>

</div>
</body>
</html>
