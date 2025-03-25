<<<<<<< HEAD:小组/第一周/学生管理系统/StudentManagementSystem/src/main/Java/ProjectRunning/MajorLogic.java
package ProjectRunning;

import Operator.Interaction;
import User_Type.Admin;
import User_Type.Student;
import User_Type.User;


//目前要更改的问题：继承中子类可以继承父类属性，所以在子类中定义父类变量冗余，需要编写一个方法使得子类的对象能够再获取并给公共对象赋值
//部分代码冗余，尝试合并形成工具类或者新方法
//添加注释
public class MajorLogic {
    public static void main(String[] args) throws Exception {
        //进行登录
        User user;
        try {
            user = Interaction.LoginEntrance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(user.getUser_type() == 0){
            Student LoginingStudent = (Student) user;
            LoginingStudent.setUser_type(0);
            //进入学生页面，应该是do，while和switch嵌套形成的菜单
            while (true) Interaction.studentOperate(LoginingStudent);
        }else{
            //获取登录的Admin对象
            Admin LoginingAdmin = (Admin) user;
            LoginingAdmin.setUser_type(1);
            while (true) Interaction.AdminOperate(LoginingAdmin);
        }

    }
}
=======
package ProjectRunning;

import Interation.Interaction;
import User_Type.Admin;
import User_Type.Student;
import User_Type.User;


//目前要更改的问题：继承中子类可以继承父类属性，所以在子类中定义父类变量冗余，需要编写一个方法使得子类的对象能够再获取并给公共对象赋值
//部分代码冗余，尝试合并形成工具类或者新方法
//添加注释
public class MajorLogic {
    public static void main(String[] args) throws Exception {
        //进行登录
        User user;
        try {
            user = Interaction.LoginEntrance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(user.getUser_type() == 0){
            Student LoginingStudent = new Student();
            LoginingStudent.setUser(user);
            LoginingStudent.setUser_type(0);
            //进入学生页面，应该是do，while和switch嵌套形成的菜单
            Interaction.studentOperate(LoginingStudent);
        }else{
            //获取登录的Admin对象
            Admin LoginingAdmin = new Admin();
            LoginingAdmin.setUser(user);
            LoginingAdmin.setUser_type(1);
            Interaction.AdminOperate(LoginingAdmin);
        }

    }
}
>>>>>>> 2d83697314848c3aee51c5225472089c07daba39:黄主源-3124004057-计算机学院/小组/第一周/学生管理系统/StudentManagementSystem/src/main/Java/ProjectRunning/MajorLogic.java
