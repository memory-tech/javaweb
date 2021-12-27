<template>
    <div>
        <div id="pieReport1" style="width: 400px;height: 300px; "></div>
        <div id="pieReport2" style="width: 400px;height: 300px; "></div>
    </div>

</template>

<script>
    import echarts from 'echarts';
    import axios from "axios";
    export default {
        name: "",
        data() {
            return {
                charts: "",
                opinion1: ["男", "女"],
                opinion2: ["正常", "缺卡"],
                opinionData1: [
                    { value: 12, name: "男" },
                    { value: 18, name: "女" }
                ],
                opinionData2: [
                    { value: 12, name: "正常" },
                    { value: 18, name: "缺卡" }
                ]
            };
        },
        methods: {
            drawPie(id,option1,data1) {
                this.charts = echarts.init(document.getElementById(id));
                this.charts.setOption({
                    tooltip: {
                        trigger: "item",
                        formatter: "{a}<br/>{b}:{c} ({d}%)"
                    },
                    legend: {
                        bottom: 10,
                        left: "center",
                        data: option1,
                        textStyle: {
                            color: 'white'
                        }
                    },
                    series: [
                        {
                            name: "状态",
                            type: "pie",
                            radius: "65%",
                            center: ["50%", "50%"],
                            avoidLabelOverlap: false,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: "rgba(0, 0, 0, 0.5)"
                                },
                                color: function(params) {
                                    //自定义颜色
                                    var colorList = ["#eeb394", "#79d2ee"];
                                    return colorList[params.dataIndex];
                                }
                            },
                            data: data1
                        }
                    ]
                });
            },
        },
        mounted() {
            axios.get("/api/count/findSex").then(resp=>{
                if(resp){
                    this.opinionData1[0].value=resp.data[0];
                    this.opinionData1[1].value=resp.data[1];
                }

                this.drawPie("pieReport1",this.opinion1,this.opinionData1);
            })
            axios.get("/api/count/findAttendance").then(resp=>{
                if(resp){
                    this.opinionData2[0].value=resp.data[0];
                    this.opinionData2[1].value=resp.data[1];
                }

                this.drawPie("pieReport2",this.opinion2,this.opinionData2);
            })
        }

    }

</script>