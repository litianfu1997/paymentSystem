package com.nnxy.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nnxy.common.utils.PageUtils;
import com.nnxy.bank.entity.FlowEntity;

import java.util.Map;

/**
 * 银行流水表
 *
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:26:03
 */
public interface FlowService extends IService<FlowEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

