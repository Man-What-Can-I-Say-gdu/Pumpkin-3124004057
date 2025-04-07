package com.Controller;

import com.entity.User;

import javax.servlet.http.HttpServletResponse;

public interface userController {
    /**
     * 实现登录功能
     * @param user 传入的用户数据
     */
    void LoginController(HttpServletResponse resp, User user);

    /**
     * 实现注册功能
     * @param user 传入的用户数据
     */
    void RegisterController(HttpServletResponse resp , User user);


    /**
     * 主界面的展示
     * @param user 传入的用户数据
     */
    void DisplayMainWindow(User user);



}
