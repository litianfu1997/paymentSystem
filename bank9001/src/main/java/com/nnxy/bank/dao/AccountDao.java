package com.nnxy.bank.dao;

import com.nnxy.bank.entity.AccountEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 银行账户表
 * 
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:26:03
 */
@Mapper
public interface AccountDao extends BaseMapper<AccountEntity> {
	
}
