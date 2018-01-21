<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<script type="text/javascript">

    $(function () {

        $("#twoSorts").combobox({

            valueField: 'id',
            textField: 'name',
            url: '/dlib/sort/fadSort?id=' + '${param.id}',
            onLoadSuccess: function () {

                var val = $(this).combobox('getData');

                for (var item in val[0]) {
                    if (item == 'id') {
                        $(this).combobox('select', val[0][item]);
                    }
                }

            }
        });

    });


</script>
<div style="text-align: center;margin-top: 20px">
    <form method="post" id="updateInput">

        选择修改的分类：
        <input id="twoSorts" name="id"/><br/>
        将该分类修改成：
        <input id="sortName" class="easyui-textbox" name="name">


    </form>
</div>

