<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">

    $(function () {
        $("#banners").datagrid({
            url: '/dlib/banner/allBanner',
            columns: [[
                {title: "轮播图ID", field: "id", width: 300},
                {title: "轮播图名", field: "name", width: 200},
                {
                    title: "轮播图路径", field: "url", width: 200,
                    formatter: function (value, row, index) {

                        return "<img style='width:80px;height:100px;' border='1' src='" + row.url + "'/>";


                    }

                },
                {title: "上传日期", field: "uploaddate", width: 200},
                {
                    title: "操作", field: "option", width: 300,
                    formatter: function (value, row, index) {
                        return "<a class='del' data-options=\"plain:true,iconCls:'icon-20130408025545236_easyicon_net_30'\" onclick=\"deleteBanner('" + row.id + "')\">[删除]</a>&nbsp;&nbsp;&nbsp;<a class='update'  data-options=\"plain:true,iconCls:'icon-edit',\" onclick=\"updateBannerDialog('" + row.id + "')\">[修改]</classa>";
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                $(".del").linkbutton();
                $(".update").linkbutton();

            },
            nowrap: true,
            striped: true,//是否现显示斑马线
            autoRowHeight: true,
            fitColumns: true,
            fit: true,
            fitColumns: true,
            pagination: true,
            pageList: [3, 6, 9, 12],
            pageSize: 3,
            toolbar: '#tb2',

        });
    });


    function deleteBanner(id) {

        $.messager.confirm('提示', '是否确定要删除？', function (r) {

            //确认删除
            if (r) {

                $.getJSON("/dlib/banner/delBanner", {"id": id}, function (result) {


                    $.messager.show({

                        title: '提示',
                        msg: result,

                    });

                });

                $('#banners').datagrid('reload');
            }

        });

    }


    function updateBannerDialog(id) {

        $("#updateDia").dialog({

            href: '/dlib/back/banner/updateBanner.jsp?id=' + id,
            width: 400,
            height: 400,
            buttons: [{
                text: '修改轮播图',
                iconCls: 'icon-application_edit',
                handler: updateBanner,  //点击事件：点击提交表单的内容到后台
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {

                }
            }]
        });
    }


    function updateBanner() {
        $("#updateBannerInput").form('submit', {
            url: '/dlib/banner/updateBanner',
            onSubmit: function () {

                return $("#updateBannerInput").form('validate');

            },
            success: function (data) {

                $.messager.show({

                    title: '提示消息',
                    msg: "修改成功",

                });

                $("#updateDia").dialog('close', true);
                $("#banners").datagrid('reload');
            }


        })

    }


    function addbannerDialog() {

        $("#addDia").dialog({
            title: '添加轮播图',
            href: '/dlib/back/banner/addBanner.jsp',
            width: 400,
            height: 400,
            buttons: [{
                text: '增加轮播图',
                iconCls: 'icon-add',
                handler: addBanner,  //点击事件：点击提交表单的内容到后台
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {

                }
            }]
        });
    }


    function addBanner() {
        $("#addBannerInput").form('submit', {

            url: '/dlib/banner/uploadBanner',
            onSubmit: function () {

                return $("#addBannerInput").form('validate');

            },
            success: function (data) {

                $.messager.show({

                    title: '提示消息',
                    msg: data,

                });

                $("#addDia").dialog('close', true);
                $("#banners").datagrid('reload');
            }


        });


    }

    function qq(value, name) {

        $("#banners").datagrid({

            url: "/dlib/banner/queryAllByIndex?value=" + value,
            columns: [[
                {title: "轮播图ID", field: "id", width: 300},
                {title: "轮播图名", field: "name", width: 200},
                {
                    title: "轮播图路径", field: "url", width: 200,
                    formatter: function (value, row, index) {

                        return "<img style='width:80px;height:100px;' border='1' src='" + row.url + "'/>";


                    }


                },
                {
                    title: "操作", field: "option", width: 300,
                    formatter: function (value, row, index) {
                        return "<a class='del' data-options=\"plain:true,iconCls:'icon-20130408025545236_easyicon_net_30'\" onclick=\"deletechap('" + row.id + "')\">[删除]</a>&nbsp;&nbsp;&nbsp;<a class='update'  data-options=\"plain:true,iconCls:'icon-edit',\" onclick=\"updatechapDialog('" + row.id + "')\">[修改]</classa>";
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                $(".del").linkbutton();
                $(".update").linkbutton();

            },
            nowrap: true,
            striped: true,//是否现显示斑马线
            autoRowHeight: true,
            fitColumns: true,
            fit: true,
            fitColumns: true,
            pagination: true,
            pageList: [3, 6, 9, 12],
            pageSize: 3,
            toolbar: '#tb6',

        });
    }

    /*console.log(value);

    if($("#main").tabs('exists',"全文检索")){

        $("#main").tabs('select',"全文检索")

    }else{

        $("#main").tabs('add',{
            title:'全文检索',
            href:encodeURI('/dlib/back/banner/banner.jsp?value='+encodeURI(value)),
            iconCls:'icon-man',
            closable:true,
            tools:[{
                iconCls:'icon-reload',
                handler:function(){

                    var tab=$("#main").tabs('getSelected');

                    tab.panel('refresh');

                }
            }],
        });
    }



}*/

</script>


<div id="banners"></div>

<div id="tb2">
    <a class="easyui-linkbutton" onclick="addbannerDialog()" data-options="iconCls:'icon-add',plain:true">添加</a>
    <%--搜索框--%>
    <input id="ss" class="easyui-searchbox"
           data-options="searcher:qq,prompt:'请输入查询条件....',menu:'#mm',width:400"/>
    <div id="mm" data-options="">
        <div data-options="name:'name',">图片名</div>
        <div data-options="name:'age'">id</div>
    </div>
</div>
<div id="addDia"></div>
<div id="updateDia"></div>