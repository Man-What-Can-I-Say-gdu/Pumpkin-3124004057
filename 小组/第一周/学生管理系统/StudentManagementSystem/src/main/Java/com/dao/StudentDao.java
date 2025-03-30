package com.dao;

import com.entity.Course;

import java.util.ArrayList;

public interface StudentDao {
    /**
     * 获得所有课程信息
     * return 所有课程信息
     */
    ArrayList<Course> getCourses();

    /**
     * 删除已选课程
     * @param StudentId 学生id
     * @param CourseId 删除的课程id
     * return 是否成功删除
     */
    boolean DeleteCourse(int StudentId,int CourseId);

    /**
     * 增加已选课程
     * @param CourseId 添加的课程id
     * @param id 删除对应的用户
     * @return 是否成功添加
     */
    boolean UpdateCourse(int CourseId,int id);

    /**
     * 查询已选课程的信息
     * @param StudentId 学生id
     * @return 学生已选的课程数组
     */
    public ArrayList<Course> getChosenCourse(int StudentId);
}
