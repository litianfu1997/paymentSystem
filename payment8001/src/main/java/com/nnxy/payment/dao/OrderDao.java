package com.nnxy.payment.dao;

import com.nnxy.payment.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表
 * 
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-11 11:19:45
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
