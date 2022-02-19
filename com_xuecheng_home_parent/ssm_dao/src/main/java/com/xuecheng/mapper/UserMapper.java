package com.xuecheng.mapper;

import com.xuecheng.domain.PromotionAd;
import com.xuecheng.domain.User;
import com.xuecheng.domain.UserVo;

import java.util.List;

// 用户的持久层接口
public interface UserMapper {
    // 自定义查询带条件的用户信息加分页方法
    List<User> findAllUser(UserVo userVo);

    // 修改用户状态
    void updateUserStatus(User user);
}
