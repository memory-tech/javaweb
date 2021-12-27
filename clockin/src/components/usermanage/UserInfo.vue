<!--  -->
<template>
    <div>
        <el-descriptions title="用户信息" border="true" column="1">
            <el-descriptions-item label="登录名"
                >{{userinfo.no}}</el-descriptions-item
            >
            <el-descriptions-item label="用户名"
                >{{userinfo.username}}</el-descriptions-item
            >
            <el-descriptions-item label="部门"
                >{{userinfo.department}}</el-descriptions-item
            >
            <el-descriptions-item label="性别">{{userinfo.sex}}</el-descriptions-item>
            <el-descriptions-item label="电话">{{userinfo.phone}}</el-descriptions-item>
        </el-descriptions>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "userinfo",
    data() {
        return {
            userinfo: null,
        };
    },
    created() {
        let userinfoStr = localStorage.getItem("userinfo");
        let userinfoObj = JSON.parse(userinfoStr);
        this.userinfo = userinfoObj;

    },
};
</script>

<style  scoped>
.el-descriptions {
  box-sizing: border-box;
  font-size: 15px;
  color: #ffffff;
}
</style>
