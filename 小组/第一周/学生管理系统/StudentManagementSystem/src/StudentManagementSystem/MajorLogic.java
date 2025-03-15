package StudentManagementSystem;

public class MajorLogic {
    public static void main(String[] args) {
        //String[] MenuText = {"登录" , "注册" , "退出"};
        //String title = "登录";
        //System.out.println(Menu.MenuDisplay(MenuText,title));

        try {
            Login.LoginEntrance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
