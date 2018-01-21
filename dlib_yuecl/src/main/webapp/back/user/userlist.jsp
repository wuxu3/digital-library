<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#users").datagrid({
            url: '/dlib/user/allUser',
            columns: [[
                {title: "用户id", field: "id", width: 200},
                {title: "用户名", field: "name", width: 200},
                {title: "账号", field: "accoutNumber", width: 200},
                {title: "密码", field: "password", width: 100},
                {title: "头像", field: "photo", width: 200},
                {title: "已读图书", field: "inreadcount", width: 100},
                {title: "未读图书", field: "outreadcount", width: 100},
                {title: "用户状态", field: "userSatus", width: 100},
                {title: "注册时间", field: "registerdate", width: 150},
                {title: "书架ID", field: "rackId", width: 200},
                {title: "生日", field: "bir", width: 200},
                {title: "盐", field: "salt", width: 160},
                {
                    title: "操作", field: "option", width: 200,
                    formatter: function (value, row, index) {
                        return "<a class='del' data-options=\"plain:true,iconCls:'icon-20130408025545236_easyicon_net_30'\" onclick=\"deleteUser('" + row.id + "')\">[冻结]</a><br/><a class='del' data-options=\"plain:true,iconCls:'icon-20130408025545236_easyicon_net_30'\" onclick=\"activeUser('" + row.id + "')\">[激活]</a>";
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                $(".del").linkbutton();

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
            toolbar: '#tb4',

        });
    });


    function deleteUser(id) {
        $.messager.confirm('提示', '是否确定要冻结该用户？', function (r) {

            //确认删除
            if (r) {

                $.getJSON("/dlib/user/updateSta", {"id": id, "pj": "0"}, function (result) {


                    $.messager.show({

                        title: '提示',
                        msg: '用户已冻结',

                    });


                });

                $('#users').datagrid('reload');
            }

        });
    }


    function activeUser(id) {
        $.messager.confirm('提示', '是否确定要激活该用户？', function (r) {

            //确认删除
            if (r) {

                $.getJSON("/dlib/user/updateSta", {"id": id, "pj": "1"}, function (result) {


                    $.messager.show({

                        title: '提示',
                        msg: '用户已激活',

                    });

                });

                $('#users').datagrid('reload');

            }

        });

    }

    function qq() {
    }
</script>

<div id="users"></div>

<div id="tb4">
    <input id="ss" class="easyui-searchbox"
           data-options="searcher:qq,prompt:'请输入查询条件....',menu:'#mm',width:400"/>
    <div id="mm" data-options="">
        <div data-options="name:'name',">姓名</div>
        <div data-options="name:'age'">年龄</div>
    </div>
</div>