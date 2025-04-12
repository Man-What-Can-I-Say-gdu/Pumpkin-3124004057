package com.entity;

import com.DataBasePool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Course {
    public String course_name;
    public int course_id;
    public int credit;

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String
    toString() {
        return "课程编号：" +course_id +"\t 课程名称：" + course_name + "\t所占学分：" + credit;
    }

    /*//实现提供课程信息功能
    public static ArrayList<Course> DisplayAllCourses() throws Exception {
        ArrayList<Course> courses = new ArrayList<>();
        Course course = new Course();
        //获取驱动
        //获取连接池对象
        Connection connection = ConnectionPool.GetConnection();
        //构建查询语句
        String SelectSQL = "select * from course";
        //获取预处理语句
        PreparedStatement preparedStatement = connection.prepareStatement(SelectSQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        //遍历得到课程信息
        while (resultSet.next()) {
            course.setCourseCode(resultSet.getInt("course_id"));
            course.setCourseName(resultSet.getNString("course_name"));
            course.setCredits(resultSet.getInt("credits"));
            courses.add(course);
        }
        preparedStatement.close();
        ConnectionPool.RecycleConnection(connection);
        return courses;
    }


    //实现提供用户课程信息功能
    public static ArrayList<Course> DisplayAllCourses(String SelectUser) throws Exception {
        ArrayList<Course> courses = new ArrayList<>();
        Course course = new Course();
        //获取驱动
        //获取连接池对象
        Connection connection = ConnectionPool.GetConnection();
        //构建查询语句
        String SelectSQL = "select * from course where course_name = ?" ;
        //获取预处理语句
        PreparedStatement preparedStatement = connection.prepareStatement(SelectSQL);
        preparedStatement.setString(1, SelectUser);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            course.setCourseCode(resultSet.getInt("course_id"));
            course.setCourseName(resultSet.getNString("course_name"));
            course.setCredits(resultSet.getInt("credits"));
            courses.add(course);
        }
        preparedStatement.close();
        ConnectionPool.RecycleConnection(connection);
        return courses;
    }*/
}
