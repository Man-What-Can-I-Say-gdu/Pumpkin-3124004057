# listener

监听器

监听器可以监听在application,session,request三个对象创建、销毁或者往其中添加修改删除属性时自动执行代码的功能组件

## listener分类（8个）

![1742014307316](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742014307316.png)

## ServletContextListener使用

1.定义类，实现ServletContextListener接口

![1742014419604](C:\Users\Pumpkin\AppData\Roaming\Typora\typora-user-images\1742014419604.png)

2.在类上添加@WebListener注解