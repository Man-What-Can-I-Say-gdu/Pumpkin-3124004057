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
                LoginFunction();
            case 2:
                RegisterFunction();
        }

        return null;
    }

    //实现注册功能
    private static User RegisterFunction() throws Exception {
        User user = new User();
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
        Statement statement = connection.createStatement();
        String AddUserData = "insert into user(name,password,user_type) values (?,?,?)";
        preparedStatement = connection.prepareStatement(AddUserData);
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入你的用户名(长度在10个字符以内)：");
        user.setUser_name(scan.next());
        System.out.print("请输入你的密码(长度在8~20位)：");
        user.setPassword(scan.next());
        System.out.println("请选择你的用户类型(数字)：");
        System.out.println("1.学生");
        System.out.println("2.管理员");
        user.setUser_type(scan.nextInt());
        preparedStatement.setString(1,user.getUser_name());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, UserType.
        boolean execute = preparedStatement.execute();
        preparedStatement.close();
        connection.close();
        System.out.println(user);
        return user;
    }

    private static void LoginFunction(){

    }


}
