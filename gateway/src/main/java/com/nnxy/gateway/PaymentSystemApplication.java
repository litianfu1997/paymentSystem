package com.nnxy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-10 19:04
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentSystemApplication.class, args);
    }

}


