package com.xuecheng.mapper;

import com.xuecheng.domain.PromotionAd;
import com.xuecheng.domain.PromotionSpace;

import java.util.List;

// 广告的持久层接口
public interface PromotionAdMapper {
    // 自定义查询所有广告信息加分页方法
    List<PromotionAd> findAllAdByPage();

    // 添加广告
    void savePromotionAd(PromotionAd promotionAd);

    // 修改广告
    void updatePromotionAd(PromotionAd promotionAd);
    // 根据id回显广告数据
    PromotionAd findPromotionAdById(int id);
    // id修改广告状态
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
