package com.Service.ServiceImp;
import com.dao.UserDao;
import com.Service.UserService;
import com.entity.User;

import java.sql.SQLException;

public class UserServiceImp implements UserService {
    private final UserDao userDao;
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean Register(User user) {
        //1.验证用户是否存在
        if (userDao.IsUserExist(user.getUser_name())){
            //存在报错
            throw new RuntimeException("User is exist");
        }
        //不存在：添加
        return userDao.addUser(user);
    }

    @Override
    public User Login(String name, String password) {
        //1.验证用户是否存在，不存在报错，存在执行下一步
        if(!userDao.IsUserExist(name)){
            return null;
        }
        //存在：验证密码是否正确
        User LoginUser;
        try {
            LoginUser = userDao.getAllByName(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //2.验证密码是否正确
        if(password.equals(LoginUser.getPassword())){
            return LoginUser;
        }else {
            throw new RuntimeException("Wrong password");
        }
    }

    @Override
    public boolean ModifyPassword(User user, String newPassword) {
        return userDao.updatePassword(user, newPassword);
    }

    @Override
    public User GetAllByUserName(String UserName) {
        try {
            return userDao.getAllByName(UserName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean VerifyIdentity(int UserId, String PhoneNumber) {
        return PhoneNumber.equals(userDao.getAllById(UserId).getPhone_number());
    }

    @Override
    public boolean PutUserToStudentBase(int userId) {
        return userDao.addStudent(userId);
    }
}
