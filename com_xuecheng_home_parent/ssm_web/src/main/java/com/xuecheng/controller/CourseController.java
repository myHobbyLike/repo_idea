package com.xuecheng.controller;

import com.xuecheng.domain.Course;
import com.xuecheng.domain.CourseVo;
import com.xuecheng.domain.ResponseResult;
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

// 课程管理的表现层
@RestController
@RequestMapping("/course")
public class CourseController {
    // 注入课程管理的业务层对象
    @Autowired
    private CourseService courseService;

    // 自定义方法实现调用业务层
    @RequestMapping("/findAllCourse")
    public ResponseResult findCourseByConditioin(@RequestBody CourseVo courseVo) {
        // 调用业务层对象的方法
        List<Course> courses = courseService.findCourseByConditioin(courseVo);
        // 把结果返回
        return new ResponseResult(true, 200, "响应成功", courses);
    }

    // 文件上传
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 判断文件是不是空的
        if (file.isEmpty()) {
            // 是空的，抛异常
            throw new RuntimeException();
        }
        // 获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");
        // System.out.println(realPath); // D:\software\java\tomcat\apache-tomcat-8.5.55-windows-x64\apache-tomcat-8.5.55\webapps\ssm-web\
        // webapps目录 D:\software\java\tomcat\apache-tomcat-8.5.55-windows-x64\apache-tomcat-8.5.55\webapps\
        String path = realPath.substring(0, realPath.indexOf("ssm-web"));
        // 文件上传到服务器保存文件的位置
        String filePath = path + "upload\\";
        // System.out.println(filePath);
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 定义新文件名
        String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        // 创建file对象
        File f = new File(filePath, newFileName);
        // 判断该父目录存在吗
        if (!f.getParentFile().exists()) {
            // 该父目录不存在就创建目录
            f.getParentFile().mkdirs();
        }
        // 上传文件
        file.transferTo(f);
        // 将文件名和文件路径返回
        Map<String, String> map = new HashMap<String, String>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080" + "/upload/" + newFileName);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", map);
        return responseResult;
    }

    // 新增课程管理信息
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        if (null == courseVo.getId()) {
            courseService.saveCourseOrTeacher(courseVo);
            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
            return responseResult;
        } else {
            courseService.updateCourseOrTeacher(courseVo);
            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
            return responseResult;
        }
    }

    // 课程id查询课程管理信息
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam int id) {
        try {
            CourseVo courseVo = courseService.findCourseById(id);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", courseVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 修改课程状态信息
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int status, int id) {
        // 调用课程管理的业务层对象
        courseService.updateCourseStatus(status, id);
        // 保存修改后的状态并返回结果
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("status", status);
        return new ResponseResult(true, 200, "响应成功", map);
    }
}
