package Interation;

import java.util.Scanner;

//菜单工具类，通过传入的参数实现菜单选择
public final class Menu {
    private Menu() {
    }
    /*
    *
    *@param MenuText 需要的选项
    *@param title 菜单的标题
    *
     */
    public static int MenuDisplay(String[] MenuText, String Title) {
        Scanner sc = new Scanner(System.in);
        //打印菜单头
        System.out.println("====" + Title + "====");
        //打印菜单尾
        for (int i = 0; i < MenuText.length; i++) {
            System.out.println(i + 1 + ". " + MenuText[i]);
        }
        System.out.println("====" + Title + "====");
        System.out.print("请输入你的选择：");
        //返回参数选择实现业务
        return sc.nextInt();
    }
}
