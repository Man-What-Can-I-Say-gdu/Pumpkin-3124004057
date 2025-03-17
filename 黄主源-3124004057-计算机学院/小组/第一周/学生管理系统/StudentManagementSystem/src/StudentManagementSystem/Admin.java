package StudentManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Admin extends User{
    public int[] AdminId;
    public String varchar;

    public int[] getAdminId() {
        return AdminId;
    }

    public void setAdminId(int[] adminId) {
        AdminId = adminId;
    }

    public String getVarchar() {
        return varchar;
    }

    public void setVarchar(String varchar) {
        this.varchar = varchar;
    }
    //查询学生信息
    public void CheckOut(String StudentName) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem","root","123456");
        String CheckOutSQL = "select * from student,user where user.name =?";
        PreparedStatement preparedStatement = connection.prepareStatement(CheckOutSQL);
        preparedStatement.setString(1,StudentName);
        ResultSet result = preparedStatement.executeQuery();
        while(result.next()){
            System.out.println("学生名称：" + result.getString("name"));
            System.out.println("电话号码：" + result.getString("phone_number"));
        }
        int StudentId = result.getInt("student_id");
        CheckOutSQL = "select * from student_with_course where student_id =?";
        preparedStatement = connection.prepareStatement(CheckOutSQL);
        preparedStatement.setInt(1,StudentId);
        result = preparedStatement.executeQuery();
        System.out.println("学生课程信息");
        while(result.next()){
            System.out.println(result.getString("student_id")+":" + result.getString("name"));
        }
        preparedStatement.close();
        connection.close();
    }
    //修改学生手机号
    public void ModifyPhoneNumber(String StudentName) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem","root","123456");
        String SQL ="select id from user where user.name =?";
        //
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1,StudentName);
        ResultSet result = preparedStatement.executeQuery();
        int id =-1;
        while(result.next()){
             id = result.getInt("id");
        }
        String ModifySQL = "update user set phone_number = ? where id = ?";
        preparedStatement = connection.prepareStatement(ModifySQL);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入新手机号：");
        String phoneNumber;
        while(true){
            phoneNumber = sc.nextLine();
            if(!Pattern.matches("\\d{11}$",phoneNumber)){
                System.out.println("格式有误，请重新输入：");
            }else{
                break;
            }
        }
        preparedStatement.setString(1,phoneNumber);
        preparedStatement.setInt(2,id);
        if(preparedStatement.executeUpdate() <= 0){
            System.out.println("添加失败！");
            System.exit(0);
        }
        System.out.println("添加成功！");
        preparedStatement.close();
        connection.close();
    }
    //查询所有课程信息
    public void CheckCourse() throws Exception{
        Course.DisplayAllCourses();
    }
    //对课程信息进行删除
    private void DeleteCourse() throws Exception {
        //验证管理员身份并询问是否确认删除，若否则返回
        if(!this.VerifyIdentity()){
            System.out.println("验证失败！");
        }
        //展示课程信息
        this.CheckCourse();
        //输入要删除的课程id并删除，同时对绑定的学生信息进行清除
        System.out.println("请输入需要修改的课程的id：");
        Scanner sc = new Scanner(System.in);
        int DeleteCourseId = sc.nextInt();
        //链接
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem","root","123456");
        String DeleteCourseSQL = "delete from course where course_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(DeleteCourseSQL);
        preparedStatement.setInt(1,DeleteCourseId);
        preparedStatement.executeUpdate();
        if(preparedStatement.executeUpdate() <= 0){
            System.out.println("删除失败！");
        }else{
            System.out.println("删除成功！");
        }
        preparedStatement.close();
        connection.close();
    }
    //查询某个课程的选课信息
    public void ShowCourseImage(String CourseName) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem","root","123456");
        String GetCourseIdSQL ="select course_name from course where course_name=?";
        PreparedStatement preparedStatement =connection.prepareStatement(GetCourseIdSQL);
        preparedStatement.setString(1,CourseName);
        ResultSet result = preparedStatement.executeQuery();
        int CourseId = -1;
        while(result.next()){
            CourseId = result.getInt("course_id");
        }
        String ShowCourseImageSQL = "select user.name from course,student_with_course,user,student where course_id = ? and " +
                "student_with_course.student_id = user.id and student_with_course.course_id = course.course_id";
        preparedStatement = connection.prepareStatement(ShowCourseImageSQL);
        preparedStatement.setInt(1,CourseId);
        result = preparedStatement.executeQuery();
        System.out.println("选了这门课的人有：");
        while(result.next()){
            System.out.print(result.getString("name"));
        }
        preparedStatement.close();
        connection.close();
    }
    //对课程信息进行增加
    private void AddCourse() throws Exception{
        if(!this.VerifyIdentity()){
            return;
        }
        this.CheckCourse();
        System.out.println("请输入要添加的课程名称：");
        Scanner sc = new Scanner(System.in);
        String AddCourseName = sc.nextLine();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementSystem","root","123456");
        String FindRepetitionSQL = "select * from course where course_name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(FindRepetitionSQL);
        preparedStatement.setString(1,AddCourseName);
        ResultSet result = preparedStatement.executeQuery();
        if(result.next()){
            System.out.println("添加的课程已存在，请重新输入");
        }
        String AddCourseSQL = "insert into course values ?";
        preparedStatement = connection.prepareStatement(AddCourseSQL);
        preparedStatement.setString(1,AddCourseName);
        preparedStatement.executeUpdate();
        if(preparedStatement.executeUpdate() <= 0){
            System.out.println("添加课程失败！");
        }else{
            System.out.println("添加课程成功！");
        }
        preparedStatement.close();
        connection.close();
    }
    //查询某个学生的选课信息
    private void CheckStudentCourse(String StudentName) throws Exception{
        ArrayList<Course> StudentCourseImage = Course.DisplayAllCourses(StudentName);
        for (Course course : StudentCourseImage) {
            System.out.println(course);
        }
    }
}
