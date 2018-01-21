<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="photoType" style="width: 600px;height:400px;"></div>
<script type="text/javascript">

    $(function () {

        $.get("/dlib/book/countByType", function (result) {

            console.log(result);
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('photoType'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '图书封面类型展示',
                    subtext: '最新数据',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['jpg类型', 'png类型', 'gif类型']
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: result,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };


            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        }, "JSON");


    });

</script>