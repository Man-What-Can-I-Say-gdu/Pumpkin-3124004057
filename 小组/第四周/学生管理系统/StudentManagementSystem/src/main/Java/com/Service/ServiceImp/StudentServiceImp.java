package com.Service.ServiceImp;

import com.Service.StudentService;
import com.dao.DaoImp.StudentDaoImp;
import com.dao.StudentDao;
import com.entity.Course;

import java.util.ArrayList;

public class StudentServiceImp implements StudentService {
    private StudentDaoImp studentDao;

    public StudentServiceImp(StudentDaoImp studentDao) {
        this.studentDao = studentDao;
    }

    public StudentDaoImp getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDaoImp studentDao) {
        this.studentDao = studentDao;
    }


    @Override
    public boolean ChooseCourse(int CourseId, int UserId) {
        return studentDao.UpdateCourse(CourseId,UserId);
    }

    @Override
    public boolean DeleteChosenCourse(int CourseId, int UserId) {
        return studentDao.DeleteCourse(UserId,CourseId);
    }

    @Override
    public ArrayList<Course> CheckChosenCourse(int UserId) {
        return studentDao.getChosenCourse(UserId);
    }

    @Override
    public ArrayList<Course> GetUnChosenCourse(int UserId) {
        return studentDao.getUnChosenCourse(UserId);
    }
}
