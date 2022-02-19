package com.xuecheng.service;

import com.github.pagehelper.PageInfo;
import com.xuecheng.domain.PromotionAd;
import com.xuecheng.domain.PromotionAdVo;
import com.xuecheng.domain.UserVo;

// 用户的业务层接口
public interface UserService {
    // 自定义查询带条件的用户信息加分页方法
    PageInfo findAllUserByPage(UserVo userVo);
    // 修改用户状态
    void updateUserStatus(int id, String status);
}
