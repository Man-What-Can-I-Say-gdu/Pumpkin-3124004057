const { merge } = require('webpack-merge');
const common = require('./webpack.common.js');

module.exports = merge(common, {
    mode: 'development',
    API: '"/api"',
    devtool: 'inline-source-map',
    devServer: {
        liveReload: true,
        hot: true,
        open: true,
        static: ['./'],
    },
    proxyTable: {
        '/api': {
            target: 'http://localhost:8080/StudentManagementSystem', //  请求后台的真实路径，这里是我本地启动的一个后台项目
            changeOrigin: true, //
            pathRewrite: {
                '^/api': '' // 重写路径
            }
        },
    },
});
