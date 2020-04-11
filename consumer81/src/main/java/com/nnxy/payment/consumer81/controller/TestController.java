package com.nnxy.payment.consumer81.controller;

import com.nnxy.payment.consumer81.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:11
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public @ResponseBody Account test(@RequestBody Account a){

        return a;
    }
}
