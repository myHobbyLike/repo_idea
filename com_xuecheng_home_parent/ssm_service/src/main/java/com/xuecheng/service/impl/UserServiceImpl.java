package com.xuecheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuecheng.domain.PromotionAd;
import com.xuecheng.domain.PromotionAdVo;
import com.xuecheng.domain.User;
import com.xuecheng.domain.UserVo;
import com.xuecheng.mapper.PromotionAdMapper;
import com.xuecheng.mapper.UserMapper;
import com.xuecheng.service.PromotionAdService;
import com.xuecheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

// 用户的业务层接口的实现类
@Service
public class UserServiceImpl implements UserService {
    // 注入用户的持久层对象
    @Autowired
    private UserMapper userMapper;

    // 自定义查询带条件的用户信息加分页方法
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        // 使用pageHelper
        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> allUser = userMapper.findAllUser(userVo);
        PageInfo<User> pageInfo = new PageInfo<User>(allUser);
        return pageInfo;
    }
    // 修改用户状态
    @Override
    public void updateUserStatus(int id, String status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        userMapper.updateUserStatus(user);
    }
}
