package com.dao;

import com.entity.Course;

import java.util.ArrayList;

public interface CourseDao {
    /**
     * 提供课程信息
     * @return 课程信息的数组
     */
    abstract ArrayList<Course> findAll();

    /**
     * 提供指定课程信息
     * @param course_name 课程名
     * @return 课程信息
     */
    abstract Course findByName(String course_name);







}
