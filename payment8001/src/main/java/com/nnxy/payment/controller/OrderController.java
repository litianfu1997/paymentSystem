package com.nnxy.payment.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/selectOrdersByAId")
    public List<OrderEntity> selectOrdersByAId(@RequestBody OrderEntity orderEntity){
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        List<OrderEntity> orderEntities = orderService.list(wrapper.eq("a_id", orderEntity.getaId()));
        return orderEntities;
    }

    /**
     * 新增订单
     * @param orderEntity
     * @return
     */
    @PostMapping("/insert")
    public R insert(@RequestBody OrderEntity orderEntity){
        boolean b = orderService.save(orderEntity);
        if(b == false){
            return R.error(444,"插入数据失败");
        }
        return R.ok("数据插入成功");
    }

    /**
     * 删除订单
     * @param orderEntity
     * @return
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody OrderEntity orderEntity){
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        boolean b = orderService.remove(queryWrapper.eq("order_id", orderEntity.getOrderId()));
        if (b == false){
            return R.error(444,"删除失败");
        }
        return R.ok("删除成功");
    }

    /**
     * 通过订单id获取订单
     * @param orderEntity
     * @return
     */
    @RequestMapping("/getById")
    public R getById(@RequestBody OrderEntity orderEntity){
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        OrderEntity one = orderService.getOne(queryWrapper.eq("order_id", orderEntity.getOrderId()));
        if (one == null){
            return R.error(444,"该订单不存在");
        }
        return R.ok("查询成功").put("order",one);
    }

    /**
     * 通过账户查询所有订单信息
     * @param orderEntity
     * @return
     */
    @RequestMapping("/getOrderList")
    public R getOrderList(@RequestBody OrderEntity orderEntity){
        QueryWrapper<OrderEntity> queryWrapper = new QueryWrapper<>();
        List<OrderEntity> list = orderService.list(queryWrapper.eq("a_id", orderEntity.getaId()));
        return R.ok("查询成功").put("orders",list);
    }



    /**
     * 更新订单
     * @param orderEntity
     * @return
     */
    @PostMapping("/updateOrder")
    public R updateOrder(@RequestBody OrderEntity orderEntity){
        UpdateWrapper<OrderEntity> updateWrapper = new UpdateWrapper<>();
        boolean b = orderService.update(orderEntity, updateWrapper.eq("order_account", orderEntity.getOrderAccount()));
        if (b == false){
            return R.error(444,"更新数据失败");
        }
        return R.ok("数据更新成功");
    }




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
