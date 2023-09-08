<script setup>




</script>

<script>

// 引入 axios 库
import axios from 'axios';
function getNewList() {
  const customAxios = axios.create({
    baseURL: 'apis', // 新的基础URL
  });
  return new Promise((resolve, reject) => {
    customAxios.get('/news/get?channel=头条&start=0&num=10&appkey=6532af8b02315960')
        .then(res => {
          resolve(res);
          console.log(res.data.result.list[0]);
        }).catch(error => {
      reject(error);
    });
  });
}

// 创建一个函数来发送请求
export default {
  props: ['title'],
  data() {
    return {
      newsList: []
    };
  },
  created() {
    getNewList().then(res => {
      this.newsList = res.data.result.list;
    }).catch(error => {
      console.error('Error:', error);
    });
  },
  methods: {
    passTitle(title){
      this.$emit('title-updated', title);

    }
  }
};


</script>

<template>
  <div v-for="item in newsList" :key="item.id">
    {{item.src}}: <p class = 'scrollbar-demo-item'>{{ item.title }}</p>
    <el-button class = 'new-button' @click="passTitle(item.title)">加入提问框</el-button><!-- 假设每条新闻包含一个标题字段 -->
  </div>
</template>

<style scoped>
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 65px;
  margin: 5px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-info-light-9);
  color: var(--el-color-danger);

}

.new-button{
  margin-bottom: 20px;
}
</style>