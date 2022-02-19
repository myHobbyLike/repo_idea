package com.xuecheng.service;

import com.xuecheng.domain.PromotionSpace;

import java.util.List;

// 广告位的业务层接口
public interface PromotionSpaceService {
    // 自定义查询所有广告位信息方法
    List<PromotionSpace> findAllPromotionSpace();

    // 新增广告位
    void savePromotionSpace(PromotionSpace promotionSpace);

    // 修改广告位
    void updatePromotionSpace(PromotionSpace promotionSpace);
    // 根据id查询广告位信息
    PromotionSpace findPromotionSpaceById(int id);
}
