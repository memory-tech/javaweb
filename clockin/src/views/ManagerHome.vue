<template>
    <div>
        <el-container>
            <el-header class="homeHeader">
                <div class="title">员工考勤管理系统</div>
                <el-button type="danger" @click="logout">退出</el-button>
            </el-header>
            <el-container>
                <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                    <el-menu @select="menuClick" >
                            <el-menu-item index="/usercontrol">员工管理</el-menu-item>
                            <el-menu-item index="/noticecontrol">公告管理</el-menu-item>
                            <el-menu-item index="/clockcontrol">考勤记录管理</el-menu-item>
                            <el-menu-item index="/makeupcontrol">补卡申请处理</el-menu-item>
                            <el-menu-item index="/countcontrol">信息统计</el-menu-item>
                    </el-menu>
                </el-aside>
                <el-main>
                    <router-view />
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name:"ManagerHome",
        methods:{
            logout() {
                this.$message.success("退出成功!");
                localStorage.clear();
                this.$router.replace("/");
            },
            menuClick(index){
                this.$router.push(index);
            }
        }
    }
</script>

<style>
    .homeHeader{
        background: #409EFF;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 15px;
        box-sizing: border-box;
    }
    .homeHeader .title{
        font-size: 30px;
        font-family: 华文行楷;
        color: white;
    }
</style>