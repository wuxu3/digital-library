<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/dlib/back/static/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/dlib/back/static/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/dlib/back/static/themes/IconExtension.css">
    <link href="/dlib/back/static/js/easyui.css" rel="stylesheet" type="text/css">
    <link href="/dlib/back/static/js/easyui_animation.css" rel="stylesheet" type="text/css">
    <link href="/dlib/back/static/js/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/dlib/back/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/dlib/back/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/dlib/back/static/js/jquery-easyui-validater.js"></script>
    <script type="text/javascript" src="/dlib/back/static/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/dlib/back/static/js/echarts.min.js"></script>
    <link rel="stylesheet" href="/dlib/back/static/kindeditor/themes/default/default.css"/>
    <link rel="stylesheet" href="/dlib/back/static/kindeditor/plugins/code/prettify.css"/>
    <script charset="utf-8" src="/dlib/back/static/kindeditor/kindeditor.js"></script>
    <script charset="utf-8" src="/dlib/back/static/kindeditor/zh-CN.js"></script>
    <script charset="utf-8" src="/dlib/back/static/kindeditor/plugins/code/prettify.js"></script>
    <script type="text/javascript" src="/dlib/back/static/js/jquery.insdep-extend.min.js"></script>

    <script type="text/javascript">

        $(function () {
            $(function () {
                $.get("/dlib/back/static/json/menu.json", function (result) {

                    $.each(result, function (idx, menu) {

                        var content = "";
                        $.each(menu.child, function (index, ch) {

                            var cont = ch.name + "#" + ch.href + "#" + ch.iconCls;

                            content += "<div style='width:90%;margin:5px 0px 5px 0px;border:1px #95B8E7 solid' class='easyui-linkbutton' data-options=\"plain:true,iconCls:'" + ch.iconCls + "'\" onclick=\"skip('" + cont + "')\">" + ch.name + "</div><br/>"

                        });

                        $("#accord").accordion('add', {

                            title: menu.name,
                            iconCls: menu.iconCls,
                            content: content,
                        })
                    });
                }, "JSON");
            });

        });

        function skip(content) {

            console.log(content);

            var cont = content.split("#");

            if ($("#main").tabs('exists', cont[0])) {

                $("#main").tabs('select', cont[0])

            } else {

                $("#main").tabs('add', {
                    title: cont[0],
                    href: cont[1],
                    iconCls: cont[2],
                    closable: true,
                    tools: [{
                        iconCls: 'icon-reload',
                        handler: function () {

                            var tab = $("#main").tabs('getSelected');

                            tab.panel('refresh');

                        }
                    }],
                });
            }
        }


        function register() {

            $("#adminRegister").dialog({
                width: 600,
                height: 500,
                href: "/dlib/back/admin/register.jsp",
                buttons: [{
                    text: '立即注册',
                    iconCls: 'icon-save',
                    handler: adminRegister,  //点击事件：点击提交表单的内容到后台
                }, {
                    text: '关闭',
                    iconCls: 'icon-cancel',
                    handler: function () {

                    }
                }]
            })
        }


        function adminRegister() {

            $("#adminInput").form('submit', {

                url: '/dlib/admin/register',
                onSubmit: function () {

                    return $("#adminInput").form('validate');

                },
                success: function (data) {

                    var result = eval("(" + data + ")");

                    $.messager.show({

                        title: '提示消息',
                        msg: result.msg,

                    });

                    $("#adminRegister").dialog('close', true);
                    $("#headder").panel({

                        href: '/dlib/back/main/header.jsp',
                    });
                }
            })
        }


        function login() {

            $("#adminLogin").dialog({
                width: 350,
                height: 250,
                href: "/dlib/back/admin/login.jsp",
                buttons: [{
                    text: '立即登录',
                    iconCls: 'icon-save',
                    handler: adminLogin,  //点击事件：点击提交表单的内容到后台
                }, {
                    text: '关闭',
                    iconCls: 'icon-cancel',
                    handler: function () {

                    }
                }]
            })
        }

        function adminLogin() {

            $("#adminlog").form('submit', {

                url: '/dlib/admin/login',
                onSubmit: function () {

                    return $("#adminlog").form('validate');

                },
                success: function (data) {

                    var result = eval("(" + data + ")");

                    $.messager.show({

                        title: '提示消息',
                        msg: result.msg,

                    });

                    $("#adminLogin").dialog('close', true);
                    $("#headder").panel({

                        href: '/dlib/back/main/header.jsp',
                    });

                }
            })
        }

        function loginout() {

            location.href = '/dlib/admin/loginout';

        }

    </script>
</head>
<body class="easyui-layout" data-options="split:false">

<%--头部--%>
<div id="headder" data-options="region:'north',split:false,href:'/dlib/back/main/header.jsp'"
     style="height:100px;"></div>

<%--尾部--%>
<div data-options="region:'south',split:false" style="height:100px;"></div>

<%--左边栏--%>
<div data-options="region:'west',split:false,width:250">
    <div id="accord" class="easyui-accordion" data-options="fit:true"></div>

</div>

<div data-options="region:'center'">
    <div id="main" class="easyui-tabs" data-options="fit:true"></div>

</div>

<div id="adminRegister"></div>

<div id="adminLogin"></div>

</body>
</html>
