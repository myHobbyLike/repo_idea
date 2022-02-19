package com.xuecheng.controller;

import com.github.pagehelper.PageInfo;
import com.xuecheng.domain.PromotionAd;
import com.xuecheng.domain.PromotionAdVo;
import com.xuecheng.domain.PromotionSpace;
import com.xuecheng.domain.ResponseResult;
import com.xuecheng.service.PromotionAdService;
import com.xuecheng.service.PromotionSpaceService;
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
@RequestMapping("/PromotionAd")
// 广告位的表现层
public class PromotionAdController {
    // 注入广告的业务层对象
    @Autowired
    private PromotionAdService promotionAdService;

    // 自定义查询所有广告信息加分页方法
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVo promotionAdVo) {
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllAdByPage(promotionAdVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", pageInfo);
        return responseResult;
    }

    // 文件上传
    @RequestMapping("/PromotionAdUpload")
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

    // 自定义添加或修改广告的方法
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionAd promotionAd) {
        if (promotionAd.getId() == null) {
            Date date = new Date();
            promotionAd.setCreateTime(date);
            promotionAd.setUpdateTime(date);
            promotionAdService.savePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
            return result;
        } else {
            Date date = new Date();
            promotionAd.setUpdateTime(date);
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
            return result;
        }
    }

    // 根据id回显广告数据
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam int id) {
        try {
            PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionAd);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // id修改广告状态
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id, @RequestParam int status) {
        //执行修改操作
        if (status == 1) {
            promotionAdService.updatePromotionAdStatus(id, status);
        } else {
            promotionAdService.updatePromotionAdStatus(id, 0);
        }
        //保存修改后的状态,并返回
        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
}
