<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>
<script type="text/javascript">

    $.fn.datebox.defaults.formatter = function (date) {
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        return y + '/' + m + '/' + d;
    }
    $.fn.datebox.defaults.parser = function (s) {
        if (!s) return new Date();
        var ss = s.split('/');
        var y = parseInt(ss[0], 10);
        var m = parseInt(ss[1], 10);
        var d = parseInt(ss[2], 10);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
            return new Date(y, m - 1, d);
        } else {
            return new Date();
        }
    };

    $(function () {
        $("#books").datagrid({
            url: '/dlib/book/allBooks',
            columns: [[
                {title: "图书ID", field: "id", width: 100},
                {
                    title: "图书名", field: "name", width: 200,
                    formatter: function (value, row, index) {
                        return "<a onclick=\"qChapter('" + row.id + "#" + row.name + "')\">" + value + "</a>"
                    }

                },
                {title: "上架时间", field: "publishdate", width: 200},
                {
                    title: "图书分类", field: "sort", formatter: function (value, row, index) {
                    if (value) {
                        return value.name;
                    } else {
                        return '';
                    }
                }, width: 150
                },
                {title: "阅读人数", field: "readNumber", width: 100},
                {
                    title: "封面图片", field: "photo", width: 200,
                    formatter: function (value, row, index) {

                        return "<img style='width:80px;height:100px;' border='1' src='" + row.photo + "'/>";


                    }

                },
                {title: "作者", field: "author", width: 150},
                {title: "isbn", field: "isbn", width: 200},
                {title: "出版社", field: "publish", width: 200},
                {
                    title: "书籍状态", field: "status", formatter: function (value, row, index) {
                    if (value == 1) {
                        return "上架中";
                    } else {
                        return '已下架';
                    }
                }, width: 100
                },
                {
                    title: "操作", field: "option", width: 200,
                    formatter: function (value, row, index) {
                        return "<a class='del' data-options=\"plain:true,iconCls:'icon-20130408025545236_easyicon_net_30'\" onclick=\"deleteBook('" + row.id + "')\">[删除]</a><br/><a class='update'  data-options=\"plain:true,iconCls:'icon-edit',\" onclick=\"updateBookDialog('" + row.id + "')\">[修改]</a><br/><a class='detail'  data-options=\"plain:true,iconCls:'icon-edit',\" onclick=\"detailDialog('" + row.id + "')\">[详情]</a>";
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                $(".del").linkbutton();
                $(".update").linkbutton();
                $(".detail").linkbutton();

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
            toolbar: '#tb1',

        });
    });


    function qChapter(content) {

        var cont = content.split("#");


        if ($("#main").tabs('exists', cont[1])) {

            $("#main").tabs('select', cont[1])

        } else {

            $("#main").tabs('add', {
                title: cont[1],
                href: '/dlib/back/chapter/chapter.jsp?id=' + cont[0],
                iconCls: 'icon-man',
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

    function deleteBook(id) {

        $.messager.confirm('提示', '是否确定要删除？', function (r) {

            //确认删除
            if (r) {

                $.getJSON("/dlib/book/delBook", {"id": id}, function (result) {


                    $.messager.show({

                        title: '提示',
                        msg: result,

                    });

                });

                $('#books').datagrid('reload');
            }

        });


    }


    function updateBookDialog(id) {

        $("#updateBookDia").dialog({

            title: '修改图书信息',
            width: 400,
            height: 600,
            href: '/dlib/back/book/updateBooks.jsp?id=' + id,
            buttons: [{
                text: '确认修改',
                iconCls: 'icon-add',
                handler: updateBook,  //点击事件：点击提交表单的内容到后台
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {
                }
            }]
        });

    }


    function updateBook() {

        $("#bookupdateInput").form('submit', {

            url: '/dlib/book/updatebook',
            onSubmit: function () {

                return $("#bookupdateInput").form('validate');

            },
            success: function (data) {

                $.messager.show({

                    title: '提示消息',
                    msg: data,
                });
                $("#updateBookDia").dialog('close', true);
                $("#books").datagrid('reload');
            }
        });


    }


    function addBookDialog() {

        $("#addBookDia").dialog({

            title: '添加图书信息',
            width: 400,
            height: 600,
            href: '/dlib/back/book/addBooks.jsp',
            buttons: [{
                text: '增加图书',
                iconCls: 'icon-add',
                handler: addBook,  //点击事件：点击提交表单的内容到后台
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {
                }
            }]
        })
    }

    function addBook() {
        $("#bookAddInput").form('submit', {

            url: '/dlib/book/upload',
            onSubmit: function () {

                return $("#bookAddInput").form('validate');

            },
            success: function (data) {

                $.messager.show({

                    title: '提示消息',
                    msg: '添加成功',
                });
                $("#addBookDia").dialog('close', true);
                $("#books").datagrid('reload');
            }

        });
    }


    function detailDialog(id) {
        $("#bookDetail").dialog({
            title: '图书详细信息',
            width: 400,
            height: 300,
            href: '/dlib/back/book/detail.jsp?id=' + id,
            buttons: [{
                text: '修改封面',
                iconCls: 'icon-add',
                handler: changePhoto,  //点击事件：点击提交表单的内容到后台
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {
                }
            }]
        })
    }


    function changePhoto() {

        $("#detailInput").form('submit', {
            url: "/dlib/book/changePhoto",
            onSubmit: function () {

                return $("#detailInput").form('validate');

            },
            success: function (data) {

                $.messager.show({

                    title: '提示消息',
                    msg: '修改成功',
                });
                $("#bookDetail").dialog('close', true);
                $("#books").datagrid('reload');
                //$('#bookDetail').panel('refresh','/dlib/back/book/detail.jsp?id='+data);

            }

        });


    }


    function qq() {
    }

</script>

<div id="books"></div>

<div id="tb1">
    <a class="easyui-linkbutton" onclick="addBookDialog()" data-options="iconCls:'icon-add',plain:true">添加</a>
    <%--搜索框--%>
    <input id="ss" class="easyui-searchbox"
           data-options="searcher:qq,prompt:'请输入查询条件....',menu:'#mm',width:400"/>
    <div id="mm" data-options="">
        <div data-options="name:'name',">书籍名称</div>
        <div data-options="name:'isbn'">书籍isbn</div>
    </div>
</div>

<div id="addBookDia"></div>
<div id="updateBookDia"></div>
<div id="bookDetail"></div>