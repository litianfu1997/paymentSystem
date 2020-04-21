package com.nnxy.syslog.dao;

import com.nnxy.syslog.entity.SyslogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付系统日志文件
 * 
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-21 11:54:28
 */
@Mapper
public interface SyslogDao extends BaseMapper<SyslogEntity> {
	
}
