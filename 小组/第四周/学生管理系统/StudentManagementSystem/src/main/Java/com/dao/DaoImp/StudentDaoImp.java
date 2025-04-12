package com.dao.DaoImp;

import com.dao.StudentDao;
import com.DataBasePool.*;
import com.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDaoImp extends UserDaoImp implements StudentDao {
    @Override
    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try{
            //获取连接和SQL语句
            Connection connection = ConnectionPool.GetConnection();
            String SQL="Select * from course";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            //获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            //将结果存放至数组中
            while(resultSet.next()){
                Course course = new Course();
                course.setCredit(resultSet.getInt("credit"));
                course.setCourse_name(resultSet.getString("courseName"));
                course.setCourse_id(resultSet.getInt("courseCode"));
                courses.add(course);
            }
            //回收连接
            preparedStatement.close();
            ConnectionPool.RecycleConnection(connection);
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
            Connection connection = ConnectionPool.GetConnection();
            String DeleteSQL = "Delete from studentmanagementsystem.student_with_course where (student_id,course_id) = (? ,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(DeleteSQL);
            preparedStatement.setInt(1,StudentId);
            preparedStatement.setInt(2, CourseId);
            //执行SQL语句并获取影响行数
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            ConnectionPool.RecycleConnection(connection);
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
            Connection connection = ConnectionPool.GetConnection();
            String UpdateSQL = "insert into student_with_course (student_id, course_id) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(UpdateSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, CourseId);
            //执行SQL语句并获取影响行数
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            ConnectionPool.RecycleConnection(connection);
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
            Connection connection = ConnectionPool.GetConnection();
            String SQL="Select * from student_with_course,course where student_id = ? && course.course_id = student_with_course.course_id";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,StudentId);
            //获取结果集并将结果集放入数组中
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Course course = new Course();
                course.setCredit(resultSet.getInt("credit"));
                course.setCourse_name(resultSet.getString("course_name"));
                course.setCourse_id(resultSet.getInt("course_id"));
                courses.add(course);
            }
            //回收连接
            preparedStatement.close();
            ConnectionPool.RecycleConnection(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //返回课程信息
        return courses;
    }

    @Override
    public ArrayList<Course> getUnChosenCourse(int userId) {
        ArrayList<Course> UnChosenCourses = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.GetConnection();
            //将存在的课程当作一个表作为查询条件并返回
            String SQL = "Select course.course_id, course_name, credit from course where course_id not in(select course_id from student_with_course where student_with_course.student_id=?)";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Course course = new Course();
                course.setCredit(resultSet.getInt("credit"));
                course.setCourse_name(resultSet.getString("course_name"));
                course.setCourse_id(resultSet.getInt("course_id"));
                UnChosenCourses.add(course);
            }
            preparedStatement.close();
            ConnectionPool.RecycleConnection(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return UnChosenCourses;
    }
}
