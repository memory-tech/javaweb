<!--  -->
<template>

    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>修改密码</span>
            </div>
            <el-form
                ref="loginFormRef"
                :rules="loginRules"
                :model="loginForm"
                class="login_form"
                label-width="100px"
            >
                <el-form-item label="输入原密码" prop="oldpassword">
                    <el-input
                        v-model="loginForm.oldpassword"
                        type="password"
                    ></el-input>
                </el-form-item>

              <el-form-item label="输入新密码" prop="newpassword">
                <el-input
                    v-model="loginForm.newpassword"
                    type="password"
                ></el-input>
              </el-form-item>

                <el-form-item label="确认新密码" prop="repassword">
                    <el-input
                        v-model="loginForm.repassword"
                        type="password"
                    ></el-input>
                </el-form-item>


                <el-form-item class="btns">
                    <el-button type="primary" @click="handleLogin"
                        >提交</el-button
                    >
                    <el-button type="success" @click="handleReset"
                        >重置</el-button
                    >
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "UpdatePassword",
  data: function () {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };

    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入新密码"));
      } else{
        if (this.loginForm.newpassword !== "") {
        this.$refs.loginFormRef.validateField("repassword");}
        callback();
      }
    };

    var validatePass3 = (rule, value, callback) => {
          if (value === "") {
            callback(new Error("请再次输入新密码"));
          } else if (value !== this.loginForm.newpassword) {
            callback(new Error("两次输入密码不一致!"));
          } else {
            callback();
          }
          }
    return {
      username: "",
      loginForm: {
        username: "",
        oldpassword: "",
        repassword: "",
        newpassword: "",
      },

      loginRules: {
        oldpassword: [
          {validator: validatePass, trigger: "blur"},
          {required: true, message: "请输入原来的密码"},
        ],
        newpassword: [
          {
            validator: validatePass2,
            required: true,
            message: "请输入新的密码名称",
            trigger: "blur",
          },
          {
            min: 5,
            max: 11,
            message: "长度在 5 到 11 个字符",
            trigger: "blur",
          },
        ],
        repassword: [
          {validator: validatePass3, trigger: "blur"},
          {required: true, message: "请确认新的密码"},
        ],
      },
    };
  },
    created() {},
    methods: {
        handleLogin() {
            this.$refs.loginFormRef.validate(async (valid) => {
                if (valid) {
                    let userinfoStr = localStorage.getItem("userinfo");
                    let userinfoObj = JSON.parse(userinfoStr);
                    this.userinfo = userinfoObj;
                    if (this.loginForm.oldpassword != this.userinfo.password) {
                        this.$message.success("原密码错误！");
                        return;
                    }
                    //如果验证成功
                    //首相在这个地方，返回的是一个对象，而且这个地想里面含有很多数据，
                    //在这个地方主要使用的就是这个解析的语法，在这个地方就可以很好的使用这个语法了，然后就可以很好的之后的访问了

                    const  res = await axios.get(
                        "/api/user/editPassword?"+"no=" + this.userinfo.no + "&password=" + this.loginForm.newpassword
                    );


                    //然后在这个地方是进行处理这个登录的，然后就可以很好的进行这个之后的操作。
                    if (res.code == 0) {
                        this.$message.success("修改密码成功！"); //信息提示
                    } else {
                        this.$message.success("修改失败！"); //信息的提示
                    }
                } else {
                    //如果失败
                    return;
                }
            });
        },
        handleReset() {
            this.$refs.loginFormRef.resetFields();
        },
    },
};
</script>

<style  scoped>
.el-breadcrumb {
    margin-bottom: 15px;
    font-size: 12px;
}
.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}
.clearfix:after {
    clear: both;
}

.box-card {
    width: 400px;
}
</style>
