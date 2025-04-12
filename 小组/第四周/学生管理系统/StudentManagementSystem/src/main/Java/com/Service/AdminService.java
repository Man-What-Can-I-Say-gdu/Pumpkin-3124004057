package com.Service;

import com.entity.Course;
import com.entity.User;

import java.util.ArrayList;

public interface AdminService {
    /**
     * 查询学生的信息
     * @param userName 学生的名称
     * @return 学生除学课外的信息
     */
    User SelectStudentByName(String userName);

    /**
     * 修改学生的手机号
     * @param studentId 修改手机号的学生的名字
     * @param newPhoneNumb 新手机号
     * @return 是否成功修改
     */
    boolean ModifyStudentPhoneNumb(int studentId,String newPhoneNumb);

    /**
     * 删除课程信息
     * @param courseId 课程id
     * @return 是否成功删除
     */
    boolean DeleteCourse(int courseId);

    /**
     * 增加课程
     * @param course 课程信息
     * @return 返回是否增加成功
     */
    boolean AddCourse(Course course);

    /**
     * 查询某个课程的选课信息
     * @param courseId 要查询的课程的id
     * @return 选课课程的学生信息
     */
    ArrayList<User> SelectChosenStudent(int courseId);

    /**
     * 查询所有学生的信息
     * @return 所有学生除选课外的信息
     */
    ArrayList<User> SelectAllStudent();


    /**
     * 修改课程学分
     * @param courseId 课程id
     * @param Credits 学分
     * @return 是否修改成功
     */
    boolean ModifyCredits(int courseId,int Credits);

}
