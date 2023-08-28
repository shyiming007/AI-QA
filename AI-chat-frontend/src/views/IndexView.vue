<script setup>

 import {get} from "@/net";
 import {ElMessage} from "element-plus";
 import router from "@/router";
 import { Search } from '@element-plus/icons-vue';

 // 从 LocalStorage 获取用户名
 const username = localStorage.getItem('username');
 const logout =()=>{
   get('api/auth/logout',(message)=>{
     ElMessage.success(message)
     router.push('/')
   })
 }
</script>



<script>

import { computed } from 'vue';
import { useStore } from 'vuex';

import axios from 'axios'
// 后端解决跨域导入cors包
 export default {

   data() {
     return {
       askStr: "",
       reply: ""
     };
   },
   methods: {
     fetchReply() {
       axios.post("http://localhost:8080/chatGpt/askAi", {
         askStr: this.askStr
       })
           .then(response => {
             this.reply = response.data.replyStr;
           })
           .catch(error => {
             console.error("API request error:", error);
           });
     },
     // 保存记录方法
     saveRecord() {
       const record = {
         ask: this.askStr,
         reply: this.reply
       };
       this.$store.dispatch('saveRecord', record); // 调用 store 中的 action
     },
     clearRecords() {
       this.$store.dispatch('clearAllRecords');
     }

   },
   computed: {
     // 获取所有记录的计算属性
     allRecords() {
       return this.$store.getters.allRecords; // 从 store 中获取记录
     }
   }
 };
</script>

<template>
<div>欢迎进入AI聊天应用</div>
  <p>欢迎，{{ username }}用户</p>
  <div> <el-button @click="logout">退出登陆</el-button></div>
  <div>
    <el-row :gutter="20">
    <el-col :span="6">
      <div class="grid-content ep-bg-purple" />
<!--    使用v-model 双向绑定数据-->
    <input v-model="askStr" @keyup.enter="fetchReply" placeholder="请输入问题" />
    <el-button :icon="Search" @click="fetchReply" round>获取回复</el-button>
    <p>您的问题：{{ askStr }}</p>
    <p>AI回复：{{ reply }}</p>
    </el-col>
    <el-col :span="6"><div class="grid-content ep-bg-purple" />
    <!-- 保存记录的按钮 -->
    <el-button @click="saveRecord">保存记录</el-button>
    <el-button @click="clearRecords">清空记录</el-button>

    <!-- 显示之前的记录 -->
    <div v-for="(record, index) in allRecords" :key="index">
      <p>问题{{ index + 1 }}：{{ record.ask }}</p>
      <p>回复：{{ record.reply }}</p>
    </div>
    </el-col>
    </el-row>
  </div>

</template>

<style scoped>

</style>

<style>
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>