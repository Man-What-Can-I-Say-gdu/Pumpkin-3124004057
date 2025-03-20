package Operator;

import User_Type.Admin;
import CourseImage.Course;
import User_Type.Student;
import User_Type.User;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

//实现键盘监听器主要是为了当在任意位置都可以通过Esc进行退出，因此我们只需要重写keyRelease
public class Interaction {
    public static User LoginEntrance() throws Exception {
        //设置表头和选项
        String[] LoginMenuText = {"登录", "注册", "修改密码", "退出"};
        String LoginMenuTitle = "学生管理系统";
        //调用方法以进入登录界面
        User user = new User();
        switch (Menu.MenuDisplay(LoginMenuText, LoginMenuTitle)) {
            case 1:
                user = User.LoginFunction();
                break;
            case 2:
                user = User.RegisterFunction();
                break;
            case 3:
                User.ModifyPassword();
            case 4:
                User.Exit();
        }
        return user;
    }

    //学生端的页面交互
    public static void studentOperate(Student student) throws Exception {
        String[] StudMenuText = {
                "1.查询可选课程",
                "2.选择课程",
                "3.退选课程",
                "4.查询已选课程",
                "5.更改信息",
                "6.退出程序"
        };
        do {
            switch (Menu.MenuDisplay(StudMenuText, "课程管理系统--学生端")) {
                case 1:
                    //查询可选课程
                    ArrayList<Course> courses = Course.DisplayAllCourses();
                    for (Course course : courses) {
                        System.out.println(course);
                    }
                    break;
                case 2:
                    //选择课程
                    System.out.println("========选课========");
                    student.ChosenCourse();
                    break;
                case 3:
                    //推选课程
                    student.DropCourse();
                    break;
                case 4:
                    student.ChosedCourse();
                    break;
                case 5:
                    //修改修改密码的逻辑，使其在没有实例调用与有实例调用时有有不同的验证方法
                    User.ModifyPassword(student);
                    break;
                case 6:
                    User.Exit();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + Menu.MenuDisplay(StudMenuText, "课程管理系统--学生端"));
            }
        } while (true);
    }

    //管理员的页面交互
    public static void AdminOperate(Admin admin) throws Exception {
        String[] AdminMenuText = {
                "1.查询所有学生",
                "2.修改学生手机号",
                "3.查询所有课程",
                "4.修改课程学分",
                "5.查询某课程的学生名单",
                "6.查询某学生的选课情况",
                "7.开设新课",
                "8.删除课程",
                "9.退出"
        };
        do{
            switch (Menu.MenuDisplay(AdminMenuText,"管理员菜单")){
                case 1:
                    //查询所有学生的信息
                    admin.ShowAllStudentImage();
                    break;
                case 2:
                    //修改学生手机号
                    admin.ModifyPhoneNumber();
                    break;
                case 3:
                    //查询所有课程
                    admin.CheckCourse();
                    break;
                case 4:
                    //修改课程学分
                    admin.ResetCredits();
                    break;
                case 5:
                    //查询某课程的学生名单
                    admin.ShowCourseImage();
                    break;
                case 6:
                    //查询某学生的选课情况
                    admin.CheckStudentCourse();
                    break;
                case 7:
                    //开设新课
                    admin.AddCourse();
                    break;
                case 8:
                    //删除课程
                    admin.DeleteCourse();
                    break;
                case 9:
                    User.Exit();
            }
        }while(true);

    }


}