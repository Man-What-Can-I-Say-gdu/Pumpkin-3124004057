package CourseImage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Course {
    public String courseName;
    public int courseCode;
    public int Credits;

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int credits) {
        Credits = credits;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String
    toString() {
        return "课程编号：" +courseCode +"\t 课程名称：" + courseName + "\t所占学分：" + Credits;
    }

    //实现提供课程信息功能
    public static ArrayList<Course> DisplayAllCourses() throws Exception {
        ArrayList<Course> courses = new ArrayList<>();
        Course course = new Course();
        //获取驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接池对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
        //构建查询语句
        String SelectSQL = "select * from course";
        //获取预处理语句
        PreparedStatement preparedStatement = connection.prepareStatement(SelectSQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            course.setCourseCode(resultSet.getInt("course_id"));
            course.setCourseName(resultSet.getNString("course_name"));
            course.setCredits(resultSet.getInt("credits"));
            courses.add(course);
        }
        preparedStatement.close();
        connection.close();
        return courses;
    }


    //实现提供用户课程信息功能
    public static ArrayList<Course> DisplayAllCourses(String SelectUser) throws Exception {
        ArrayList<Course> courses = new ArrayList<>();
        Course course = new Course();
        //获取驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接池对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem?serverTimezone=Asia/Shanghai","root","123456");
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
        connection.close();
        return courses;
    }
}
