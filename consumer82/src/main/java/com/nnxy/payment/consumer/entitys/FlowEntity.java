package com.nnxy.payment.consumer.entitys;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 银行流水表
 * 
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:26:03
 */
@Data
public class FlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 流水id
	 */

	private Long fId;
	/**
	 * 金额
	 */
	private Double fMoney;
	/**
	 * 类型
	 */
	private String fType;
	/**
	 * 账户外键
	 */
	private Long aId;
	/**
	 * 流水创建时间
	 */
	private Date fDate;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getfId() {
		return fId;
	}

	public void setfId(Long fId) {
		this.fId = fId;
	}

	public Double getfMoney() {
		return fMoney;
	}

	public void setfMoney(Double fMoney) {
		this.fMoney = fMoney;
	}

	public String getfType() {
		return fType;
	}

	public void setfType(String fType) {
		this.fType = fType;
	}

	public Long getaId() {
		return aId;
	}

	public void setaId(Long aId) {
		this.aId = aId;
	}

	public Date getfDate() {
		return fDate;
	}

	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}
}
