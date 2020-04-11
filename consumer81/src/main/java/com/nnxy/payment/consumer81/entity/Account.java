package com.nnxy.payment.consumer81.entity;

import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:12
 */
@Data
public class Account {
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
    private Long aMoney;
}
