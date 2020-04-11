package com.nnxy.bank.dao;

import com.nnxy.bank.entity.FlowEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 银行流水表
 * 
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:26:03
 */
@Mapper
public interface FlowDao extends BaseMapper<FlowEntity> {
	
}
