package com.nnxy.payment.consumer81.service;

import com.nnxy.common.utils.R;
import com.nnxy.payment.consumer81.entitys.AccountEntity;
import com.nnxy.payment.consumer81.entitys.FlowEntity;
import com.nnxy.payment.consumer81.entitys.OrderEntity;
import com.nnxy.payment.consumer81.feign.BankServiceFeign;
import com.nnxy.payment.consumer81.feign.PaymentServiceFeign;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-12 15:12
 */
@Service
public class ConsumerService {

    @Autowired
    private BankServiceFeign bankServiceFeign;

    @Autowired
    private PaymentServiceFeign paymentServiceFeign;


    /**
     * 支付业务
     *
     * @param accountEntity
     * @param flowEntity
     * @param orderEntity
     * @return
     */
//    @GlobalTransactional
    @Transactional
    public R payment(AccountEntity accountEntity, FlowEntity flowEntity, OrderEntity orderEntity) {
        //生成订单
        String uuid = UUID.randomUUID().toString();
        orderEntity.setOrderAccount(uuid);
        paymentServiceFeign.insert(orderEntity);
        //1.查询账户余额
        R balance = bankServiceFeign.checkingBalance(accountEntity);
        Double money = (Double) balance.get("money");
        //2.比对所支付数额与余额的大小
        if (money - flowEntity.getfMoney() <= 0) {
            paymentServiceFeign.delete(orderEntity);
            return R.error(444, "余额不足，请充值");
        }
        //3.插入流水
        long millis = System.currentTimeMillis();
        flowEntity.setaId(millis);
        R r1 = bankServiceFeign.insert(flowEntity);
        //5.扣减余额
        accountEntity.setaMoney(money - flowEntity.getfMoney());
        R r2 = bankServiceFeign.update(accountEntity);
        //6.更新订单
        orderEntity.setFlowId(millis);
        R r3 = paymentServiceFeign.updateOrder(orderEntity);
        Integer code1 = (Integer) r1.get("code");
        Integer code2 = (Integer) r2.get("code");
        Integer code3 = (Integer) r3.get("code");

        if (code1 == 444 || code2 == 444 || code3 == 444) {
            return R.error(444, "支付失败");
        }
        return R.ok("支付成功");
    }

    /**
     * 转账业务
     *
     * @param accountEntity
     * @param target        对方账户
     * @param flowEntity
     * @param orderEntity
     * @return
     */
//    @GlobalTransactional
    @Transactional
    public R transfer(AccountEntity accountEntity, String target, FlowEntity flowEntity, OrderEntity orderEntity) {
        //生成两个订单

        //自己的订单
        String uuid1 = UUID.randomUUID().toString();
        orderEntity.setOrderAccount(uuid1);
        paymentServiceFeign.insert(orderEntity);
        //对方的订单
        OrderEntity targetOrderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderEntity, targetOrderEntity);
        String uuid2 = UUID.randomUUID().toString();
        targetOrderEntity.setOrderAccount(uuid2);
        paymentServiceFeign.insert(targetOrderEntity);
        //1.查询账户余额
        R balance = bankServiceFeign.checkingBalance(accountEntity);
        Double money = (Double) balance.get("money");
        //2.比对所支付数额与余额的大小
        if (money - flowEntity.getfMoney() <= 0) {
            paymentServiceFeign.delete(orderEntity);
            paymentServiceFeign.delete(targetOrderEntity);
            return R.error(444, "余额不足，请充值");
        }
        //3.插入流水
        //自己的流水
        long millis1 = System.currentTimeMillis();
        flowEntity.setaId(millis1);
        R r1 = bankServiceFeign.insert(flowEntity);
        //对方的流水
        FlowEntity targetFlowEntity = new FlowEntity();
        long millis2 = System.currentTimeMillis();
        targetFlowEntity.setaId(millis2);
        //查找对应账户
        AccountEntity a = new AccountEntity();
        a.setaAccount(target);
        AccountEntity newAccount = bankServiceFeign.getAccountById(a);
        BeanUtils.copyProperties(flowEntity,targetFlowEntity);
        targetFlowEntity.setaId(newAccount.getaId());
        targetFlowEntity.setfType("收入");
        R r5 = bankServiceFeign.insert(targetFlowEntity);
        //5.己方扣减余额，对方增加余额
        accountEntity.setaMoney(money - flowEntity.getfMoney());
        R r2 = bankServiceFeign.update(accountEntity);
        //对方增加余额
        newAccount.setaMoney(newAccount.getaMoney() + flowEntity.getfMoney());
        R r4 = bankServiceFeign.update(newAccount);

        //6.更新订单
        orderEntity.setFlowId(millis1);
        R r3 = paymentServiceFeign.updateOrder(orderEntity);
        targetOrderEntity.setFlowId(millis2);
        targetOrderEntity.setaId(newAccount.getaId());
        R r6 = paymentServiceFeign.updateOrder(targetOrderEntity);
        Integer code1 = (Integer) r1.get("code");
        Integer code2 = (Integer) r2.get("code");
        Integer code3 = (Integer) r3.get("code");
        Integer code4 = (Integer) r4.get("code");
        Integer code5 = (Integer) r5.get("code");
        Integer code6 = (Integer) r6.get("code");

        if (code1 == 444 || code2 == 444 || code3 == 444 || code4 == 444
                || code5 == 444 || code6 == 444) {
            return R.error(444, "支付失败");
        }
        return R.ok("支付成功");
    }
}
