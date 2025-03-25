<<<<<<<< HEAD:黄主源-3124004057-计算机学院/小组/第一周/学生管理系统/StudentManagementSystem/src/main/Java/Operator/Menu.java
package Operator;

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
========
package StudentManagementSystem;

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
>>>>>>>> 2d83697314848c3aee51c5225472089c07daba39:黄主源-3124004057-计算机学院/小组/第一周/学生管理系统/StudentManagementSystem/src/StudentManagementSystem/Menu.java
