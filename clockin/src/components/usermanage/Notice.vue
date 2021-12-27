<!--  -->
<template>
    <div>
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="title" label="公告标题" width="180" >
            </el-table-column>
            <el-table-column prop="content" label="公告内容" width="540">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "Notice",
    data() {
        return {
            tableData:[]
        };
    },
    created(){
        this.bukashenqing();
    },

    methods: {
        async bukashenqing() {
            console.log(this.userinfo);
            let res = await axios.get(
                "/api/notice/query" 
            );
        
            this.tableData  = res.data
        },
    },
};
</script>

<style  scoped>
.el-table {
  position: relative;
  box-sizing: border-box;
  flex: 1;
  width: 100%;
  max-width: 100%;
  font-size: 14px;
  color: #1890ff;
}
</style>
