package com.Service;

import com.entity.Course;

import java.util.ArrayList;

public interface StudentService {

    /**
     * 选课功能
     * @param CourseId 要选的课程id
     * @param UserId 要选课的学生id
     * @return 是否成功添加
     */
    boolean ChooseCourse(int CourseId,int UserId);


    /**
     * 退选
     * @param CourseId 要退选的课程id
     * @param UserId 要退选的学生id
     * @return 是否成功退选
     */
    boolean DeleteChosenCourse(int CourseId,int UserId);

    /**
     * 查询已选课程
     * @param UserId 要查询的学生id
     * @return 已选课程的数组
     */
    ArrayList<Course> CheckChosenCourse(int UserId);


}
