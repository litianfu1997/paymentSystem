package com.nnxy.payment.consumer81.controller;

import com.nnxy.common.utils.R;
import com.nnxy.payment.consumer81.entitys.AccountEntity;
import com.nnxy.payment.consumer81.feign.BankServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-11 15:02
 */
@RestController("/consumer")
public class ConsumerController {

    @Autowired
    private BankServiceFeign bankServiceFeign;

    @GetMapping("/test")
    public String test(){
        System.out.println("我是consumer，我准备调用bank9001服务");
        return bankServiceFeign.test();
    }

    @PostMapping("/login")
    public R login(AccountEntity accountEntity){
        return bankServiceFeign.login(accountEntity);
    }

    @PostMapping("/register")
    public R register(AccountEntity accountEntity){
        return bankServiceFeign.register(accountEntity);
    }
}
