<template>
    <div>
        <el-input prefix-icon="iconfont icon-sousuo" size="small" v-model="searchTableInfo" placeholder="请输入搜索内容" style="width:240px"></el-input>
        <el-button type="primary" size="small" @click="handleAdd" style="margin-left: 10px">新建</el-button>
        <div class="table-content" style="margin-top: 10px">
            <el-table :data="pageList" border stripe @selection-change style="width: 100%;">
                <el-table-column prop="no" label="编号"></el-table-column>
                <el-table-column prop="username" label="姓名"></el-table-column>
                <el-table-column prop="department" label="部门"></el-table-column>
                <el-table-column prop="sex" label="性别"></el-table-column>
                <el-table-column prop="phone" label="电话号码"></el-table-column>
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
                title="编辑员工信息"
                :visible.sync="dialogVisible"
                width="30%">
            <div class="updatePerInput">
                <el-tag>编号</el-tag>
                <el-input size="small" :disabled="true" style="width: 80%" v-model="updatePer.no"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>名字</el-tag>
                <el-input size="small" style="width: 80%" v-model="updatePer.username"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>部门</el-tag>
                <el-input size="small" style="width: 80%" v-model="updatePer.department"></el-input>
            </div>

            <div class="updatePerInput">
                <el-tag>性别</el-tag>
                <el-select size="small" style="width: 80%" v-model="updatePer.sex" placeholder="请选择">
                    <el-option
                            v-for="item in options"
                            :key="item"
                            :label="item"
                            :value="item">
                    </el-option>
                </el-select>
            </div>
            <div class="updatePerInput">
                <el-tag>电话</el-tag>
                <el-input size="small" style="width: 80%" v-model="updatePer.phone"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>密码</el-tag>
                <el-input size="small" style="width: 80%" v-model="updatePer.password"></el-input>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="small" @click="dialogVisible = false">取 消</el-button>
                <el-button size="small" type="primary" @click="doUpdate">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog
                title="新建员工信息"
                :visible.sync="addDialogVisible"
                width="30%">
            <div class="updatePerInput">
                <el-tag>名字</el-tag>
                <el-input size="small" style="width: 80%" v-model="addPer.username"></el-input>
            </div>
            <div class="updatePerInput">
                <el-tag>部门</el-tag>
                <el-input size="small" style="width: 80%" v-model="addPer.department"></el-input>
            </div>

            <div class="updatePerInput">
                <el-tag>性别</el-tag>
                <el-select size="small" style="width: 80%" v-model="addPer.sex" placeholder="请选择">
                    <el-option
                            v-for="item in options"
                            :key="item"
                            :label="item"
                            :value="item">
                    </el-option>
                </el-select>
            </div>
            <div class="updatePerInput">
                <el-tag>电话</el-tag>
                <el-input size="small" style="width: 80%" v-model="addPer.phone"></el-input>
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
                searchList: [],
                currentPage1: 1,
                pageSize: 10,
                pageList: [],
                dialogVisible: false,
                addDialogVisible: false,
                options:["男","女"],
                updatePer:{
                    id:1,
                    no:'',
                    username:'',
                    department:'',
                    sex:'',
                    phone:'',
                    password:''
                },
                addPer:{
                    username:'',
                    department:'',
                    sex:'',
                    phone:''
                },
                searchTableInfo:''
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
                axios.get("/api/manager/findAll").then(response => {
                    this.rawList = response.data;
                    this.currentPage1= 1;
                    this.pageSize= 10;
                    this.search();
                });
            },
            doUpdate: function (){
                axios.get("api/manager/updateOne?no="+this.updatePer.no+"&username="+this.updatePer.username+"&department="
                    +this.updatePer.department+"&sex="+this.updatePer.sex+"&phone="+this.updatePer.phone+"&password="+this.updatePer.password).then(resp=>{
                    if(resp){
                        this.initTable();
                        this.dialogVisible=false;
                    }
                })
            },
            doAdd: function (){
                axios.get("api/manager/addOne?username="+this.addPer.username+"&department="
                    +this.addPer.department+"&sex="+this.addPer.sex+"&phone="+this.addPer.phone).then(resp=>{
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
                    if(this.rawList[from].no.indexOf(this.searchTableInfo)!=-1 || this.rawList[from].username.indexOf(this.searchTableInfo)!=-1
                    ||this.rawList[from].department.indexOf(this.searchTableInfo)!=-1 || this.rawList[from].phone.indexOf(this.searchTableInfo)!=-1
                    ||this.rawList[from].sex.indexOf(this.searchTableInfo)!=-1){
                        this.searchList.push(this.rawList[from]);
                    }
                }
                this.currentChangePage(this.searchList, 1);
            },
            handleAdd(){
                this.addPer={};
                this.addDialogVisible=true;
            },
            handleEdit(index,data){
                Object.assign(this.updatePer,data);
                this.dialogVisible=true;
            },
            handleDelete(index,data){
                this.$confirm('此操作将删除['+data.username+'],是否继续', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    axios.get("api/manager/deleteById?id="+data.id).then(resp=>{
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