package com.nnxy.payment.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnxy.payment.entity.OrderEntity;
import com.nnxy.payment.service.OrderService;
import com.nnxy.common.utils.PageUtils;
import com.nnxy.common.utils.R;



/**
 * 订单表
 *
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-11 11:19:45
 */
@RestController
@RequestMapping("payment/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("payment:order:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    //@RequiresPermissions("payment:order:info")
    public R info(@PathVariable("orderId") Long orderId){
		OrderEntity order = orderService.getById(orderId);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("payment:order:save")
    public R save(@RequestBody OrderEntity order){
		orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("payment:order:update")
    public R update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("payment:order:delete")
    public R delete(@RequestBody Long[] orderIds){
		orderService.removeByIds(Arrays.asList(orderIds));

        return R.ok();
    }

}
