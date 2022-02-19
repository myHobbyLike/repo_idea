package com.xuecheng.controller;

import com.xuecheng.domain.PromotionSpace;
import com.xuecheng.domain.ResponseResult;
import com.xuecheng.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
// 广告位的表现层
public class PromotionSpaceController {
    // 注入广告位的业务层对象
    @Autowired
    private PromotionSpaceService promotionSpaceService;

    // 自定义查询所有广告位信息方法
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace() {
        List<PromotionSpace> promotionSpaces = promotionSpaceService.findAllPromotionSpace();
        return new ResponseResult(true, 200, "响应成功", promotionSpaces);
    }

    // 自定义添加或修改广告位的方法
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace) {
        if (promotionSpace.getId() == null) {
            //新增
            promotionSpaceService.savePromotionSpace(promotionSpace);
            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
            return responseResult;
        } else {
            // 修改
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
            return responseResult;
        }
    }

    // 根据id查询广告位信息
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id) {
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionSpace);
        return result;
    }
}
