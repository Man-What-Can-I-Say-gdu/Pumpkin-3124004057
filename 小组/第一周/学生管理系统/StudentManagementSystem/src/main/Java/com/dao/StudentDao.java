package com.dao;

public interface StudentDao {
    /**
     * 获得已选课程id
     * @param id 学生id
     * return 课程id
     */
    abstract Integer[] GetChosenCourseId(int id);


    /**
     *  通过课程id展示课程
     *  @param CourseId 课程id
     *  return 课程名称
     */
    abstract String[] GetCourseName(int CourseId);

    /**
     * 删除已选课程
     * @param CourseId 删除的课程id
     * return 是否成功删除
     */
    abstract boolean DeleteCourse(int CourseId);

    /**
     * 增加已选课程
     * @param CourseId 添加的课程id
     * @return 是否成功添加
     */
    abstract boolean UpdateCourse(int CourseId);
}
