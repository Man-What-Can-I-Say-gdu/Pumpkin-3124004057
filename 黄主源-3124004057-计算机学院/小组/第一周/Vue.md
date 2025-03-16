# Vue

![1742028608166](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742028608166.png)

## Vue操作

1.新建HTML页面，引入Vue.js文件

<script src="js/vue.js"></script>

2.在JS代码区域创建Vue核心对象，进行数据绑定

new Vue({

​	el:"#app",

​	data(){

​		return{

​			username:""

​		}

​	}

});

3.编写视图

<div id="app">
    <input name="username"v-model="username">
    {{username}}
</div>

## Vue常用指令

![1742029148968](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742029148968.png)

eg：

v-bind：

<a v-bind:href="url">百度一下</a>

<a :href="url">百度一下</a>

v-model:

<input name="username"v-model="username">

v-on

html:<input type="button"value="一个按钮"v-on:click"show()">

<input type="button"value="一个按钮"@click"show()">

![1742029645899](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742029645899.png)

![1742029778379](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742029778379.png)

## Vue生命周期

![1742029893414](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742029893414.png)

![1742029907410](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742029907410.png)