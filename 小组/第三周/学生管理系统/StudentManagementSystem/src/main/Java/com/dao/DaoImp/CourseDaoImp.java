package com.dao.DaoImp;

import com.DataBasePool.ConnectionPool;
import com.dao.CourseDao;
import com.entity.Course;

import java.sql.*;
import java.util.ArrayList;

public class CourseDaoImp implements CourseDao {

    @Override
    public ArrayList<Course> findAll() {
        ArrayList<Course> result= new ArrayList<Course>();
        try {
            Connection connection = ConnectionPool.GetConnection();
            String SQL = "select * from course";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setCourse_name(resultSet.getString("course_name"));
                course.setCourse_id(resultSet.getInt("course_id"));
                course.setCredit(resultSet.getInt("course_credits"));
                result.add(course);
            }
            preparedStatement.close();
            ConnectionPool.RecycleConnection(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Course findByName(String course_name) {
        Course result=new Course();
        try{
            Connection connection = ConnectionPool.GetConnection();
            String SQL = "select * from course where course_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result.setCredit(resultSet.getInt("course_credits"));
                result.setCourse_name(resultSet.getString("course_name"));
                result.setCourse_id(resultSet.getInt("course_id"));
            }
            preparedStatement.close();
            ConnectionPool.RecycleConnection(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
