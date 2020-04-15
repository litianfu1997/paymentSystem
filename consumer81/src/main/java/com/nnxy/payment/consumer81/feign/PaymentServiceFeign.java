package com.nnxy.payment.consumer81.feign;

import com.nnxy.common.utils.R;
import com.nnxy.payment.consumer81.entitys.OrderEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-12 14:36
 */
@FeignClient("payment-service")
public interface PaymentServiceFeign {

    /**
     * 新增订单
     * @param orderEntity
     * @return
     */
    @PostMapping("/payment/order/insert")
    public R insert(@RequestBody OrderEntity orderEntity);

    /**
     * 删除订单
     * @param orderEntity
     * @return
     */
    @DeleteMapping("/payment/order/deleteOrder")
    public R deleteOrder(@RequestBody OrderEntity orderEntity);

    /**
     * 通过订单id获取订单
     * @param orderEntity
     * @return
     */
    @RequestMapping("/payment/order/getById")
    public R getById(@RequestBody OrderEntity orderEntity);


    /**
     * 更新订单
     * @param orderEntity
     * @return
     */
    @PostMapping("/payment/order/updateOrder")
    public R updateOrder(@RequestBody OrderEntity orderEntity);

    /**
     * 通过账户查询所有订单信息
     * @param orderEntity
     * @return
     */
    @RequestMapping("/payment/order/getOrderList")
    public R getOrderList(@RequestBody OrderEntity orderEntity);

    /**
     * 通过aid查询所有订单
     * @param orderEntity
     * @return
     */
    @PostMapping("/payment/order/selectOrdersByAId")
    public List<OrderEntity> selectOrdersByAId(@RequestBody OrderEntity orderEntity);
}
