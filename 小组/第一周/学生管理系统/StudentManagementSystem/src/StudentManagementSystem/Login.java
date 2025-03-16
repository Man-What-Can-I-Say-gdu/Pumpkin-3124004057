package StudentManagementSystem;
import java.sql.*;
import java.util.Scanner;

public class Login {

    public static User LoginEntrance() throws Exception {
        //设置表头和选项
        String[] LoginMenuText = {"登录" , "注册" , "修改密码" , "退出"};
        String LoginMenuTitle = "学生管理系统";
        //调用方法以进入登录界面
        switch (Menu.MenuDisplay(LoginMenuText , LoginMenuTitle)){
            case 1:
                System.out.println(LoginFunction());
                break;
            case 2:
                RegisterFunction();
                break;
            case 3:
                Exit();
        }

        return null;
    }

    //实现注册功能
    private static User RegisterFunction() throws Exception {
        //获取User对象，用于存放注册的用户
        User user = new User();
        //注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //获取和数据库间的连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");

        //构建语句对象，先对输入的姓名内容进行查重
        PreparedStatement preparedStatement;
        String SelectSql = "select * from user where name=?";
        preparedStatement = connection.prepareStatement(SelectSql);

        Scanner scan = new Scanner(System.in);
        //输入用户名，同时对用户名进行判断，
        while(true){
            System.out.print("请输入你的用户名(长度在10个字符以内)：");
            user.setUser_name(scan.next());
            //填充占位符
            preparedStatement.setString(1,user.getUser_name());
            //执行sql语句，同时对结果进行判断,若用户存在则提示重新输入，不存在则创建用户
            if (preparedStatement.executeQuery().next()){
                System.out.println("该用户已存在，请重新输入:");
            }else{
                break;
            }
        }
        //设置sql语句，这里的作用是：添加数据
        String AddUserData = "insert into user(name,password,user_type) values (?,?,?)";
        preparedStatement = connection.prepareStatement(AddUserData);
        //输入密码并判断密码长度
        System.out.print("请输入你的密码(长度在8~20位)：");
        while (true){
            user.setPassword(scan.next());
            if(user.getPassword().length() < 8 || user.getPassword().length() > 20){
                System.out.println("请重新输入:");
            }else{
                break;
            }
        }
        System.out.println("请选择你的用户类型(数字)：");
        System.out.println("1.学生");
        System.out.println("2.管理员");
        while(user.setUser_type(scan.nextInt()).getUser_type() >= 2){
            System.out.print("输入错误，请重新选择：");
        }
        //填充占位符
        preparedStatement.setString(1,user.getUser_name());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3,user.getUser_type());
        //执行sql语句，并判断是否能够创建成功
        if (!preparedStatement.execute()){
            System.out.println("创建用户失败：未知错误");
        }
        preparedStatement.close();
        connection.close();
        System.out.println(user);
        return user;
    }

    private static User LoginFunction() throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        User user = new User();
        Connection connection;
        //获取与数据库的链接
        try{
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        //创建预处理对象
        PreparedStatement preparedStatement;
        //创建sql语句：查询
        String SelectSql = "select name,user_type,password from user where name=?";
        //添加sql到预处理对象中
        preparedStatement = connection.prepareStatement(SelectSql);
        //获取添加账户的昵称，在库中匹配
        System.out.print("请输入要登陆的账号的昵称：");
        Scanner scan = new Scanner(System.in);
        String userName;
        //判断用户是否存在
        while (true){
            //获取用户名
            userName = scan.next();
            //填充占位符
            preparedStatement.setString(1,userName);
            System.out.println(userName);
            //判断用户是否存在
            if (preparedStatement.executeQuery().next()){
                break;
            }else{
                System.out.println("该用户不存在，请重新输入：");
            }
        }
        //进行下一条查询的SQL语句
        String JudgeSQL = "select * from user where name=? and password=?";
        preparedStatement = connection.prepareStatement(JudgeSQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1,userName);
        String password;
        //输入密码，并对密码进行判断
        System.out.println("请输入密码：");
        ResultSet resultSet;
        while(true){
            password = scan.next();
            //判断密码的长度是否符合要求
            if(password.length()<10 || password.length()>20){
                System.out.println("输入的密码格式错误（密码应为10~20个字符）");
                System.out.println("请重新输入：");
                continue;
            }
            //填充密码的占位符
            preparedStatement.setString(2,password);
            //获取结果集
            resultSet = preparedStatement.executeQuery();
            //判断密码是否正确，若密码错误，数据库中无法找到对应数据
            if(resultSet.next()){
                //将结果导入user变量中
                user.setUser_name(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setUser_type(resultSet.getInt("user_type"));
                break;
            }else{
                //
                System.out.println("密码错误，请重新输入：");
            }
        }
        System.out.println("登陆成功，欢迎" + user.getUser_name());
        //返回user变量，方便后续进行判断
        return user;
    }
    public static void Exit(){
        System.exit(0);
    }

}
