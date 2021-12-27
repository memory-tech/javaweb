<template>
    <div>
        <el-input prefix-icon="iconfont icon-sousuo" size="small" v-model="searchTableInfo" placeholder="请输入搜索内容" style="width:240px"></el-input>
        <el-button type="primary" size="small" @click="handleAdd" style="margin-left: 10px">发布公告</el-button>
        <div class="table-content" style="margin-top: 10px">
            <el-table :data="pageList" border stripe @selection-change style="width: 100%;">
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="center" inline class="demo-table-expand" style="width: 50%">
                            <el-form-item label="标题" class="el-form-item">
                                <span>{{ props.row.title }}</span>
                            </el-form-item>
                            <el-form-item label="内容" class="el-form-item">
                                <p>{{ props.row.content }}</p>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-table-column>
                <el-table-column prop="title" label="标题" align="center"></el-table-column>
                <el-table-column prop="content" label="内容" header-align="center" :show-overflow-tooltip="true"></el-table-column>
                <el-table-column label="操作" width="220" align="center">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
        <el-dialog
                title="编辑公告"
                :visible.sync="dialogVisible"
                width="30%">
            <div class="noticeInput">
                <el-tag>标题</el-tag>
                <el-input size="small" style="width: 80%" v-model="updateNotice.title"></el-input>
            </div>
            <div>
                <el-divider>内容</el-divider>
                <el-input type="textarea" :rows="10" placeholder="请输入内容" v-model="updateNotice.content"></el-input>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="doUpdate">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog
                title="发布公告"
                :visible.sync="addDialogVisible"
                width="30%">
            <div class="noticeInput">
                <el-tag>标题</el-tag>
                <el-input size="small" style="width: 80%" v-model="addNotice.title"></el-input>
            </div>
            <div>
                <el-divider>内容</el-divider>
                <el-input type="textarea" :rows="10" placeholder="请输入内容" v-model="addNotice.content"></el-input>
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
        data() {
            return {
                rawList: [],
                searchList: [
                    // {
                    //     title:'123',
                    //     content:'54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert13454ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttertttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttertttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttertttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert54ttert'
                    // },
                    // {
                    //     title:'456',
                    //     content:'54tter123t'
                    // }
                ],
                currentPage1: 1,
                pageSize: 10,
                pageList: [],
                dialogVisible: false,
                addDialogVisible: false,
                updateNotice:{
                    id:1,
                    title:'',
                    content:''
                },
                addNotice:{
                    title:'',
                    content:''
                },
                searchTableInfo:''
            };
        },

        mounted() {
            this.initTable();
            this.currentChangePage(this.searchList, 1);
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
                axios.get("/api/notice/query").then(response => {
                    this.rawList = response.data;
                    this.currentPage1= 1;
                    this.pageSize= 10;
                    this.search();
                });
            },
            doUpdate: function (){
                axios.get("/api/notice/edit?id="+this.updateNotice.id+"&title="+this.updateNotice.title+"&content="+this.updateNotice.content).then(resp=>{
                    if(resp){
                        this.initTable();
                        this.dialogVisible=false;
                    }
                })
            },
            doAdd: function (){
                axios.get("/api/notice/add?title="+this.addNotice.title+"&content="+this.addNotice.content).then(resp=>{
                    if(resp){
                        this.initTable();
                        this.addDialogVisible=false;
                    }
                })
            },
            search(){
                let from=0;
                console.log("搜索");
                let to=this.rawList.length;
                this.searchList=[];
                for(;from<to;from++){
                    if(this.rawList[from].title.indexOf(this.searchTableInfo)!=-1){
                        this.searchList.push(this.rawList[from]);
                    }
                }
                this.currentChangePage(this.searchList, 1);
            },
            handleAdd(){
                this.addNotice={};
                this.addDialogVisible=true;
            },
            handleEdit(index,data){
                Object.assign(this.updateNotice,data);
                this.dialogVisible=true;
            },
            handleDelete(index,data){
                this.$confirm('此操作将删除['+data.title+'],是否继续', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.get("/api/notice/delete?id="+data.id).then(resp=>{
                        if(resp){
                            this.initTable();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            }
        }
    };
</script>

<style>
    .noticeInput{
        display: flex;
        justify-content: space-between;
        margin-top: 10px;
        margin-bottom: 10px;
    }
    .demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
    .demo-table-expand p {
        margin-left: 100px;
        width: 700px;
        text-overflow: ellipsis;
        word-wrap: break-word;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        overflow: hidden;
    }
</style>