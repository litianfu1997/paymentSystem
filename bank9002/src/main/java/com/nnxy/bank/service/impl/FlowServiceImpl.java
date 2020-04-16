package com.nnxy.bank.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nnxy.common.utils.PageUtils;
import com.nnxy.common.utils.Query;

import com.nnxy.bank.dao.FlowDao;
import com.nnxy.bank.entity.FlowEntity;
import com.nnxy.bank.service.FlowService;


@Service("flowService")
public class FlowServiceImpl extends ServiceImpl<FlowDao, FlowEntity> implements FlowService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FlowEntity> page = this.page(
                new Query<FlowEntity>().getPage(params),
                new QueryWrapper<FlowEntity>()
        );

        return new PageUtils(page);
    }

}