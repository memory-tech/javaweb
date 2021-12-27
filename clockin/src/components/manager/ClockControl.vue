<template>
    <div>
        <div>
            <el-input size="small" v-model="searchTableInfo" placeholder="请输入搜索内容" style="width:240px"></el-input>
            <el-checkbox-group v-model="checkboxGroup"  style="display: inline-block;margin-left: 10px;">
                <el-checkbox-button v-for="option in options" :label="option" :key="option">{{option}}</el-checkbox-button>
            </el-checkbox-group>
        </div>

        <div class="table-content" style="margin-top: 10px">
            <el-table :data="pageList" border stripe @selection-change style="width: 100%;">
                <el-table-column prop="date" label="日期"></el-table-column>
                <el-table-column prop="no" label="编号"></el-table-column>
                <el-table-column prop="username" label="姓名"></el-table-column>
                <el-table-column prop="department" label="部门"></el-table-column>
                <el-table-column prop="begin" label="签到时间"></el-table-column>
                <el-table-column prop="end" label="签退时间"></el-table-column>
                <el-table-column prop="state" label="考勤状态"></el-table-column>
                <el-table-column label="操作">
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
                title="编辑考勤记录"
                :visible.sync="dialogVisible"
                width="30%">
            <div class="updatePerInput">
                <el-tag>编号</el-tag>
                <el-input size="small" :disabled="true" style="width: 80%" v-model="updateAtt.no"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>名字</el-tag>
                <el-input size="small" :disabled="true" style="width: 80%" v-model="updateAtt.username"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>部门</el-tag>
                <el-input size="small" :disabled="true" style="width: 80%" v-model="updateAtt.department"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>日期</el-tag>
                <el-input size="small" style="width: 80%" v-model="updateAtt.date"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>签到时间</el-tag>
                <el-input size="small" style="width: 80%" v-model="updateAtt.begin"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>签退时间</el-tag>
                <el-input size="small" style="width: 80%" v-model="updateAtt.end"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>状态</el-tag>
                <el-input size="small" style="width: 80%" v-model="updateAtt.state"></el-input>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="doUpdate">确 定</el-button>
            </span>
        </el-dialog>
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
                dialogVisible: false,
                updateAtt: {
                    id: 1,
                    date: '',
                    no: '',
                    username: '',
                    department: '',
                    begin: '',
                    end: '',
                    state: ''
                },
                searchTableInfo: '',
                checkboxGroup:["正常","缺卡","旷工"],
                options: ["正常","缺卡","旷工"]
            };
        },

        mounted() {
            this.initTable();
        },

        watch:{
            'searchTableInfo'(){
                this.search();
            },
            'checkboxGroup'(){
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
                axios.get("/api/attend/findAll").then(response => {
                    this.rawList = response.data;
                    this.currentPage1= 1;
                    this.pageSize= 10;
                    this.search();
                });
            },
            doUpdate: function (){
                axios.get("api/attend/edit?id="+this.updateAtt.id+"&no="+this.updateAtt.no+"&username="+this.updateAtt.username+"&department="
                    +this.updateAtt.department+"&date="+this.updateAtt.date+"&begin="+this.updateAtt.begin+"&end="+this.updateAtt.end+"&state="+this.updateAtt.state).then(resp=>{
                    if(resp){
                        this.initTable();
                        this.dialogVisible=false;
                    }
                })
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
                    if((this.check(this.rawList[from].no) || this.check(this.rawList[from].username)
                    || this.check(this.rawList[from].department)|| this.check(this.rawList[from].state))
                        &&this.rawList[from].state!=null&&this.checkboxGroup.indexOf(this.rawList[from].state)!=-1){
                        this.searchList.push(this.rawList[from]);
                    }
                }
                this.currentChangePage(this.searchList, 1);
            },
            handleEdit(index,data){
                Object.assign(this.updateAtt,data);
                this.dialogVisible=true;
            },
            handleDelete(index,data){
                this.$confirm('此操作将删除['+data.username+']的考勤记录,是否继续', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.get("api/attend/delete?no="+data.no+"&date="+data.date).then(resp=>{
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
    .updatePerInput{
        display: flex;
        justify-content: space-between;
        margin-top: 10px;
    }

</style>