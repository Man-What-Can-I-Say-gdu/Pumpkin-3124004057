package com.dao;

import com.entity.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 根据name判断用户是否存在
     * @param name 用户名
     * @return 是否存在
     */
    abstract boolean IsUserExist(String name);

    /**
     * 根据用户名获取用户信息
     * @param name 用户名
     * @return 用户对象
     */
    abstract User getAllByName(String name) throws SQLException;

    /**
     * 根据ID获取用户信息
     * @param id 用户ID
     * @return 用户对象
     */
    abstract User getAllById(int id);

    /**
     * 添加用户
     * @param user 用户对象
     * @return 是否添加成功
     */
    boolean addUser(User user);

    /**
     * 更新密码
     * @param user 用户对象
     * @param newPassword 新密码
     */
    abstract boolean updatePassword(User user, String newPassword);


    /**
     * 将用户添加到学生类
     * @param userId 用户id
     * @return 是否添加成功
     */
    boolean addStudent(int userId);
}