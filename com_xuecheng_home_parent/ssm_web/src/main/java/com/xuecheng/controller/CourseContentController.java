package com.xuecheng.controller;

import com.xuecheng.domain.*;
import com.xuecheng.service.CourseContentService;
import com.xuecheng.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 课程内容的表现层
@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    // 注入课程内容的业务层对象
    @Autowired
    private CourseContentService courseContentService;

    // 自定义方法实现调用业务层 课程管理的id查询章节信息和课时信息
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(int courseId) {
        // 调用业务层对象的方法
        List<CourseSection> courseSections = courseContentService.findSectionAndLessonByCourseId(courseId);
        // 把结果返回
        return new ResponseResult(true, 200, "响应成功", courseSections);
    }

    // 回显章节对应的课程信息
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(int courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
        return result;
    }

    // 新增或修改章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {
        try {
            if (null == section.getId()) {
                courseContentService.saveSection(section);
                return new ResponseResult(true, 200, "响应成功", null);
            } else {
                courseContentService.updateSection(section);
                return new ResponseResult(true, 200, "响应成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改章节状态 * 状态，0:隐藏；1：待更新；2：已发布 *
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id, @RequestParam int status) {
        try {
            courseContentService.updateSectionStatus(id, status); //封装最新的状态信息
            Map<String, Integer> map = new HashMap<>();
            map.put("status", status);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 新增或修改课时信息
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson) {
        try {
            if (null == lesson.getId()) {
                courseContentService.saveLesson(lesson);
                return new ResponseResult(true, 200, "响应成功", null);
            } else {
                courseContentService.updateLesson(lesson);
                return new ResponseResult(true, 200, "响应成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
