//
//
//
//
// const BASE_URL = process.env.NODE_ENV === 'development'
//     ?'http://localhost:8080/StudentManagementSystem'
//     :

//
// module.exports = {
//     devServer: {
//         proxy:{
//             '/api':{
//                 target: 'http://localhost:8080/StudentManagementSystem',
//                 changeOrigin: true,
//                 pathRewrite: {'^/api':''}
//             }
//         }
//     }
// };
//
// axios.post('/api/login',data);