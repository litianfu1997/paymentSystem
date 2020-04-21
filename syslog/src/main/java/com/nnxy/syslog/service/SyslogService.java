package com.nnxy.syslog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nnxy.common.utils.PageUtils;
import com.nnxy.syslog.entity.SyslogEntity;

import java.util.Map;

/**
 * 支付系统日志文件
 *
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-21 11:54:28
 */
public interface SyslogService extends IService<SyslogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

