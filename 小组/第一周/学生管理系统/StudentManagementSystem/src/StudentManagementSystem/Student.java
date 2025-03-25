package StudentManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{
    public User user;
    public ArrayList<String> ChooseCourse;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<String> getChooseCourse() {
        return ChooseCourse;
    }

    public void setChooseCourse(ArrayList<String> chooseCourse) {
        ChooseCourse = chooseCourse;
    }


    //实现查询可选课程课程功能
    public void SelectCourse() throws Exception {
        Course.DisplayAllCourses();
    }
    //实现选课功能
    public void ChosenCourse() throws Exception {
        //根据对应学生信息关联到对应数据库并对数据库进行修改
        //获取学生的名称
        String StudentName = this.getUser_name();
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接池对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
        String NeedSQL;
        //获取学生id
        NeedSQL = "SELECT id from user where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1, StudentName);
        ResultSet resultSet = preparedStatement.executeQuery();
        //设置学生id用于查找
        int id = -1;
        while (resultSet.next()) {
            id = Integer.parseInt(resultSet.getString("id"));
        }
        //获取已经选择了的课程编号和名称
        NeedSQL = "Select course.course_id from student_with_course,course where student_id = ? and course.course_id = student_with_course.course_id";
        //获取预处理语句
        preparedStatement = connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1,this.getUser_name());
        //获取结果集
        resultSet = preparedStatement.executeQuery();
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
    }
    //退选
    public void DropCourse() throws Exception {
        //根据对应学生信息关联到对应数据库并对数据库进行修改
        //获取学生的名称
        String StudentName = this.getUser_name();
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接池对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
        String NeedSQL;
        //获取学生id
        NeedSQL = "SELECT id from user where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1, StudentName);
        ResultSet resultSet = preparedStatement.executeQuery();
        //设置学生id用于查找
        int id = -1;
        while (resultSet.next()) {
            id = Integer.parseInt(resultSet.getString("id"));
        }
        //获取已经选择了的课程编号和名称
        NeedSQL = "Select course.course_id,course.course_name from student_with_course,course where student_id = ? and course.course_id = student_with_course.course_id";
        //获取预处理语句
        preparedStatement = connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1,this.getUser_name());
        //获取结果集
        resultSet = preparedStatement.executeQuery();
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
    }
    //查询已选课程
    public void ChosedCourse() throws Exception {
        String StudentName = this.getUser_name();
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接池对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
        String NeedSQL;
        //获取学生id
        NeedSQL = "SELECT id from user where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1, StudentName);
        ResultSet resultSet = preparedStatement.executeQuery();
        //设置学生id用于查找
        int id = -1;
        while (resultSet.next()) {
            id = Integer.parseInt(resultSet.getString("id"));
        }
        //获取已经选择了的课程编号和名称
        NeedSQL = "Select course.course_id from student_with_course,course where student_id = ? and course.course_id = student_with_course.course_id";
        //获取预处理语句
        preparedStatement = connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1,this.getUser_name());
        //获取结果集
        resultSet = preparedStatement.executeQuery();
        System.out.println("已选课程有：");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("course_id") + ":" +resultSet.getString("course_name"));
        }
    }
}
