package com.nnxy.payment.consumer.entitys;

import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-12 16:23
 */
@Data
public class AllEntity {
    private AccountEntity accountEntity;
    private FlowEntity flowEntity;
    private OrderEntity orderEntity;
    private String target;
}
