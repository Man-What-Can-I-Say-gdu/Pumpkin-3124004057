package com.Service;

import com.entity.User;
import com.dao.UserDao;
public interface UserService {
    /**
     * 实现注册功能
     * @param user 注册的用户
     * @return 是否注册成功
     */
    boolean Register(User user);

    /**
     * 登陆功能：验证密码与名字是否一样
     * @param name 登录的用户名
     * @param password 登录的密码
     * @return 成功登录：用户的信息，失败：空user对象
     */
    User Login(String name,String password);

    /**
     * 修改密码
     * @param newPassword 新的密码
     * @param user 用户信息
     * @return 是否成功修改
     */
    boolean ModifyPassword(User user,String newPassword);

    /**
     * 身份验证
     * @param PhoneNumber 输入的手机号码，作为身份验证凭证
     * @param UserId 用户的id
     * @return 是否正确
     */
    boolean VerifyIdentity(int UserId,String PhoneNumber);

    /**
     * 获取用户id
     * @param UserName 用于获取用户id的用户名
     * @return 返回学生id
     */
    User GetAllByUserName(String UserName);


    /**
     * 将用户数据存放到student表中
     * @param userId 添加的用户数据
     * @return 是否存放成功
     */
    boolean PutUserToStudentBase(int userId);
}
