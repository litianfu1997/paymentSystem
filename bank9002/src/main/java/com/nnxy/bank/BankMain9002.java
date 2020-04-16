package com.nnxy.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:28
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BankMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(BankMain9002.class,args);
    }
}
