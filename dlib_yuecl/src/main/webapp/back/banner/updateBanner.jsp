<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>

    var url = '/dlib/banner/oneBanner?id=' + '${param.id}';

    $("#updateBannerInput").form('load', url);

</script>


<div>
    <form method="post" id="updateBannerInput" enctype="multipart/form-data">

        <div style="text-align: center;margin-top: 10px">

            <input type="hidden" name="id">
            <div style="margin-top: 10px">
                轮播图名：<br/>
                <input class="easyui-textbox" name="name"/>

            </div>
            <div style="margin-top: 10px">
                上传图片：<br/>
                <input class="easyui-filebox" name="file"/>

            </div>
            <div style="margin-top: 10px">
                上传日期：<br/>
                <input class="easyui-datebox" name="uploaddate"/>
            </div>


        </div>

    </form>
</div>
