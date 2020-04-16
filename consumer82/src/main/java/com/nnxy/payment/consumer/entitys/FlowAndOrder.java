package com.nnxy.payment.consumer.entitys;

import java.util.Date;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-14 10:21
 */

public class FlowAndOrder {

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 金额
     */
    private Double fMoney;
    /**
     * 类型
     */
    private String fType;
    /**
     * 账户
     */
    private Long aId;
    /**
     * 流水创建时间
     */
    private Date fDate;
    /**
     * 订单唯一凭证
     */
    private String orderAccount;
    /**
     * 订单详细
     */
    private String descs;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }
}
