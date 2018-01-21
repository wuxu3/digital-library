<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<script type="text/javascript">

    $(function () {

        $("#allFirstSort").combobox({

            valueField: 'id',
            textField: 'name',
            url: '/dlib/sort/allFirstSort'
        });


    });


</script>

<div style="text-align: center;margin-top: 20px">
    <form id="addSortInput" method="post">

        <div>
            输入新的分类名：<br/>
            <input class="easyui-textbox" data-options="prompt:'输入新的分类信息'" name="name"/>
        </div>
        <div style="margin-top: 10px">
            选择所属父分类：<br/>
            <input id="allFirstSort" name="parentId"/>

        </div>

    </form>
</div>