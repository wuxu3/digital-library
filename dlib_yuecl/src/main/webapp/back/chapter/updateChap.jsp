<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    var url = '/dlib/chap/singleChap?id=' + '${param.id}';
    $("#updateChapInput").form('load', url);

</script>

<div style="margin-top: 20px;text-align: center">
    <form method="post" enctype="multipart/form-data" id="updateChapInput">
        <div style="margin-top: 3px">
            章节名：<br/>
            <input type="hidden" name="id"/>
            <input class="easyui-textbox" name="name"/>

        </div>
        <div style="margin-top: 10px">
            选择书籍：<br/>
            <input class="easyui-combobox" name="bookId" data-options="
                valueField: 'id',
                textField: 'name',
                url: '/dlib/book/allBoks',
                 onLoadSuccess:function(){
                             var val = $(this).combobox('getData');
                             for (var item in val[0]) {
                                 if (item == 'id') {
                                     $(this).combobox('select', val[0][item]);
                                }
                              }

                         },
            "/>

        </div>

    </form>

</div>