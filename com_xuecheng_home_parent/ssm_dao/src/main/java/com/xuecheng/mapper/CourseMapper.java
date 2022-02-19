package com.xuecheng.mapper;

import com.xuecheng.domain.Course;
import com.xuecheng.domain.CourseVo;
import com.xuecheng.domain.Teacher;

import java.util.List;

// 课程管理的持久层接口
public interface CourseMapper {
    // 多条件查询列表
    List<Course> findCourseByConditioin(CourseVo courseVo);

    //保存课程信息
    void saveCourse(Course course);

    // 保存讲师信息
    void saveTeacher(Teacher teacher);

    // 课程id查询课程管理信息
    CourseVo findCourseById(int id);

    //修改课程信息
    void updateCourse(Course course);

    //修改讲师信息
    void updateTeacher(Teacher teacher);

    // 修改课程状态信息
    void updateCourseStatus(Course course);
}
