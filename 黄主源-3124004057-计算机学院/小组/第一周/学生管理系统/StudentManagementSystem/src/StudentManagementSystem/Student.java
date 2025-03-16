package StudentManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        String NeedSQL = "Select count(course_id) from user , student ,student_with_course, course " +
                "where user.name = ? and user.id = student.user_id and" +
                " student_with_course.course_id = course.course_id and" +
                " student_with_course.student_id = student.user_id";
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
        NeedSQL = "select course.course_name from student,course,student_with_course,user " +
                " where ? = user.name and user.id = student.user_id and student.user_id=student_with_course.student_id and " +
                "student_with_course.course_id != course.course_id";
        preparedStatement =connection.prepareStatement(NeedSQL);
        preparedStatement.setString(1,this.getUser_name());
    }
}
