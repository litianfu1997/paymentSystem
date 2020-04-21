package com.nnxy.bank.service;

import com.nnxy.common.utils.R;
import com.nnxy.syslog.entity.SyslogEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-21 16:39
 */
@FeignClient(value = "syslog-service")
public interface SyslogService {

    @PostMapping("/syslog/syslog/save")
    R save(SyslogEntity syslogEntity);

}
