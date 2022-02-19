package com.xuecheng.service;

import com.github.pagehelper.PageInfo;
import com.xuecheng.domain.PromotionAd;
import com.xuecheng.domain.PromotionAdVo;
import com.xuecheng.domain.PromotionSpace;

import java.util.List;

// 广告的业务层接口
public interface PromotionAdService {
    // 自定义查询所有广告信息加分页方法
    PageInfo<PromotionAd> findAllAdByPage(PromotionAdVo promotionAdVo);

    // 添加广告
    void savePromotionAd(PromotionAd promotionAd);

    // 修改广告
    void updatePromotionAd(PromotionAd promotionAd);
    // 根据id回显广告数据
    PromotionAd findPromotionAdById(int id);
    // id修改广告状态
    void updatePromotionAdStatus(int id, int status);
}
