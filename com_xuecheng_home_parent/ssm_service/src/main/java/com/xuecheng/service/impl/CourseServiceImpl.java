package com.xuecheng.service.impl;

import com.xuecheng.domain.Course;
import com.xuecheng.domain.CourseVo;
import com.xuecheng.domain.Teacher;
import com.xuecheng.mapper.CourseMapper;
import com.xuecheng.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

// 课程管理的业务层接口的实现类
@Service
public class CourseServiceImpl implements CourseService {
    // 注入课程管理的持久层对象
    @Autowired
    private CourseMapper courseMapper;

    // 多条件查询列表
    @Override
    public List<Course> findCourseByConditioin(CourseVo courseVo) {
        // 调用课程管理的持久层对象的方法
        List<Course> courses = courseMapper.findCourseByConditioin(courseVo);
        // 结果返回业务层
        return courses;
    }

    // 新增课程管理信息
    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        // 封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVo);
        // 补全信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //保存课程信息
        courseMapper.saveCourse(course);
        // 获取新插入数据的课程id
        int id = course.getId();
        // 封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVo);
        // 补全信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setCourseId(id);
        //保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    // 课程id查询课程管理信息
    @Override
    public CourseVo findCourseById(int id) {
        return courseMapper.findCourseById(id);
    }

    // 修改课程管理信息
    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        // 封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVo);
        // 补全信息
        Date date = new Date();
        course.setUpdateTime(date);
        //修改课程信息
        courseMapper.updateCourse(course);
        // 封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVo);
        // 补全信息
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());
        //修改讲师信息
        courseMapper.updateTeacher(teacher);
    }

    // 修改课程状态信息
    @Override
    public void updateCourseStatus(int status, int id) {
        // 封装数据
        Course course = new Course();
        course.setStatus(status);
        course.setId(id);
        course.setUpdateTime(new Date());
        // 调用课程管理的持久层对象
        courseMapper.updateCourseStatus(course);
    }
}
