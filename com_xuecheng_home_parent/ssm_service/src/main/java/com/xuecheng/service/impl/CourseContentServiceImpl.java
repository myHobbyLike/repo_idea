package com.xuecheng.service.impl;

import com.xuecheng.domain.Course;
import com.xuecheng.domain.CourseLesson;
import com.xuecheng.domain.CourseSection;
import com.xuecheng.mapper.CourseContentMapper;
import com.xuecheng.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

// 课程内容的业务层接口的实现类
@Service
public class CourseContentServiceImpl implements CourseContentService {
    // 注入课程内容的持久层对象
    @Autowired
    private CourseContentMapper courseContentMapper;

    // 自定义方法实现调用业务层 课程管理的id查询章节信息和课时信息
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
        // 调用课程内容的持久层对象的方法
        List<CourseSection> courseSections = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        // 结果返回
        return courseSections;
    }

    // 回显章节对应的课程信息
    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    // 新增章节信息
    @Override
    public void saveSection(CourseSection section) {
        //补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);
        courseContentMapper.saveSection(section);
    }

    // 修改章节信息
    @Override
    public void updateSection(CourseSection section) {
        //补全信息
        Date date = new Date();
        section.setUpdateTime(date);
        courseContentMapper.updateSection(section);
    }

    // 修改章节状态
    @Override
    public void updateSectionStatus(int id, int status) {
        //封装数据
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(section);
    }

    // 新增课时信息
    @Override
    public void saveLesson(CourseLesson lesson) {
        //补全信息
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);
        courseContentMapper.saveLesson(lesson);
    }

    // 修改课时信息
    @Override
    public void updateLesson(CourseLesson lesson) {
        // 补全信息
        Date date = new Date();
        lesson.setUpdateTime(date);
        courseContentMapper.updateLesson(lesson);
    }
}
