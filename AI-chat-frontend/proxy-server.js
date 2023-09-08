const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');

const app = express();

// 设置代理，将请求转发到目标API
app.use('/api', createProxyMiddleware({
    target: 'https://api.jisuapi.com', // 目标API的基本URL
    changeOrigin: true, // 允许跨域
    pathRewrite: {
        '^/api': '', // 可选：将前端请求中的/api路径重写为空
    },
}));

// 启动代理服务器
const port = 3000; // 代理服务器的端口
app.listen(port, () => {
    console.log(`Proxy server is running on port ${port}`);
});
