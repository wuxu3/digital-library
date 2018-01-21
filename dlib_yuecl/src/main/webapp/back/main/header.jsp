<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="text-align: center">

    <h1>数字图书馆后台管理系统</h1>

    <c:if test="${sessionScope.admin==null}">


        <a style="margin-left:90%" class="easyui-linkbutton" data-options="plain:true" onclick="login()">登录</a>
        <a class="easyui-linkbutton" data-options="plain:true" onclick="register()">注册</a>

    </c:if>

    <c:if test="${sessionScope.admin!=null}">


        <a style="margin-left:90%">欢迎您：${sessionScope.admin.realname}</a>

        <a class="easyui-linkbutton" data-options="plain:true" onclick="loginout()">登出</a>


    </c:if>

</div>

