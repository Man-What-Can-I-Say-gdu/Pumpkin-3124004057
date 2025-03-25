package StudentManagementSystem;
public class Login {

    public static User LoginEntrance() throws Exception {
        //设置表头和选项
        String[] LoginMenuText = {"登录" , "注册" , "修改密码" , "退出"};
        String LoginMenuTitle = "学生管理系统";
        //调用方法以进入登录界面
        User user = new User();
        switch (Menu.MenuDisplay(LoginMenuText , LoginMenuTitle)){
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
        return null;
    }
}