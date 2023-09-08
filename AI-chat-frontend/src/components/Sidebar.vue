<script setup>
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {Platform} from "@element-plus/icons-vue";

const logout =()=>{
  get('api/auth/logout',(message)=>{
    ElMessage.success(message)
    router.push('/')
  })
}

// 从 LocalStorage 获取用户名
const username = localStorage.getItem('username');
</script>

<template>
  <el-aside width="100%">
    <el-menu :default-active="defaultActive" class="el-menu--horizontal" @select="handleMenuSelect">
      <el-menu-item index="0"><el-icon :size="60"><Platform/></el-icon>智能编辑</el-menu-item>
      <el-menu-item>
        <a href="https://github.com/shyiming007/AI-QA" target="_blank" class="no-underline">
          <el-icon><View /></el-icon>
           代码开源
        </a>
      </el-menu-item>
      <div class="flex-grow" />


      <p>用户名: {{username}}</p>
      <el-menu-item @click="logout" index="退出"><el-icon><User /></el-icon>退出登陆</el-menu-item>


    </el-menu>
  </el-aside>
</template>

<script>
import { ElMenu, ElMenuItem } from 'element-plus';

export default {
  components: {
    ElMenu,
    ElMenuItem,

  },
  data() {
    return {
      defaultActive: 'home'
    };
  },
  methods: {
    handleMenuSelect(index) {
      // 处理菜单项选中事件
      console.log('Selected:', index);
    }
  }
};
</script>

<style>
/* 样式可以根据需要自行调整 */
@import 'element-plus/dist/index.css';
.flex-grow {
  flex-grow: 1;
}

.no-underline {
  text-decoration: none; /* 移除下划线 */
}

</style>
