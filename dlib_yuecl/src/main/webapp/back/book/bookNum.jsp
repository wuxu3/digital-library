<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="agee" style="width: 600px;height:400px;"></div>
<script type="text/javascript">

    $(function () {

        $.get("/dlib/book/getBoksNum", function (result) {

            console.log(result);
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('agee'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '书籍阅读量一览'
                },
                tooltip: {},
                legend: {
                    data: ['书籍阅读量']
                },
                xAxis: {
                    data: result.bookName
                    /* ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"] */
                },
                yAxis: {},
                series: [{
                    name: '阅读量',
                    type: 'bar',
                    data: result.readCount,
                    itemStyle: {
                        normal: {
                            color: function (params) {
                                var colorList = ['#c23531', '#2f4554', '#61a0a8', '#d48265', '#91c7ae', '#749f83', '#ca8622', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
                                return colorList[params.dataIndex];
                            }
                        }
                    }
                    /* [5, 20, 36, 10, 10, 20] */

                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        }, "JSON");


    });

</script>