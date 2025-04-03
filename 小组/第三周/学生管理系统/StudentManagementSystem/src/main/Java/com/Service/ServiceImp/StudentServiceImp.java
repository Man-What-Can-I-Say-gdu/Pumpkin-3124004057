package com.Service.ServiceImp;

import com.Service.StudentService;
import com.dao.StudentDao;
import com.entity.Course;

import java.util.ArrayList;

public class StudentServiceImp implements StudentService {
    private StudentDao studentDao;
    public void setStudentDao(StudentDao studentDao) {
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
}
