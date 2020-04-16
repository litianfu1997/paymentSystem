package com.nnxy.payment.consumer.entitys;


import lombok.Data;

import java.io.Serializable;

/**
 * 银行账户表
 * 
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:26:03
 */

@Data
public class AccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账户主键
	 */
	private Long aId;
	/**
	 * 账户号
	 */
	private String aAccount;
	/**
	 * 密码
	 */
	private String aPassword;
	/**
	 * 账户余额
	 */
	private Double aMoney;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getaId() {
		return aId;
	}

	public String getaAccount() {
		return aAccount;
	}

	public String getaPassword() {
		return aPassword;
	}

	public Double getaMoney() {
		return aMoney;
	}

	public void setaId(Long aId) {
		this.aId = aId;
	}

	public void setaAccount(String aAccount) {
		this.aAccount = aAccount;
	}

	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}

	public void setaMoney(Double aMoney) {
		this.aMoney = aMoney;
	}
}
