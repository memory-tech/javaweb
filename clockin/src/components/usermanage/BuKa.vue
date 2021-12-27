<!--  -->
<template>
    <div>
        <el-calendar v-model="value">
            <template slot="dateCell" slot-scope="{ date, data }">
                {{ data.day.split("-").slice(1).join("-") }}
                <div
                    v-if="getCurYearAndMonth(date) == getCurYearAndMonth(value)"
                    style="display: inline-block"
                >
                    <el-button type="primary" @click="bukashenqing"
                        >补卡申请</el-button
                    >
                </div>
            </template>
        </el-calendar>
        <el-dialog
                title="补卡申请"
                :visible.sync="DialogVisible"
                width="30%">
            <div class="updatePerInput">
                <el-tag>员工编号</el-tag>
                <el-input size="small" style="width: 80%" :disabled="true" v-model="userinfo.no"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>日期</el-tag>
                <el-input size="small" style="width: 80%" :disabled="true" v-model="value.toLocaleDateString()"></el-input>
            </div>
            <div>
                <el-divider>理由</el-divider>
                <el-input type="textarea" :rows="10" placeholder="请输入理由" v-model="reason"></el-input>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="addDialogVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="doAdd">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "BuKa",
    data() {
        return {
            userinfo: null,
            value: new Date(),
            DialogVisible: false,
            reason: ""
        };
    },
    created() {
        let userinfoStr = localStorage.getItem("userinfo");
        let userinfoObj = JSON.parse(userinfoStr);
        this.userinfo = userinfoObj;

        console.log(this.userinfo);
    },
    methods: {
        bukashenqing() {
            this.reason=""
            this.DialogVisible=true;

        },
        doAdd(){
            axios.get("/api/deal/add?" +
                "no=" +
                this.userinfo.no +
                "&date=" +
                this.value.toLocaleDateString()+
                "&reason="+this.reason).then(resp=>{
                if(resp){
                    this.DialogVisible=false;
                }
            })
        },
        getCurYearAndMonth(date) {
            let year = date.getFullYear();
            let month = date.getMonth() + 1;
            let day = date.getDate();
            return year + "-" + month + "-" + day;
        },
    },
};
</script>

<style  scoped>
</style>
