package com.Service.ServiceImp;

import com.Service.AdminService;
import com.dao.AdminDao;
import com.dao.DaoImp.AdminDaoImp;
import com.entity.Admin;
import com.entity.Course;
import com.entity.User;

import java.util.ArrayList;

public class AdminServiceImp implements AdminService {
    AdminDao adminDao = new AdminDaoImp();

    public AdminServiceImp(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public User SelectStudentByName(String userName) {
        return adminDao.SelectStudentImage(userName);
    }

    @Override
    public boolean ModifyStudentPhoneNumb(int studentId, String newPhoneNumb) {
        return adminDao.ModifyStudentPhone(studentId,newPhoneNumb);
    }

    @Override
    public boolean DeleteCourse(int courseId) {
        return adminDao.DeleteCourse(courseId);
    }

    @Override
    public boolean AddCourse(Course course) {
        return adminDao.UpdateCourse(course);
    }

    @Override
    public ArrayList<User> SelectChosenStudent(int courseId) {
        return adminDao.SelectChosenCourseName(courseId);
    }

    @Override
    public ArrayList<User> SelectAllStudent() {
        return adminDao.SelectStudent();
    }

    @Override
    public boolean ModifyCredits(int courseId, int Credits) {
        return adminDao.ModifyCredits(courseId,Credits);
    }
}
