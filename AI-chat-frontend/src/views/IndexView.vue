<script setup>

 import {get} from "@/net";
 import {ElMessage} from "element-plus";
 import router from "@/router";
 import Sidebar from "@/components/Sidebar.vue";
 import { Search } from '@element-plus/icons-vue';
 import { ref } from 'vue'
 import news from '@/components/news.vue'

 const input = ref('')


</script>



<script>
import news from '@/components/news.vue'
import { computed } from 'vue';
import { useStore } from 'vuex';

import axios from 'axios'
// 后端解决跨域导入cors包
 export default {
   components: {
     news
   },
   data() {
     return {
       askStr: " 简要列举两个历史上的类似事件",
       reply: "",
       parentTitle: ''

     };
   },
   methods: {
     handleTitleUpdate(newTitle) {
       this.parentTitle = newTitle;
       this.askStr = newTitle + " 简要列举两个历史上的类似事件"},

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

  <sidebar />



  <div class = 'body'>
    <el-row :gutter="20">
      <el-col :span="6">
      <div class="grid-content ep-bg-purple" />
<!--    使用v-model 双向绑定数据-->
    <el-input class = "input-q" v-model="askStr" @keyup.enter="fetchReply" placeholder="请输入问题">

      <template #append>
        <el-button :icon="Search" @click="fetchReply" round>获取回复</el-button>
        </template>
      </el-input>



    <el-card class="small-card">

      <p>您的问题：{{ askStr }}</p>
    <p>AI回复：{{ reply }}</p>

    </el-card>


    </el-col>

    <el-col :span="6"><div class="grid-content ep-bg-purple" />
    <!-- 保存记录的按钮 -->

      <el-card class="box-card">
        <template #header>
        <div class="card-header">

          <el-button @click="saveRecord" type="info" text>保存记录</el-button>
          <el-button @click="clearRecords" type="info" text>清空记录</el-button>
          <span>问答历史 <el-icon><List/></el-icon></span>


        </div>
          </template>


    <!-- 显示之前的记录 -->
        <el-scrollbar height="500px">
    <div v-for="(record, index) in allRecords" :key="index">

      <p class="scrollbar-demo-item">问题{{ index + 1 }}：{{ record.ask }}</p>
      <p>回复：{{ record.reply }}</p>

    </div>
          </el-scrollbar>
      </el-card>


    </el-col>
      <el-col :span="5"><div class="grid-content ep-bg-purple" /><el-card class="box-card2">
        <template #header>
          <div class="card-header">
            <span>今日头条新闻 <el-icon><Reading /></el-icon></span>

            </div>
          </template>
        <el-scrollbar height="500px">
        <news :title="parentTitle" @title-updated="handleTitleUpdate"/>
        </el-scrollbar></el-card></el-col>
    </el-row>

  </div>

</template>

<style scoped>
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  margin: 5px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}
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

.box-card {
  width: 480px;
  height: 600px;
  margin-top:80px;
}

.box-card2 {
  width: 480px;
  height: 600px;
  margin-top:80px;
  margin-left: 200px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;

}

.small-card{
  margin:20px;

}

.input-q{
  margin: 10px;
  width: 1300px;
}

.body{
  background-color: lightsteelblue;
  height: 92vh;
}

</style>