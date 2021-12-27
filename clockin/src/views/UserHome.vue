<!--  -->
<template>
    <div>
        <el-container style="height: 900px">
            <el-header
                ><el-button type="danger" @click="logout"
                    >退出</el-button
                ></el-header
            >
            <el-container>
                <el-aside
                    width="200px"
                    style="background-color: rgb(238, 241, 246)"
                >
                    <el-menu :router="true">
                            <el-menu-item index="/userinfo"
                                >查询用户信息</el-menu-item
                            >
                            <el-menu-item index="/updatepassword"
                                >修改密码</el-menu-item
                            >
                            <el-menu-item index="/qiandao">签到</el-menu-item>
                            <el-menu-item index="/buka"
                                >当天补卡申请</el-menu-item
                            >
                            <el-menu-item index="/notice"
                                >查看公告信息</el-menu-item
                            >
                    </el-menu>
                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
export default {
    name: "UserHome",
    data() {
        return {};
    },
    methods: {
        logout() {
            this.$message.success("退出成功!");
            localStorage.clear();
            this.$router.replace("/");
        },
    },
};
</script>

<style  scoped>
.el-aside {
  display: block;
  position: relative;
  overflow-y: scroll;
}
</style>
