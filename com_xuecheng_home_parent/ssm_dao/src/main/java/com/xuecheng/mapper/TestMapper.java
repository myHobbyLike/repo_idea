package com.xuecheng.mapper;

import com.xuecheng.domain.Test;

import java.util.List;
// 测试类的持久层接口
public interface TestMapper {
    // 自定义方法测试mybatis
    List<Test> findAllTest();
}
