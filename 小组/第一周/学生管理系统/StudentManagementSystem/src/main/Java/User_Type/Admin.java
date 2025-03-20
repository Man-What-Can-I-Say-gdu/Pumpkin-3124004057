package User_Type;

import CourseImage.Course;
import DataBasePool.ConnectionPool;

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
        //获取数据库连接
        Connection connection = ConnectionPool.GetConnection();
        //编写SQL语句：用于获取学生的全部信息
        String CheckOutSQL = "select * from student,user where user.name =?";
        //将SQL传入PreparedStatement对象获取学生信息
        PreparedStatement preparedStatement = connection.prepareStatement(CheckOutSQL);
        preparedStatement.setString(1,StudentName);
        ResultSet result = preparedStatement.executeQuery();
        //遍历获取学生的信息并打印
        while(result.next()){
            System.out.println("学生名称：" + result.getString("name"));
            System.out.println("电话号码：" + result.getString("phone_number"));
        }
        preparedStatement.close();
        ConnectionPool.RecycleConnection(connection);
    }
    //修改学生手机号
    public void ModifyPhoneNumber() throws Exception{
        //对管理员身份进行核对
        this.VerifyIdentity();
        //创建Scanner对象用于获取输入的学生信息
        Scanner sc = new Scanner(System.in);
        //连接数据库
        Connection connection = ConnectionPool.GetConnection();
        //获取学生的姓名，用于指定修改手机号的学生账号
        String name = sc.nextLine();
        //编写更新学生手机号的SQL
        String ModifySQL = "update user set phone_number = ? where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(ModifySQL);
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
        preparedStatement.setString(2,name);
        if(preparedStatement.executeUpdate() <= 0){
            System.out.println("添加失败！请检查输入的学生姓名是否正确！");
            //递归实现对学生姓名重新判断
            ModifyPhoneNumber();
        }
        System.out.println("添加成功！");
        preparedStatement.close();
        ConnectionPool.RecycleConnection(connection);
    }
    //查询所有课程信息
    public void CheckCourse() throws Exception{
        //for循环遍历课程
        for (Course displayAllCourse : Course.DisplayAllCourses()) {
            System.out.println(displayAllCourse);
        }
    }
    //对课程进行删除
    public void DeleteCourse() throws Exception {
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
        //连接数据库
        Connection connection = ConnectionPool.GetConnection();
        //删除course
        String DeleteCourseSQL = "delete from course where course_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(DeleteCourseSQL);
        preparedStatement.setInt(1,DeleteCourseId);
        preparedStatement.executeUpdate();
        //对删除操作后的结果进行判断，若影响行数小于等于0则删除失败，若大于0则删除成功
        if(preparedStatement.executeUpdate() <= 0){
            System.out.println("删除失败！");
        }else{
            System.out.println("删除成功！");
        }
        //删除和学生的关联
        DeleteCourseSQL = "delete from student_with_course where course_id =?";
        preparedStatement = connection.prepareStatement(DeleteCourseSQL);
        //打印是否成功的信息
        if(preparedStatement.executeUpdate() != 0){
            System.out.println("已断开删除课程与学生的关联");
        }else{
            System.out.println("删除课程与学生无关联");
        }
        preparedStatement.close();
        ConnectionPool.RecycleConnection(connection);
    }
    //查询某个课程的选课信息
    public void ShowCourseImage() throws Exception {
        //声名变量CourseName用于存放需要查询的课程的信息
        String CourseName;
        Scanner sc = new Scanner(System.in);
        Connection connection = ConnectionPool.GetConnection();
        //获取课程Id用于关联其他表进行查询
        String GetCourseIdSQL ="select course_id from course where course_name=?";
        PreparedStatement preparedStatement =connection.prepareStatement(GetCourseIdSQL);
        //获取课程名称并通过名称获得ID
        System.out.println("请输入你要查询的课程名：");
        CourseName = sc.nextLine();
        preparedStatement.setString(1,CourseName);
        ResultSet result = preparedStatement.executeQuery();
        //声名课程ID变量，初始值为-1，方便进行判断
        int CourseId = -1;
        if(result.next()){
            CourseId = result.getInt("course_id");
        }
        //隐式多表联查，通过id获取选课的人的名字
        String ShowCourseImageSQL = "select user.name from course,student_with_course,user,student where course_id = ? and " +
                "student_with_course.student_id = user.id and student_with_course.course_id = course.course_id";
        preparedStatement = connection.prepareStatement(ShowCourseImageSQL);
        preparedStatement.setInt(1,CourseId);
        result = preparedStatement.executeQuery();
        //对选了该课程的人的名字进行打印
        System.out.println("选了这门课的人有：");
        while(result.next()){
            System.out.print(result.getString("name"));
        }
        preparedStatement.close();
        connection.close();
    }
    //对课程进行增加
    public void AddCourse() throws Exception{
        //对管理员身份进行判断，防止误操作
        if(!this.VerifyIdentity()){
            return;
        }
        //展示所有课程信息，目的是让管理员能够知道可以操作的课程数量
        this.CheckCourse();
        //获取要添加的课程名称
        System.out.println("请输入要添加的课程名称：");
        Scanner sc = new Scanner(System.in);
        String AddCourseName = sc.nextLine();
        //获取驱动
        Connection connection = ConnectionPool.GetConnection();
        String FindRepetitionSQL = "select * from course where course_name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(FindRepetitionSQL);
        //对需要添加的课程信息进行查重，若已存在则重新获取课程名称
        preparedStatement.setString(1,AddCourseName);
        ResultSet result = preparedStatement.executeQuery();
        if(result.next()){
            System.out.println("添加的课程已存在，请重新输入");
            AddCourse();
        }
        //要添加的课程不存在，可以执行添加操作
        String AddCourseSQL = "insert into course values ?";
        preparedStatement = connection.prepareStatement(AddCourseSQL);
        preparedStatement.setString(1,AddCourseName);
        preparedStatement.executeUpdate();
        //对添加的结果进行判断
        if(preparedStatement.executeUpdate() <= 0){
            System.out.println("添加课程失败！");
        }else{
            System.out.println("添加课程成功！");
        }
        preparedStatement.close();
        ConnectionPool.RecycleConnection(connection);
    }
    //查询某个学生的选课信息
    public void CheckStudentCourse() throws Exception{
        //获取学生的姓名
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要查询的学生的名称：");
        String StudentName = sc.nextLine();
        //调用展示学生信息的方法
        ArrayList<Course> StudentCourseImage = Course.DisplayAllCourses(StudentName);
        for (Course course : StudentCourseImage) {
            System.out.println(course);
        }
    }
    //查询所有学生的信息
    public void ShowAllStudentImage() throws Exception{
        //进行身份验证，防止信息泄露
        if(!this.VerifyIdentity()){
            System.out.println("身份验证失败！");
        }else {
            //获取数据库连接
            Connection connection = ConnectionPool.GetConnection();
            //SQL作用是获得用户的全部信息
            String ShowAllStudentImageSQL = "select * from user";
            PreparedStatement preparedStatement = connection.prepareStatement(ShowAllStudentImageSQL);
            ResultSet result = preparedStatement.executeQuery();
            //对结果进行打印
            while(result.next()){
                System.out.println( result.getString("name") + ":" + result.getString("phone_number"));
            }
            preparedStatement.close();
            ConnectionPool.RecycleConnection(connection);
        }
    }
    //修改课程学分
    public void ResetCredits() throws Exception{
        //进行身份验证
        if(!this.VerifyIdentity()){
            System.out.println("身份验证失败，请重新验证");
        }else{
            //获取驱动
            Connection connection = ConnectionPool.GetConnection();
            String UpdateCreditsSQL = "update course set credit =? where course.course_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(UpdateCreditsSQL);
            Scanner sc = new Scanner(System.in);
            //获取需要修改的课程的名字
            System.out.println("请输入要更改的课程名：");
            String CourseName = sc.nextLine();
            //获取需要修改的课程的学分
            System.out.println("请输入要新的学分：");
            int Credits = sc.nextInt();
            preparedStatement.setInt(1,Credits);
            preparedStatement.setString(2,CourseName);
            preparedStatement.executeUpdate();
            //对影响行数进行判断，并提示修改结果
            if(preparedStatement.executeUpdate() <= 0){
                System.out.println("修改失败！请检查课程输入是否正确");
                //递归进行修改学分
                ResetCredits();
            }else{
                System.out.println("修改成功！");
                preparedStatement.close();
                ConnectionPool.RecycleConnection(connection);
            }
        }
    }
}