package com.nnxy.syslog.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.nnxy.syslog.annotation.Syslog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nnxy.syslog.entity.SyslogEntity;
import com.nnxy.syslog.service.SyslogService;
import com.nnxy.common.utils.PageUtils;
import com.nnxy.common.utils.R;



/**
 * 支付系统日志文件
 *
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-21 11:54:28
 */
@RestController
@RequestMapping("syslog/syslog")
public class SyslogController {
    @Autowired
    private SyslogService syslogService;


    @Syslog("test")
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("syslog:syslog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = syslogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("syslog:syslog:info")
    public R info(@PathVariable("id") Long id){
		SyslogEntity syslog = syslogService.getById(id);

        return R.ok().put("syslog", syslog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("syslog:syslog:save")
    public R save(@RequestBody SyslogEntity syslog){
		syslogService.save(syslog);

        return R.ok();
    }

    /**
     * 修改
     */
    @Syslog("修改日志")
    @RequestMapping("/update")
    //@RequiresPermissions("syslog:syslog:update")
    public R update(@RequestBody SyslogEntity syslog){
		syslogService.updateById(syslog);

        return R.ok();
    }

    /**
     * 删除
     */
    @Syslog("删除日志")
    @RequestMapping("/delete")
    //@RequiresPermissions("syslog:syslog:delete")
    public R delete(@RequestBody Long[] ids){
		syslogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
