package com.dao.DaoImp;

import com.dao.AdminDao;
import com.entity.Course;
import com.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminDaoImp implements AdminDao {
    @Override
    public User SelectStudentImage(String username) {
        User user = new User();
        try {
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String SQL = "select * from user where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUser_type(resultSet.getInt("user_type"));
                user.setPhone_number(resultSet.getString("phone_number"));
                user.setPassword(resultSet.getString("password"));
                user.setUser_name(username);
            }
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean ModifyStudentPhone(int id, String newPhoneNumb) {
        boolean result = false;
        try {
            //获取连接和SQL语句
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String SQL = "update user set phone_number=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            //填充
            preparedStatement.setString(1, newPhoneNumb);
            preparedStatement.setInt(2, id);
            //获取结果：是否成功修改
            result = preparedStatement.execute();
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean DeleteCourse(int Course_id) {
        boolean result = false;
        try{
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String SQL = "delete from course where course.course_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,Course_id);
            result = preparedStatement.execute();
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean UpdateCourse(Course course) {
        boolean result = false;
        try {
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String AddCourseSQL = "insert into course(course_name, credit) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(AddCourseSQL);
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2,course.getCredits());
            result = preparedStatement.execute();
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ArrayList<String> SelectChosenCourseName(int course_id) {
        ArrayList<String> result = new ArrayList<String>();
        try{
            //获取连接
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String SQL = "select user.name from course,student_with_course,user,student where course_id = ? and "+
                    "student_with_course.student_id = user.id and student_with_course.course_id = course.course_id";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,course_id);
            //得到结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            //遍历过去结果
            int i = 0;
            while (resultSet.next()) {
                result.add(resultSet.getString("name"));
            }
            //关闭连接池
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public ArrayList<User> SelectStudent() {
        ArrayList<User> result = new ArrayList<>();
        try{
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String SQL = "select * from user";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            while(resultSet.next()){
                user.setUser_name(resultSet.getString("user_name"));
                user.setUser_type(resultSet.getInt("user_type"));
                user.setPhone_number(resultSet.getString("phone_number"));
                user.setPassword(resultSet.getString("password"));
                user.setId(resultSet.getInt("id"));
                result.add(user);
            }
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean ModifyCredits(int credits, int course_id) {
        boolean result = false;
        try{
            //获取连接
         Connection connection = DataBasePool.ConnectionPool.GetConnection();
         String SQL = "update course set credit=? where id=?";
         PreparedStatement preparedStatement = connection.prepareStatement(SQL);
         //填充预处理语句
         preparedStatement.setInt(1,credits);
         preparedStatement.setInt(2,course_id);
         //获取修改结果
         result = preparedStatement.execute();
         //回收连接
         preparedStatement.close();
         DataBasePool.ConnectionPool.RecycleConnection(connection);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }
}
