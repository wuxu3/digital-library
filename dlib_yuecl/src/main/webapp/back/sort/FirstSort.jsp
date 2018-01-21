<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>

<script type="text/javascript">
    $(function () {
        $("#sorts").datagrid({
            url: '/dlib/sort/allSorts',
            columns: [[
                {title: "分类ID", field: "id", width: 300},
                {title: "二级分类名", field: "name", width: 200},
                {
                    title: "所属一级分类", field: "firstSort", formatter: function (value, row, index) {
                    if (value.name) {
                        return value.name;
                    } else {
                        return '';
                    }
                }, width: 200
                },
                {
                    title: "操作", field: "option", width: 300,
                    formatter: function (value, row, index) {
                        return "<a class='del' data-options=\"plain:true,iconCls:'icon-20130408025545236_easyicon_net_30'\" onclick=\"deleteSort('" + row.id + "')\">[删除]</a>&nbsp;&nbsp;&nbsp;<a class='update'  data-options=\"plain:true,iconCls:'icon-edit',\" onclick=\"updateSortDialog('" + row.id + "')\">[修改]</classa>";
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
            toolbar: '#tb',

        });
    });

    function deleteSort(id) {

        $.messager.confirm('提示', '是否确定要删除？', function (r) {

            //确认删除
            if (r) {

                $.getJSON("/dlib/sort/delSort", {"id": id}, function (result) {


                    $.messager.show({

                        title: '提示',
                        msg: result,
                    });

                });

                $("#sorts").datagrid('reload');
            }
        });
    }


    function updateSortDialog(id) {

        $("#updateSortDia").dialog({

            title: '修改分类信息',
            href: '/dlib/back/sort/updateSort.jsp?id=' + id,
            width: 400,
            height: 300,
            buttons: [{
                text: '修改信息',
                iconCls: 'icon-delete',
                handler: updateSort,  //点击事件：点击提交表单的内容到后台
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {

                }
            }]

        })
    }

    function updateSort() {

        $("#updateInput").form('submit', {

            url: '/dlib/sort/modify',
            onSubmit: function () {

                return $("#updateInput").form('validate');

            },
            success: function (data) {

                $.messager.show({

                    title: '提示消息',
                    msg: data,

                });

                $("#updateSortDia").dialog('close', true);
                $("#sorts").datagrid('reload');
            }
        });
    }

    function addsortDialog() {

        $("#addSortDia").dialog({

            title: '增加分类信息',
            href: '/dlib/back/sort/addSort.jsp',
            width: 400,
            height: 300,
            buttons: [{
                text: '增加信息',
                iconCls: 'icon-add',
                handler: addSort,  //点击事件：点击提交表单的内容到后台
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {

                }
            }]
        });
    }

    function addSort() {

        $("#addSortInput").form('submit', {

            url: '/dlib/sort/addSort',
            onSubmit: function () {

                return $("#addSortInput").form('validate');

            },
            success: function (data) {

                $.messager.show({

                    title: '提示消息',
                    msg: data,

                });

                $("#addSortDia").dialog('close', true);
                $("#sorts").datagrid('reload');
            }


        });
    }


    function qq() {

    }


</script>

<div id="sorts"></div>

<div id="tb">
    <a class="easyui-linkbutton" onclick="addsortDialog()" data-options="iconCls:'icon-add',plain:true">添加</a>
    <%--搜索框--%>
    <input id="ss" class="easyui-searchbox"
           data-options="searcher:qq,prompt:'请输入查询条件....',menu:'#mm',width:400"/>
    <div id="mm" data-options="">
        <div data-options="name:'name',">姓名</div>
        <div data-options="name:'age'">年龄</div>
    </div>
</div>

<div id="updateSortDia"></div>
<div id="addSortDia"></div>
