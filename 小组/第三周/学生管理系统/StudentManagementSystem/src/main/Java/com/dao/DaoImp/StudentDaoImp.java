package com.dao.DaoImp;

import com.dao.StudentDao;
import com.DataBasePool.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDaoImp implements StudentDao {
    @Override
    public Integer[] GetChosenCourseId(int id){
        //最多选6门课
        Integer[] result = new Integer[6];
        try{
            //创建连接
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            //获取查找语句
            String NeedSQL = "Select course.course_id from student_with_course,course where student_id = ? and course.course_id = student_with_course.course_id";
            PreparedStatement preparedStatement = connection.prepareStatement(NeedSQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //将结果存放导要返回的数组中
            int i =0;
            while(resultSet.next()){
                result[i] = resultSet.getInt("id");
                i++;
            }
            //回收连接
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public String GetCourseName(int CourseId) {
        String result = null;
        try {
            //获取连接和查询的SQL语句
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String SelectSQL = "Select course.course_name from course where course_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SelectSQL);
            preparedStatement.setInt(1, CourseId);
            //获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("course_name");
            }
            //回收连接
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean DeleteCourse(int CourseId) {
        int result;
        try {
            //获取连接和SQL语句
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String DeleteSQL = "Delete from course where course_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(DeleteSQL);
            preparedStatement.setInt(1, CourseId);
            //执行SQL语句并获取影响行数
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result > 0;
    }

    @Override
    public boolean UpdateCourse(int CourseId,int id) {
        int result;
        try {
            //获取连接和SQL语句
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String UpdateSQL = "insert into student_with_course (student_id, course_id) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(UpdateSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, CourseId);
            //执行SQL语句并获取影响行数
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result > 0;
    }


}
