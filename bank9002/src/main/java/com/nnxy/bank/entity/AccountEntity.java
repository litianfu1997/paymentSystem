package com.nnxy.bank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
 * 银行账户表
 * 
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:26:03
 */

@TableName("bank_account")
public class AccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账户主键
	 */
	@TableId(type = IdType.AUTO)
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

	private String aName;

	private Integer aAge;

	private String aSex;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public Integer getaAge() {
		return aAge;
	}

	public void setaAge(Integer aAge) {
		this.aAge = aAge;
	}

	public String getaSex() {
		return aSex;
	}

	public void setaSex(String aSex) {
		this.aSex = aSex;
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
