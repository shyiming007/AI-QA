import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  server: {

    open: true,
    overlay: {
      warnings: false,
      errors: true
    },
    proxy: {
      // axios请求中带有/apis的url,就会触发代理机制
      '/apis': {
        target: 'https://api.jisuapi.com',
        secure: false,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/apis/,""),
      },
},
  },
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})

