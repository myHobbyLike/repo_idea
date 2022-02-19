package com.xuecheng.mapper;

import com.xuecheng.domain.Course;
import com.xuecheng.domain.CourseLesson;
import com.xuecheng.domain.CourseSection;

import java.util.List;

// 课程内容的持久层接口
public interface CourseContentMapper {
    // 自定义方法实现调用业务层 课程管理的id查询章节信息和课时信息
    List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    // 回显章节对应的课程信息
    Course findCourseByCourseId(int courseId);

    // 新增章节信息
    void saveSection(CourseSection section);

    // 修改章节信息
    void updateSection(CourseSection section);

    // 修改章节状态
    void updateSectionStatus(CourseSection section);

    // 新增课时信息
    void saveLesson(CourseLesson lesson);

    // 修改课时信息
    void updateLesson(CourseLesson lesson);
}
