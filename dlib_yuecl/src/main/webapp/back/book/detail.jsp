<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<script type="text/javascript">

    //var url='?id='+'${param.id}';

    //$("#detailInput").form('load',url);
    $(function () {

        $.getJSON("/dlib/book/oneBook", "id=${param.id}", function (result) {

            $("#ima").prop('src', result.photo);
            $("#name").html(result.name);
            $("#publishdate").html(result.publishdate);
            $("#readNumber").html(result.readNumber)
            $("#author").html(result.author);
            $("#isbn").html(result.isbn);
            $("#publish").html(result.publish);
            $("#sketch").html(result.sketch);
            $("#id").val(result.id);
        });

    });

</script>
<div>

    <form id="detailInput" method="post" enctype="multipart/form-data">
        <div style="float: left;border:1px;width:50%;height:50%">

            <img id="ima" st style="margin-left: 30px"><br/>
            修改书籍封面:<br/>
            <input class="easyui-filebox" name="file" data-options="width:150">
        </div>

        <div style="float: left;border:1px;width:50%;height:100%">
            <input type="hidden" name="id" id="id">
            <font style="font-size: 15px;color: #868bff">书籍名称：</font><a id="name"></a><br/>
            <font style="font-size: 15px;color: #868bff">书籍上架时间：</font><a id="publishdate"></a><br/>
            <font style="font-size: 15px;color: #868bff">书籍阅读量：</font><a id="readNumber"></a><br/>
            <font style="font-size: 15px;color: #868bff">书籍作者：</font><a id="author"></a><br/>
            <font style="font-size: 15px;color: #868bff">书籍isbn:</font><a id="isbn"></a><br/>
            <font style="font-size: 15px;color: #868bff">书籍出版社：</font><a id="publish"></a><br/>
            <font style="font-size: 15px;color: #868bff">书籍简介：</font><a id="sketch"></a><br/>

        </div>

    </form>
</div>

