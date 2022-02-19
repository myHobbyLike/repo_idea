package com.xuecheng.service;

import com.xuecheng.domain.Test;

import java.util.List;
// 测试类的业务层接口
public interface TestService {
    // 自定义方法测试spring
    List<Test> findAllTest();
}
