<<<<<<< HEAD:小组/第一周/Vue.md
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

事件监听![1742740930619](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742740930619.png)

事件关联![屏幕截图 2025-03-23 222049](C:\Users\Pumpkin\Pictures\Screenshots\屏幕截图 2025-03-23 222049.png)

## Vue生命周期

![1742029893414](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742029893414.png)

![1742029907410](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742029907410.png)



![1742743772712](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742743772712.png)

![1742745273137](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742745273137.png)

![1742745788877](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742745788877.png)



![1742746323294](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742746323294.png)
=======
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
>>>>>>> 2d83697314848c3aee51c5225472089c07daba39:黄主源-3124004057-计算机学院/小组/第一周/笔记/Vue.md
