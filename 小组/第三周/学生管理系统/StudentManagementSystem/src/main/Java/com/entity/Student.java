package com.entity;

import com.DataBasePool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{

    private ArrayList<String> ChooseCourse;
    public ArrayList<String> getChooseCourse() {
        return ChooseCourse;
    }
    public void setChooseCourse(ArrayList<String> chooseCourse) {
        ChooseCourse = chooseCourse;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ChooseCourse=" + ChooseCourse +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }

/* //实现查询可选课程课程功能
    public void SelectCourse() throws Exception {
        Course.DisplayAllCourses();
    }
    //实现选课功能
    public void ChosenCourse() throws Exception {
        //根据对应学生信息关联到对应数据库并对数据库进行修改
        //获取学生id
        int id = this.getId();
        //注册驱动
        //获取连接池对象
        Connection connection = ConnectionPool.GetConnection();
        String NeedSQL;
        //获取已经选择了的课程编号和名称
        NeedSQL = "Select course.course_id from student_with_course,course where student_id = ? and course.course_id = student_with_course.course_id";
        //获取预处理语句
        PreparedStatement preparedStatement = connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1,this.getUser_name());
        //获取结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        int ResultNumber = 0;
        while (resultSet.next()) {
            ResultNumber++;
        }
        if (ResultNumber == 5) {
            System.out.println("选课数量达到上现（5个）");
            System.exit(0);
        }
        //展示course：在全集里存在但在用户集里不存在
        NeedSQL = "select course.course_id , course.course_name from course,student_with_course where student_id = ? and student_with_course.course_id!=course.course_id";
        preparedStatement =connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1,this.getUser_name());
        //获取结果集
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println("课程id：" + resultSet.getInt("course_id") + "课程名称：" + resultSet.getString("course_name"));
        }
        System.out.println("请输入要选的课程：");
        //获取要选择的课程id
        Scanner sc = new Scanner(System.in);
        int TempCourseId = sc.nextInt();
        //获取添加课程的SQL
        NeedSQL = "insert into student_with_course (student_id, course_id) values (?, ?)";
        preparedStatement = connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1, String.valueOf(id));
        preparedStatement.setInt(2,TempCourseId);
        int Result = preparedStatement.executeUpdate();
        if (Result > 0){
            System.out.println("添加成功");
        }
        preparedStatement.close();
        ConnectionPool.RecycleConnection(connection);
    }
    //退选
    public void DropCourse() throws Exception {
        //根据对应学生信息关联到对应数据库并对数据库进行修改
        //注册驱动
        Connection connection = ConnectionPool.GetConnection();
        String NeedSQL;
        //获取已经选择了的课程编号和名称
        NeedSQL = "Select course.course_id,course.course_name from student_with_course,course where student_id = ? and course.course_id = student_with_course.course_id";
        //获取预处理语句
        PreparedStatement preparedStatement = connection.prepareStatement(NeedSQL);
        //通过this获得id
        preparedStatement.setInt(1,this.getId());
        //获取结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("已选课程有：");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("course_id") + ":" +resultSet.getString("course_name"));
        }
        System.out.println("请输入要删除的课程的id：");
        //获取要选择的课程id
        Scanner sc = new Scanner(System.in);
        int TempCourseId = sc.nextInt();
        //获取添加课程的SQL
        NeedSQL = "delete from student_with_course where course_id = ?";
        preparedStatement = connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1, String.valueOf(TempCourseId));
        int Result = preparedStatement.executeUpdate();
        if (Result > 0){
            System.out.println("删除成功");
        }
        //回收
        preparedStatement.close();
        ConnectionPool.RecycleConnection(connection);
    }
    //查询已选课程
    public void ChosedCourse() throws Exception {
        //注册驱动
        //获取连接池对象
        Connection connection = ConnectionPool.GetConnection();
        String NeedSQL;
        //获取已经选择了的课程编号和名称
        NeedSQL = "Select course.course_id from student_with_course,course where student_id = ? and course.course_id = student_with_course.course_id";
        //获取预处理语句
        PreparedStatement preparedStatement = connection.prepareStatement(NeedSQL);
        preparedStatement.setInt(1,this.getId());
        //获取结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("已选课程有：");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("course_id") + ":" +resultSet.getString("course_name"));
        }
        //回收preparedstatement和connection
        preparedStatement.close();
        ConnectionPool.RecycleConnection(connection);
    }*/
}
