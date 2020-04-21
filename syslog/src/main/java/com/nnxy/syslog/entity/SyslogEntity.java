package com.nnxy.syslog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 支付系统日志文件
 * 
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-21 11:54:28
 */
@Data
@TableName("syslog")
public class SyslogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志表主键
	 */
	@TableId(type = IdType.AUTO )
	private Long id;
	/**
	 * 操作用户
	 */
	private String username;
	/**
	 * 操作
	 */
	private String operation;
	/**
	 * 执行方法
	 */
	private String method;
	/**
	 * 参数
	 */
	private String prams;
	/**
	 * 执行时长
	 */
	private Long operationTime;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
