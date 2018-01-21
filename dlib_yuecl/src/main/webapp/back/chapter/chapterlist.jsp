<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<script>

    $(function () {
        $("#chaps").datagrid({
            url: '/dlib/chap/allChapter',
            columns: [[
                {title: "章节ID", field: "id", width: 300},
                {title: "章节名", field: "name", width: 200},
                {title: "章节路径", field: "url", width: 200},
                {
                    title: "所属图书", field: "book",
                    formatter: function (value, row, index) {
                        if (value.name) {

                            return value.name;
                        } else {

                            return '';
                        }

                    },

                    width: 200
                },
                {title: "上传日期", field: "publishDate", width: 200},
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
            toolbar: '#tb3',

        });
    });

    function deletechap(id) {


        $.messager.confirm('提示', '是否确定要删除？', function (r) {

            //确认删除
            if (r) {

                $.getJSON("/dlib/chap/delChap", {"id": id}, function (result) {


                    $.messager.show({

                        title: '提示',
                        msg: result,

                    });

                });

                $('#chaps').datagrid('reload');
            }

        });


    }

    function updatechapDialog(id) {
        $("#updateDia").dialog({

            href: '/dlib/back/chapter/updateChap.jsp?id=' + id,
            width: 400,
            height: 400,
            buttons: [{
                text: '修改章节信息章节',
                iconCls: 'icon-application_edit',
                handler: updateChap,  //点击事件：点击提交表单的内容到后台
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {

                }
            }]
        });


        function updateChap() {

            $("#updateChapInput").form('submit', {
                url: '/dlib/chap/updateChapter',
                onSubmit: function () {

                    return $("#updateChapInput").form('validate');

                },
                success: function (data) {

                    $.messager.show({

                        title: '提示消息',
                        msg: '修改成功 ',
                    });
                    $("#updateDia").dialog('close', true);
                    $("#chaps").datagrid('reload');
                }
            })

        }


    }

    function addchapDialog() {

        $("#addChap").dialog({

            href: '/dlib/back/chapter/addChap.jsp',
            width: 400,
            height: 400,
            buttons: [{
                text: '添加章节',
                iconCls: 'icon-add',
                handler: addChap,  //点击事件：点击提交表单的内容到后台
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {

                }
            }]
        });
    }

    function addChap() {
        $("#addChapInput").form('submit', {
            url: '/dlib/chap/addChap',
            onSubmit: function () {

                return $("#addChapInput").form('validate');

            },
            success: function (data) {

                $.messager.show({

                    title: '提示消息',
                    msg: '添加成功',
                });
                $("#addChap").dialog('close', true);
                $("#chaps").datagrid('reload');
            }


        });


    }

    function qq() {
    }

</script>


<div id="chaps"></div>
<div id="tb3">
    <a class="easyui-linkbutton" onclick="addchapDialog()" data-options="iconCls:'icon-add',plain:true">添加</a>
    <%--搜索框--%>
    <input id="ss" class="easyui-searchbox"
           data-options="searcher:qq,prompt:'请输入查询条件....',menu:'#mm',width:400"/>
    <div id="mm" data-options="">
        <div data-options="name:'name',">图片名</div>
        <div data-options="name:'age'">id</div>
    </div>
</div>
<div id="addChap"></div>
<div id="updateDia"></div>