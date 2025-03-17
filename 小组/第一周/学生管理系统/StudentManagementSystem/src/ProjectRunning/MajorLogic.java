package ProjectRunning;

import Interation.Interaction;
import User_Type.Admin;
import User_Type.Student;
import User_Type.User;

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
