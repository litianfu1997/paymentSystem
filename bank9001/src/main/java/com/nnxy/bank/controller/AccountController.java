package com.nnxy.bank.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nnxy.bank.entity.AccountEntity;
import com.nnxy.bank.entity.FlowEntity;
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
            return R.error(444, "请勿传空参");
        }
        boolean b = accountService.save(accountEntity);
        if (b == false){
            return R.error(444,"用户注册失败");
        }
        return R.ok("用户注册成功");
    }

    /**
     * 充值
     * @param accountEntity
     * @return
     */
    @PostMapping("/recharge")
    public R recharge(@RequestBody AccountEntity accountEntity){
        if (accountEntity.getaAccount() == null || accountEntity.getaPassword() == null) {
            return R.error(444, "请勿传空参");
        }
        //先从数据库查询出该账户的余额
        QueryWrapper<AccountEntity> queryWrapper = new QueryWrapper<>();
        AccountEntity account = accountService.getOne(queryWrapper.eq("a_account", accountEntity.getaAccount()));
        //充值 = 之前余额+现在充值数额
        accountEntity.setaMoney(accountEntity.getaMoney()+account.getaMoney());
        UpdateWrapper<AccountEntity> wrapper = new UpdateWrapper<>();
        boolean b = accountService.update(accountEntity,wrapper.eq("a_account", accountEntity.getaAccount()));
        if (b == false){
            return R.error(444,"充值失败，请重试！");
        }
        return R.ok("充值成功");
    }

    /**
     * 查询余额
     * @param accountEntity
     * @return
     */
    @PostMapping("/checkingBalance")
    public R checkingBalance(@RequestBody AccountEntity accountEntity){
        if (accountEntity.getaAccount() == null || accountEntity.getaPassword() == null) {
            return R.error(444, "请勿传空参");
        }
        QueryWrapper<AccountEntity> wrapper = new QueryWrapper<>();
        AccountEntity account = accountService.getOne(wrapper.eq("a_account", accountEntity.getaAccount()));
        if (account == null) {
            return R.error(444, "用户不存在");
        }
        return R.ok("余额查询成功").put("money",account.getaMoney());
    }

    /**
     * 通过账户号获取账户
     * @param accountEntity
     * @return
     */
    @RequestMapping("/getAccountById")
    public R getAccountById(@RequestBody AccountEntity accountEntity){
        QueryWrapper<AccountEntity> queryWrapper = new QueryWrapper<>();
        AccountEntity one = accountService.getOne(queryWrapper.eq("a_account", accountEntity.getaAccount()));
        if (one == null){
            return R.error(444,"该订单不存在");
        }
        return R.ok("查询成功").put("account",one);
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
