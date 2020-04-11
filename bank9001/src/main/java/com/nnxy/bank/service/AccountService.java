package com.nnxy.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nnxy.common.utils.PageUtils;
import com.nnxy.bank.entity.AccountEntity;

import java.util.Map;

/**
 * 银行账户表
 *
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:26:03
 */
public interface AccountService extends IService<AccountEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

