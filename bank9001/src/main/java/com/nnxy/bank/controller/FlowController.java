package com.nnxy.bank.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.nnxy.bank.entity.FlowEntity;
import com.nnxy.bank.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnxy.common.utils.PageUtils;
import com.nnxy.common.utils.R;



/**
 * 银行流水表
 *
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:26:03
 */
@RestController
@RequestMapping("bank/flow")
public class FlowController {
    @Autowired
    private FlowService flowService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("bank:flow:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = flowService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fId}")
    //@RequiresPermissions("bank:flow:info")
    public R info(@PathVariable("fId") Long fId){
		FlowEntity flow = flowService.getById(fId);

        return R.ok().put("flow", flow);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("bank:flow:save")
    public R save(@RequestBody FlowEntity flow){
		flowService.save(flow);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("bank:flow:update")
    public R update(@RequestBody FlowEntity flow){
		flowService.updateById(flow);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("bank:flow:delete")
    public R delete(@RequestBody Long[] fIds){
		flowService.removeByIds(Arrays.asList(fIds));

        return R.ok();
    }

}
