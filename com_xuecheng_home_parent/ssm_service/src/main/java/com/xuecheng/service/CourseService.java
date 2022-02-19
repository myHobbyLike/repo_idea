package com.xuecheng.service;

import com.xuecheng.domain.Course;
import com.xuecheng.domain.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

// 课程管理的业务层接口
public interface CourseService {
    // 多条件查询列表
    List<Course> findCourseByConditioin(CourseVo courseVo);

    // 新增课程管理信息
    void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    // 课程id查询课程管理信息
    CourseVo findCourseById(int id);

    // 修改课程管理信息
    void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    // 修改课程状态信息
    void updateCourseStatus(int status, int id);
}
