<script setup>

</script>

<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style = "flex:1">
      <el-image style="width: 100%;height: 100%;fit:cover" src="https://pic.rmb.bdstatic.com/bjh/news/4bb52bb388ebba54f32d6ce1848a24833574.png"/>
    </div>
    <div style = "width:400px;background-color: white">
      <router-view/>
    </div>

  </div>
</template>

<style scoped>

</style>