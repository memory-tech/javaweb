<!--  -->
<template>
    <div>
        <el-calendar v-model="value">
            <template slot="dateCell" slot-scope="{ date ,data}">
                {{ data.day.split('-').slice(1).join('-') }}
                <div
                    v-if="getCurYearAndMonth(date) == getCurYearAndMonth(value)"
                    style="display:inline-block"
                >
                    <el-button type="primary" @click="qiandao">签到</el-button>
                    <el-button type="success" @click="qiantui">签退</el-button>
                </div>
            </template>
        </el-calendar>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "QianDao",
    data() {
        return {
            userinfo: null,
            value: new Date(),
        };
    },
    created() {
        let userinfoStr = localStorage.getItem("userinfo");
        let userinfoObj = JSON.parse(userinfoStr);
        this.userinfo = userinfoObj;

        console.log(this.userinfo);
    },
    methods: {
        async qiandao() {
            console.log(this.userinfo);
            let res = await axios.get(
                "/api/attend/checkIn?" +
                    "no=" +
                    this.userinfo.no +
                    "&username=" +
                    this.userinfo.username +
                    "&department=" +
                    this.userinfo.department
            );
        },
        async qiantui() {
            let res = await axios.get(
                "/api/attend/checkOut?" +
                    "no=" +
                    this.userinfo.no +
                    "&username=" +
                    this.userinfo.username +
                    "&department=" +
                    this.userinfo.department
            );
        },
        getCurYearAndMonth(date) {
            let year = date.getFullYear();
            let month = date.getMonth()+1;
            let day = date.getDate();
            return year + "-" + month + "-" + day;
        },
    },
};
</script>

<style  scoped>
</style>
