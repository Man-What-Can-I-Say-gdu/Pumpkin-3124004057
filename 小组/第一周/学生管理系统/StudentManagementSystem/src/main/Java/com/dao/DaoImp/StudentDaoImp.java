package com.dao.DaoImp;

import com.dao.StudentDao;
import com.DataBasePool.*;
import com.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDaoImp implements StudentDao {
    @Override
    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try{
            //获取连接和SQL语句
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String SQL="Select * from course";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            //获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            //将结果存放至数组中
            while(resultSet.next()){
                Course course = new Course();
                course.setCredits(resultSet.getInt("credits"));
                course.setCourseName(resultSet.getString("courseName"));
                course.setCourseCode(resultSet.getInt("courseCode"));
                courses.add(course);
            }
            //回收连接
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    @Override
    public boolean DeleteCourse(int StudentId,int CourseId) {
        int result;
        try {
            //获取连接和SQL语句
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String DeleteSQL = "Delete from studentmanagementsystem.student_with_course where (student_id,course_id) = (? ,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(DeleteSQL);
            preparedStatement.setInt(1,StudentId);
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

    @Override
    public ArrayList<Course> getChosenCourse(int StudentId) {
        ArrayList<Course> courses = new ArrayList<>();
        try{
            //获取连接和SQL语句
            Connection connection = DataBasePool.ConnectionPool.GetConnection();
            String SQL="Select * from student_with_course where student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,StudentId);
            //获取结果集并将结果集放入数组中
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Course course = new Course();
                course.setCredits(resultSet.getInt("credits"));
                course.setCourseName(resultSet.getString("courseName"));
                course.setCourseCode(resultSet.getInt("courseCode"));
                courses.add(course);
            }
            //回收连接
            preparedStatement.close();
            DataBasePool.ConnectionPool.RecycleConnection(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //返回课程信息
        return courses;
    }


}
