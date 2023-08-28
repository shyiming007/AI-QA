<script setup>
import {reactive} from "vue";
import {ElMenu, ElMessage} from "element-plus";
import router from "@/router";
import {post} from "@/net";
import store_auth from "@/stores/auth";

const form = reactive({
    username:'',
    password:'',
    remember:'false'
})

const login = ()=>{
  if (!form.username || !form.password){
    ElMessage.warning('请填写用户名和密码')
  }
  else{
    post('/api/auth/login',{
      username:form.username,
      password:form.password,
      remember:form.remember
    },(message)=>{ElMessage.success(message)
      // 存储用户名到 LocalStorage
      localStorage.setItem('username', form.username)
    router.push('/index')})
  }
}
</script>

<template>
  <div style = 'text-align: center;margin:0 20px'>
    <div style="margin-top: 150px">
      <div style="font-size: 25px">用户登陆</div>

    </div>
    <div style="margin-top: 30px">
      <el-input v-model="form.username" type="text" placeholder="用户名/邮箱"/>
      <el-input v-model="form.password" type="password" style="margin-top: 10px" placeholder="密码"/>
    </div>
    <el-row style="margin-top: 5px">
      <el-col :span="12" style="text-align: left">
        <el-checkbox v-model="form.remember" label="记住我">
        </el-checkbox>
      </el-col>
      <el-col :span="12" style="text-align: right">
        <el-link>忘记密码？</el-link>

      </el-col>
    </el-row>
    <div style="margin-top: 30px">
      <el-button @click="login" type="success" plain>登陆</el-button>
    </div>
    <div style="margin-top: 10px">
      <el-button type="warning" plain>注册</el-button>
    </div>
  </div>
</template>

<style scoped>

</style>