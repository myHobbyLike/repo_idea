package com.xuecheng.controller;

import com.github.pagehelper.PageInfo;
import com.xuecheng.domain.*;
import com.xuecheng.service.PromotionAdService;
import com.xuecheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
// 用户的表现层
public class UserController {
    // 注入用户的业务层对象
    @Autowired
    private UserService userService;

    // 自定义查询带条件的用户信息加分页方法
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllAdByPage(@RequestBody UserVo userVo) {
        PageInfo pageInfo = userService.findAllUserByPage(userVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", pageInfo);
        List<User> list = pageInfo.getList();
        System.out.println(list);
        return responseResult;
    }

    // 修改用户状态
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, @RequestParam String status) {
        if ("ENABLE".equalsIgnoreCase(status)) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }
        userService.updateUserStatus(id, status);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", status);
        return responseResult;
    }
}
