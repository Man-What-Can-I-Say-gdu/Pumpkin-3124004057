package User_Type;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class User {

    public enum UserType{
        TypeIsStudent(0),
        TypeIsAdmin(1);
        private int value;
        UserType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public static UserType getUserType(int value){
            for (UserType userType : UserType.values()) {
                if(userType.getValue() == value){
                    return userType;
                }
            }
            return null;
        }
    }
    public int id;
    public String user_name;
    private String password;
    private int user_type;
    public String phone_number;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUser_name() {
        return user_name;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public int getUser_type() {
        return user_type;
    }
    public User setUser_type(int user_type) {
        this.user_type = user_type;
        return this;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(phone_number, user.phone_number);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(phone_number);
    }
    @Override
    public String toString() {
        return "User{" +
                "user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", user_type=" + user_type +
                '}';
    }
    //实现注册功能
    public static User RegisterFunction() throws Exception {
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
        String AddUserData = "insert into user(name,password,user_type,phone_number) values (?,?,?,?)";
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
        while(user.setUser_type(scan.nextInt()).getUser_type() > 2 || user.getUser_type()<=0){
            System.out.print("输入错误，请重新选择：");
        }
        System.out.println("请输入你的手机号：");
        String PhoneNumb;
        //判断输入的手机号码是否符合规定
        while (true){
            PhoneNumb = scan.next();
            if(!Pattern.matches("^\\d{11}$",PhoneNumb)){
                System.out.println("输入的电话号码格式错误，请重新输入");
            }else{
                user.setPhone_number(PhoneNumb);
                break;
            }
        }
        //填充占位符
        preparedStatement.setString(1,user.getUser_name());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3,(user.getUser_type()-1));
        preparedStatement.setString(4,user.getPhone_number());
        //到此为止的user正常
        //执行sql语句，并判断是否能够创建成功
        if (!preparedStatement.execute()){
            System.out.println("创建用户失败：未知错误");
        }
        preparedStatement.close();
        connection.close();
        return user;
    }
    //实现登录功能
    public static User LoginFunction() throws ClassNotFoundException, SQLException {
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
        preparedStatement = connection.prepareStatement(JudgeSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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
    //实现退出功能
    public static void Exit(){
        System.exit(0);
    }
    //修改密码:通过唯一凭证（电话号码）修改密码,登陆界面使用
    public static void ModifyPassword() throws ClassNotFoundException, SQLException {
        System.out.println("请输入要修改密码的账号：");
        //建立链接
        Class.forName("com.mysql.jdbc.Driver");
        //获取链接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
        //获取预编译对象
        PreparedStatement preparedStatement;
        //设置SQL语句
        String SelectSql = "select name, phone_number from user where name =?";
        preparedStatement = connection.prepareStatement(SelectSql);
        //获取resultSet
        ResultSet resultSet;
        Scanner scanner = new Scanner(System.in);
        String UserName;
        while (true){
            UserName = scanner.next();
            preparedStatement.setString(1,UserName);
            if(!(resultSet = preparedStatement.executeQuery()).next()){
                System.out.println("不存在该用户，请重新输入：");
            }else{
                String CorrectPhoneNumber = resultSet.getString("phone_number");
                System.out.println("请输入该账号绑定的手机号：");
                if(CorrectPhoneNumber.equals(scanner.next())){
                    System.out.println("请输入新密码:");
                    String NewPassword = scanner.next();
                    String UpdateSQL = "update user set phone_number=? where name =?";
                    preparedStatement = connection.prepareStatement(UpdateSQL);
                    preparedStatement.setString(1,NewPassword);
                    preparedStatement.setString(2,UserName);
                    if(preparedStatement.executeUpdate()>0){
                        System.out.println("操作成功！");
                        break;
                    }
                }
            }
        }
        preparedStatement.close();
        connection.close();
    }
    //修改密码：已登录的用户登录
    public static void ModifyPassword(User user) throws ClassNotFoundException, SQLException {
        //建立链接
        Class.forName("com.mysql.jdbc.Driver");
        //获取链接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
        //获取预编译对象
        PreparedStatement preparedStatement;
        //设置SQL语句
        String SelectSql = "select name, phone_number from user where name =?";
        preparedStatement = connection.prepareStatement(SelectSql);
        //获取resultSet
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);
        preparedStatement.setString(1,user.getUser_name());
        String CorrectPhoneNumber = resultSet.getString("phone_number");
        System.out.println("请输入该账号绑定的手机号：");
        if(CorrectPhoneNumber.equals(scanner.next())){
            System.out.println("请输入新密码:");
            String NewPassword = scanner.next();
            String UpdateSQL = "update user set phone_number=? where name =?";
            preparedStatement = connection.prepareStatement(UpdateSQL);
            preparedStatement.setString(1,NewPassword);
            preparedStatement.setString(2,user.getUser_name());
            if(preparedStatement.executeUpdate()>0){
                System.out.println("操作成功！");

                }
            }
        preparedStatement.close();
        connection.close();
    }
    //展示用户数据
    public void Display() throws Exception {
        //获取驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
        //获取预处理对象
        PreparedStatement preparedStatement;
        //获取SQL语句
        String SelectSql = "select * from user where name =?";
        //封装SQL语句
        preparedStatement = connection.prepareStatement(SelectSql);
        preparedStatement.setString(1,this.user_name);
        //获取结果集对象
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("用户名：" + resultSet.getString("name")
                    +",电话号码：" + resultSet.getString("phone_number")
                    + ",用户类型为：" + UserType.getUserType(resultSet.getInt(user_type)));
        }
    }
    //身份验证：
    //错误：身份验证传入的参数出现问题
    public boolean VerifyIdentity() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
        PreparedStatement preparedStatement;
        String SelectSql = "select phone_number,id from user where name =?";
        preparedStatement = connection.prepareStatement(SelectSql);
        //this指针出现问题
        preparedStatement.setString(1,this.getUser_name());
        ResultSet resultSet = preparedStatement.executeQuery();
        User OperaterUser = new User();
        while(resultSet.next()){
            OperaterUser.setPhone_number(resultSet.getString("phone_number"));
            OperaterUser.setId(resultSet.getInt("id"));
        }
        System.out.println("请输入手机号码：");
        Scanner scanner = new Scanner(System.in);
        String PhoneNumber = scanner.next();
        System.out.println(OperaterUser);
        preparedStatement.close();
        connection.close();
        //空指针报错，发生错误字段“OperaterUser.getPhone_number()”
        return OperaterUser.getPhone_number().equals(PhoneNumber);
    }
}
