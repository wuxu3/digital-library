<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div style="text-align: center;margin-top:5px">
    <h2 style="text-align: center">欢迎登录</h2>

    <form method="post" id="adminlog">

        <div style="margin-top:30px">
            <input name="username" class="easyui-textbox"
                   data-options="required:true,iconAlign:'left',iconCls:'icon-man',prompt:'请输入用户名'">
        </div>

        <div style="margin-top:10px">

            <input id="pwd" name="password" class="easyui-passwordbox"
                   data-options="required:true,iconAlign:'left',prompt:'请输入登录密码'">

        </div>

    </form>

</div>

