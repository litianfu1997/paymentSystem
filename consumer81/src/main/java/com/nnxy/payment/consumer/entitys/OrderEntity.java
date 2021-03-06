package com.nnxy.payment.consumer.entitys;

import java.io.Serializable;

/**
 * 订单表
 * 
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-11 11:19:45
 */

public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 订单唯一凭证
	 */
	private String orderAccount;
	/**
	 * 流水号
	 */
	private Long flowId;
	/**
	 * 订单详细
	 */
	private String descs;
	/**
	 * 账户id
	 */
	private Long aId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderAccount() {
		return orderAccount;
	}

	public void setOrderAccount(String orderAccount) {
		this.orderAccount = orderAccount;
	}

	public Long getFlowId() {
		return flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

	public Long getaId() {
		return aId;
	}

	public void setaId(Long aId) {
		this.aId = aId;
	}
}
