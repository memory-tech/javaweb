<template>
    <div>
        <div>
            <el-input size="small" v-model="searchTableInfo" placeholder="请输入搜索内容" style="width:240px"></el-input>
        </div>

        <div class="table-content" style="margin-top: 10px">
            <el-table :data="pageList" border stripe @selection-change style="width: 100%;">
                <el-table-column prop="date" label="日期"></el-table-column>
                <el-table-column prop="no" label="编号"></el-table-column>
                <el-table-column prop="reason" label="理由"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="handlePass(scope.$index, scope.row)">批准</el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleNo(scope.$index, scope.row)">拒绝</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="block">
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage1"
                        :page-sizes="[10, 20, 30, 40]"
                        :page-size="10"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="searchList.length"
                ></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    export default {
        data: function () {
            return {
                rawList: [],
                searchList: [],
                currentPage1: 1,
                pageSize: 10,
                pageList: [],
                searchTableInfo: '',
            };
        },

        mounted() {
            this.initTable();
        },

        watch:{
            'searchTableInfo'(){
                this.search();
            }
        },

        methods: {
            handleSizeChange: function(pageSize) {
                this.pageSize = pageSize;
                this.handleCurrentChange(this.currentPage1);
            },
            handleCurrentChange: function(currentPage) {
                this.currentPage1 = currentPage;
                this.currentChangePage(this.searchList, currentPage);
            },
            currentChangePage(list, currentPage) {
                let from = (currentPage - 1) * this.pageSize;
                let to = currentPage * this.pageSize;
                this.pageList = [];
                for (; from < to; from++) {
                    if (list[from]) {
                        this.pageList.push(list[from]);
                    }
                }
            },
            initTable(){
                axios.get("/api/deal/query").then(response => {
                    this.rawList = response.data;
                    this.currentPage1= 1;
                    this.pageSize= 10;
                    this.search();
                });
            },
            check(str){
                return str!=null && str.indexOf(this.searchTableInfo)!=-1;
            }
            ,
            search(){
                let from=0;
                console.log("搜索");
                let to=this.rawList.length;
                this.searchList=[];
                for(;from<to;from++){
                    if(this.check(this.rawList[from].no) || this.check(this.rawList[from].date)){
                        this.searchList.push(this.rawList[from]);
                    }
                }
                this.currentChangePage(this.searchList, 1);
            },
            handlePass(index,data){
                axios.get("api/deal/yes?no="+data.no+"&date="+data.date).then(resp=>{
                    if(resp){
                        this.initTable();
                    }
                })
            },
            handleNo(index,data){
                this.$confirm('此操作将拒绝['+data.no+']的申请,是否继续', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.get("api/deal/no?no="+data.no+"&date="+data.date).then(resp=>{
                        if(resp){
                            this.initTable();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });
                });
            }
        }
    };
</script>

<style>
    .updatePerInput{
        display: flex;
        justify-content: space-between;
        margin-top: 10px;
    }

</style>