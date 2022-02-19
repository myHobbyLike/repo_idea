package com.xuecheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuecheng.domain.PromotionAd;
import com.xuecheng.domain.PromotionAdVo;
import com.xuecheng.domain.PromotionSpace;
import com.xuecheng.mapper.PromotionAdMapper;
import com.xuecheng.mapper.PromotionSpaceMapper;
import com.xuecheng.service.PromotionAdService;
import com.xuecheng.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

// 广告的业务层接口的实现类
@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    // 注入广告的持久层对象
    @Autowired
    private PromotionAdMapper promotionAdMapper;

    // 自定义查询所有广告信息加分页方法
    @Override
    public PageInfo<PromotionAd> findAllAdByPage(PromotionAdVo promotionAdVo) {
        PageHelper.startPage(promotionAdVo.getCurrentPage(), promotionAdVo.getPageSize());
        List<PromotionAd> allPromotionAdByPage = promotionAdMapper.findAllAdByPage();
        PageInfo<PromotionAd> pageInfo = new PageInfo<PromotionAd>(allPromotionAdByPage);
        return pageInfo;
    }

    // 添加广告
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    // 修改广告
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    // 根据id回显广告数据
    @Override
    public PromotionAd findPromotionAdById(int id) {
        PromotionAd promotionAd = promotionAdMapper.findPromotionAdById(id);
        return promotionAd;
    }

    // id修改广告状态
    @Override
    public void updatePromotionAdStatus(int id, int status) {
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
