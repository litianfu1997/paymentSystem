package com.nnxy.bank.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nnxy.bank.entity.AccountEntity;
import com.nnxy.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nnxy.common.utils.PageUtils;
import com.nnxy.common.utils.R;


/**
 * 银行账户表
 *
 * @author litianfu
 * @email 1035869369@qq.com
 * @date 2020-04-10 17:26:03
 */
@RestController
@RequestMapping("bank/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 登录业务
     * @param accountEntity
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody AccountEntity accountEntity) {

        if (accountEntity.getaAccount() == null || accountEntity.getaPassword() == null) {
            return R.error(444, "请勿传空参");
        }
        QueryWrapper<AccountEntity> wrapper = new QueryWrapper<>();
        AccountEntity one = accountService.getOne(wrapper.eq("a_account", accountEntity.getaAccount()));
        if (one == null) {
            return R.error(444, "用户不存在");
        }
        if (!accountEntity.getaPassword().equals(one.getaPassword())) {
            return R.error(444, "密码错误");
        }
        return R.ok("登录成功");
    }

    /**
     * 注册业务
     * @param accountEntity
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody AccountEntity accountEntity){
        if (accountEntity.getaAccount() == null || accountEntity.getaPassword() == null) {
            return R.error(403, "请勿传空参");
        }
        boolean b = accountService.save(accountEntity);
        if (b == false){
            return R.error(444,"用户注册失败");
        }
        return R.ok("用户注册成功");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("bank:account:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = accountService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/test")
    public String test() {
        return "我来自bank9001服务";
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{aId}")
    //@RequiresPermissions("bank:account:info")
    public R info(@PathVariable("aId") Long aId) {
        AccountEntity account = accountService.getById(aId);

        return R.ok().put("account", account);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("bank:account:save")
    public R save(@RequestBody AccountEntity account) {

        accountService.save(account);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("bank:account:update")
    public R update(@RequestBody AccountEntity account) {
        accountService.updateById(account);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("bank:account:delete")
    public R delete(@RequestBody Long[] aIds) {
        accountService.removeByIds(Arrays.asList(aIds));

        return R.ok();
    }

}
