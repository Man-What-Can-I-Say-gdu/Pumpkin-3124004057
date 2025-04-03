package com.Service.ServiceImp;

import com.Service.CourseService;
import com.dao.CourseDao;
import com.dao.DaoImp.CourseDaoImp;
import com.entity.Course;

import java.util.ArrayList;

public class CourseServiceImp implements CourseService {
    CourseDao courseDao = new CourseDaoImp();

    public CourseServiceImp(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public ArrayList<Course> getCourses() {

        return courseDao.findAll();
    }

    @Override
    public Course getCourse(String courseName) {
        return courseDao.findByName(courseName);
    }
}
