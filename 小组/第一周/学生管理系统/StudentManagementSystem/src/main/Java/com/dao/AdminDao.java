package com.dao;

import com.entity.Course;
import com.entity.User;

import java.util.ArrayList;

public interface AdminDao {
    /**
     * 查询学生信息
     * @param username 学生名
     * @return 学生的所有信息
    */
    abstract User SelectStudentImage(String username);

    /**
     * 修改学生手机号
     * @param id 学生id
     * @param newPhoneNumb 新手机号
     * @return 是否修改成功
     */
    abstract boolean ModifyStudentPhone(int id, String newPhoneNumb);

    /**
     * 删除课程信息
     * @param Course_id 课程id
     * @return 是否删除成功
     */
    abstract boolean DeleteCourse(int Course_id);


    /**
     * 增加课程
     * @param course 课程信息
     * @return 返回是否增加成功
     */
    abstract boolean UpdateCourse(Course course);

    /**
     * 查询某个课程的选课信息
     *
     * @param course_id 课程id
     * @return 选该课程的学生的信息
     */
    abstract ArrayList<String> SelectChosenCourseName(int course_id);

    /**
     * 查询所有学生的信息
     * @return 所有学生除选课外的信息
     */
    abstract ArrayList<User> SelectStudent();

    /**
     * 修改课程学分
     * @param course_id 课程id
     * @param credits 学分情况
     * @return 是否修改成功
     */
    abstract boolean ModifyCredits(int credits,int course_id);
}
