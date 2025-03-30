package com.dao.DaoImp;
import com.dao.UserDao;
import com.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDaoImp implements UserDao{


    @Override
    public boolean IsUserExist(String name) {
        //获取User对象，用于存放注册的用户
        User user = new User();
        //从数据库中获取connection
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBasePool.ConnectionPool.GetConnection();
            //构建语句对象，先对输入的姓名内容进行查重
            String SelectSql = "select * from user where name=?";
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SelectSql);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //填充占位符
        if(!(name.isEmpty())){
            //不为空的话,则对user进行赋值
            user.setUser_name(name);
            try {
                if (preparedStatement != null) {
                    preparedStatement.setString(1,user.getUser_name());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            //使用断言表示该处变量不为null
            assert connection != null;
            DataBasePool.ConnectionPool.RecycleConnection(connection);
                //返回是否结果是否存在
            assert preparedStatement != null;
            return preparedStatement.executeQuery().next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //如果预处理语句对象不存在，返回用户不存在
    }

    @Override
    public User getAllByName(String name) throws SQLException {
        User user = new User();
        Connection connection;
        PreparedStatement preparedStatement = null;
        String SelectSQL = "select * from user where name=?";
        ResultSet resultSet;
        try{
            connection = DataBasePool.ConnectionPool.GetConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SelectSQL);
                preparedStatement.setString(1,name);
            }
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //赋值user
                user.setUser_name(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone_number(resultSet.getString("phone_number"));
                user.setUser_type(resultSet.getInt("user_type"));
                user.setId(resultSet.getInt("id"));
                //关闭预处理语句并回收连接
                preparedStatement.close();
                DataBasePool.ConnectionPool.RecycleConnection(connection);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User getAllById(int id) {
        User user = new User();
        Connection connection;
        PreparedStatement preparedStatement = null;
        String SelectSQL = "select * from user where id=?";
        ResultSet resultSet;
        try{
            connection = DataBasePool.ConnectionPool.GetConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(SelectSQL);
                preparedStatement.setInt(1,id);
            }
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //赋值user
                user.setUser_name(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone_number(resultSet.getString("phone_number"));
                user.setUser_type(resultSet.getInt("user_type"));
                user.setId(resultSet.getInt("id"));
                //关闭预处理语句并回收连接
                preparedStatement.close();
                DataBasePool.ConnectionPool.RecycleConnection(connection);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return user;
    }



    @Override
    public boolean addUser(User user) {
        //从数据库中获取connection
        Connection connection;
        boolean result = false;
        try {
            connection = DataBasePool.ConnectionPool.GetConnection();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        PreparedStatement preparedStatement = null;
        //获取插入数据的语句
        String AddUserData = "insert into user(name,password,user_type,phone_number) values (?,?,?,?)";
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(AddUserData);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            if (preparedStatement != null) {
                //填充preparedStatement对象
                preparedStatement.setString(1, user.getUser_name());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setInt(3, user.getUser_type());
                preparedStatement.setString(4, user.getPhone_number());
                //执行sql语句
                result = preparedStatement.execute();
                //回收连接
                preparedStatement.close();
                DataBasePool.ConnectionPool.RecycleConnection(connection);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean updatePassword(User user, String newPassword){
        //获取链接对象
        Connection connection;
        //获取预编译对象
        PreparedStatement preparedStatement;
        //设置SQL语句
        String UpdateSQL = "update user set phone_number=? where name =?";
        //获取resultSet
        ResultSet resultSet;
        try {
            connection = DataBasePool.ConnectionPool.GetConnection();
            preparedStatement = connection.prepareStatement(UpdateSQL);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,user.getUser_name());
            //回收连接
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
            //返回更新结果
            return preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addStudent(int userId) {
        boolean result = false;
        try {
            //获取连接和SQL语句
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String SQL = "insert into student  values ?";
            //获取预处理语句并获得结果
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,userId);
            result = preparedStatement.execute();
            //回收连接
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
