package com.nnxy.payment.consumer81.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nnxy.common.utils.R;
import com.nnxy.payment.consumer81.entitys.AccountEntity;
import com.nnxy.payment.consumer81.entitys.FlowEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-11 14:49
 */
@FeignClient(value = "bank-service")
public interface BankServiceFeign {

    @GetMapping("/bank/account/test")
    public String test();

    /**
     * 登录业务
     * @param accountEntity
     * @return
     */
    @PostMapping("/bank/account/login")
    public R login(@RequestBody AccountEntity accountEntity);

    /**
     * 注册业务
     * @param accountEntity
     * @return
     */
    @PostMapping("/bank/account/register")
    public R register(@RequestBody AccountEntity accountEntity);

    /**
     * 充值
     * @param accountEntity
     * @return
     */
    @PostMapping("/bank/account/recharge")
    public R recharge(@RequestBody AccountEntity accountEntity);

    /**
     * 查询余额
     * @param accountEntity
     * @return
     */
    @PostMapping("/bank/account/checkingBalance")
    public R checkingBalance(@RequestBody AccountEntity accountEntity);

    /**
     * 修改
     */
    @RequestMapping("/bank/account/update")
    public R update(@RequestBody AccountEntity account);

    /**
     * 新增流水
     * @param flowEntity
     * @return
     */
    @PostMapping("/bank/flow/insert")
    public R insert(@RequestBody FlowEntity flowEntity);

    /**
     * 删除流水
     * @param flowEntity
     * @return
     */
    @DeleteMapping("/bank/flow/delete")
    public R delete(@RequestBody FlowEntity flowEntity);

    /**
     * 通过订单id获取订单
     * @param flowEntity
     * @return
     */
    @RequestMapping("/bank/flow/getById")
    public R getById(@RequestBody FlowEntity flowEntity);

    /**
     * 通过账户查询所有流水信息
     * @param flowEntity
     * @return
     */
    @RequestMapping("/bank/flow/getFlowList")
    public R getFlowList(@RequestBody FlowEntity flowEntity);

    /**
     * 更新流水
     * @param flowEntity
     * @return
     */
    @RequestMapping("/bank/flow/updateFlow")
    public R updateFlow(@RequestBody FlowEntity flowEntity);



}
