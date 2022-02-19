package com.xuecheng.service.impl;

import com.xuecheng.domain.PromotionSpace;
import com.xuecheng.mapper.PromotionSpaceMapper;
import com.xuecheng.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

// 广告位的业务层接口的实现类
@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {
    // 注入广告位的持久层对象
    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    // 自定义查询所有广告位信息方法
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> promotionSpaces = promotionSpaceMapper.findAllPromotionSpace();
        return promotionSpaces;
    }

    // 新增广告位
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        // 补全信息
        UUID uuid = UUID.randomUUID();
        promotionSpace.setSpaceKey(uuid.toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    // 修改广告位
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        // 补全信息
        Date date = new Date();
        promotionSpace.setUpdateTime(date);
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }

    // 根据id查询广告位信息
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpace;
    }
}
