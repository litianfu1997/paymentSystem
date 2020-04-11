package com.nnxy.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nnxy.common.utils.PageUtils;
import com.nnxy.payment.entity.OrderEntity;

import java.util.Map;

/**
 * 订单表
 *
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-11 11:19:45
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

