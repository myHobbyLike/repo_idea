package com.xuecheng.service.impl;

import com.xuecheng.domain.Test;
import com.xuecheng.mapper.TestMapper;
import com.xuecheng.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 测试类的业务层接口的实现类对象
@Service
public class TestServiceImpl implements TestService {
    // 注入测试类的持久层对象
    @Autowired
    private TestMapper testMapper;

    // 自定义方法测试spring
    @Override
    public List<Test> findAllTest() {
        // 调用持久层
        List<Test> tests = testMapper.findAllTest();
        // 返回表现层
        return tests;
    }
}
