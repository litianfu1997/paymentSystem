package com.nnxy.bank.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nnxy.bank.entity.FlowEntity;
import com.nnxy.bank.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 新增流水
     * @param flowEntity
     * @return
     */
    @PostMapping("/insert")
    public R insert(@RequestBody FlowEntity flowEntity){
        boolean b = flowService.save(flowEntity);
        if(b == false){
            return R.error(444,"插入数据失败");
        }
        return R.ok("数据插入成功");
    }

    /**
     * 删除流水
     * @param flowEntity
     * @return
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody FlowEntity flowEntity){
        QueryWrapper<FlowEntity> queryWrapper = new QueryWrapper<>();
        boolean b = flowService.remove(queryWrapper.eq("f_id", flowEntity.getfId()));
        if (b == false){
            return R.error(444,"删除失败");
        }
        return R.ok("删除成功");
    }

    /**
     * 通过id获取流水
     * @param flowEntity
     * @return
     */
    @RequestMapping("/getById")
    public R getById(@RequestBody FlowEntity flowEntity){
        QueryWrapper<FlowEntity> queryWrapper = new QueryWrapper<>();
        FlowEntity one = flowService.getOne(queryWrapper.eq("f_id", flowEntity.getfId()));
        if (one == null){
            return R.error(444,"该订单不存在");
        }
        return R.ok("查询成功").put("flow",one);
    }

    /**
     * 通过账户查询所有流水信息
     * @param flowEntity
     * @return
     */
    @RequestMapping("/getFlowList")
    public R getFlowList(@RequestBody FlowEntity flowEntity){
        QueryWrapper<FlowEntity> queryWrapper = new QueryWrapper<>();
        List<FlowEntity> list = flowService.list(queryWrapper.eq("a_id", flowEntity.getaId()));
        return R.ok("查询成功").put("orders",list);
    }

    @RequestMapping("/updateFlow")
    public R updateFlow(@RequestBody FlowEntity flowEntity){
        boolean b = flowService.update(flowEntity, new UpdateWrapper<FlowEntity>()
                .eq("f_id", flowEntity.getfId()));

        if (b==false){
            return R.error(444,"更新失败");
        }
        return R.ok("更新成功");
    }






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
