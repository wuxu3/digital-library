<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<script>


</script>

<div style="margin-top: 20px;text-align: center">
    <form method="post" enctype="multipart/form-data" id="addChapInput">
        <div style="margin-top: 3px">
            章节名：<br/>
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

        <div style="margin-top: 10px">
            选择文件：<br/>
            <input class="easyui-filebox" name="file"/>

        </div>
    </form>

</div>