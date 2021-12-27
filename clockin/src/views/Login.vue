<template>
    <div>
        <el-form ref="loginForm" :model="loginForm" :rules="rules" class="loginContainer">
            <h3 class="loginTitle">系统登录</h3>
            <el-form-item prop="username">
                <el-input type="text" auto-complete="false" v-model="loginForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" auto-complete="false" v-model="loginForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-checkbox v-model="admin" class="loginCheckbox">管理员</el-checkbox>
                <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import axios from "axios";
    export default {
        name: "Login",
        data(){
            return{
                loginForm:{
                    username:'',
                    password:''
                },
                admin: false,
                rules: {
                    username:[{required: true, message:'用户名不能为空',trigger: 'blur'}],
                    password: [{required: true, message:'密码不能为空',trigger: 'blur'}]
                },
            }
        },
        methods:{
            submitLogin(){
                this.$refs.loginForm.validate((valid) => {
                   if(valid){
                       if(this.admin){
                          axios.get("/api/manager/login?username="+this.loginForm.username+"&password="+this.loginForm.password)
                              .then(resp=>{
                              if(resp){
                                  if(resp.code == 0){
                                      localStorage.setItem("userinfo","管理员");
                                      this.$router.replace('/manager');
                                  }
                              }
                           })
                       } else{
                           axios.get("/api/user/login?no="+this.loginForm.username+"&password="+this.loginForm.password)
                               .then(resp=>{
                               if(resp){
                                   if(resp.code == 0){
                                       localStorage.setItem("userinfo",JSON.stringify(resp.data));
                                       this.$router.replace('/user');
                                   }
                               }
                           })

                       }
                   }
                   else{
                       this.$message.error('请输入所有字段');
                       return false;
                   }
                });
            }
        }
    }
</script>

<style>
    .loginContainer{
      border-radius: 15px;
      background-clip: padding-box;
      margin: 180px auto;
      width: 350px;
      padding: 15px 35px 15px 35px;
      background: #fff;
      border: 1px solid #eaeaea;
      box-shadow:0 0 25px #cac6c6;
    }
    .loginTitle{
        margin: 0px auto 40px auto;
        text-align: center;
    }
    .loginCheckbox{
        margin: 0px 0px 15px 0px;
        text-align: left;
    }
</style>