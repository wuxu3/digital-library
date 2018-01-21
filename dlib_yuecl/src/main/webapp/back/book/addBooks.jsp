<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<script type="text/javascript">

    var editor = KindEditor.create('#editor_id', {
        items: [
            'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', '/',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
            'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
            'anchor', 'link', 'unlink',
        ],
        afterBlur: function () {
            this.sync();
        },

    });

</script>

<div style="text-align: center; margin-top:20px">

    <form method="post" id="bookAddInput" enctype="multipart/form-data">
        <div>
            <%--<div style="float: left;border:1px;width:30%;height:50%;background: red">


            </div>
            <div style="float: left;border:1px;width:70%;height:100%;background:gold">
                <input class="easyui-textbox">

            </div>--%>
            <div style="margin-top: 4px">
                书籍名称:<input name="name" class="easyui-textbox" data-options="width:200"><br/>

            </div>
            <div style="margin-top: 4px">
                书籍封面:<input name="file" class="easyui-filebox" data-options="width:200"><br/>

            </div>
            <div style="margin-top: 4px">
                书籍作者:<input name="author" class="easyui-textbox" data-options="width:200"><br/>

            </div>

            <div style="margin-top: 4px">
                出版社：<input name="publish" class="easyui-textbox" data-options="width:200"><br/>

            </div>

            <div style="margin-top: 4px">
                书籍分类:<input class="easyui-combobox" data-options="width:100,
                 prompt:'一级分类',
                        url:'/dlib/sort/allFirstSort',
                        textField:'name',
                        valueField:'id',
                        onSelect:function(parent){
                            $('#secSort').combobox('clear');
                            var url = '/dlib/sort/secondSort?parentId='+parent.id;
                            $('#secSort').combobox('reload', url);
                        },

                        onLoadSuccess:function(){
                             var val = $(this).combobox('getData');
                             for (var item in val[0]) {
                                 if (item == 'id') {
                                     $(this).combobox('select', val[0][item]);
                                }
                              }

                         },

            "/><input id="secSort" name="sortId" class="easyui-combobox" data-options="width:100,

                prompt:'二级分类',
                       <%-- url:'/MyPage/province/allCity',--%>
                        valueField:'id',
                        textField:'name',
                         onLoadSuccess:function(){
                             var val = $(this).combobox('getData');

                             for (var item in val[0]) {
                                 if (item == 'id') {
                                     $(this).combobox('select', val[0][item]);
                                }
                              }

                         },


               "/><br/>

            </div>

            <div style="margin-top: 4px">
                isbn编号:<input name="isbn" class="easyui-textbox" data-options="width:200"><br/>

            </div>
            <div style="margin-top: 4px">

                书籍简介:<textarea id="editor_id" name="sketch" style="width:200px;height:300px;"></textarea>
            </div>

        </div>


    </form>

</div>
