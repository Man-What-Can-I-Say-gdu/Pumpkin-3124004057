package com.Service;

import com.entity.Course;

import java.util.ArrayList;

public interface CourseService {


    /**
     * 提供全部课程信息
     * @return 所有课程
     */
    ArrayList<Course> getCourses();

    /**
     * 提供指定课程信息
     * @param courseName 课程名
     * @return 课程信息
     */
    Course getCourse(String courseName);









}
