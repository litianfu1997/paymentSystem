package com.nnxy.payment.consumer81.feign;

import com.nnxy.common.utils.R;
import com.nnxy.payment.consumer81.entitys.AccountEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


}
