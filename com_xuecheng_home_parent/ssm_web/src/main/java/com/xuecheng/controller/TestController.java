package com.xuecheng.controller;

import com.xuecheng.domain.Test;
import com.xuecheng.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 测试类的表现层
@RestController
@RequestMapping("/test")
public class TestController {
    // 注入测试类的业务层对象
    @Autowired
    private TestService testService;

    // 自定义测试方法测试springMvc
    @RequestMapping("/findAllTest")
    public List<Test> findAllTest() {
        // 调用业务层对象
        List<Test> tests = testService.findAllTest();
        // 结果返回前端
        return tests;
    }
}
