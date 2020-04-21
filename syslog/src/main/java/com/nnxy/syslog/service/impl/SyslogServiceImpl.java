package com.nnxy.syslog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nnxy.common.utils.PageUtils;
import com.nnxy.common.utils.Query;

import com.nnxy.syslog.dao.SyslogDao;
import com.nnxy.syslog.entity.SyslogEntity;
import com.nnxy.syslog.service.SyslogService;


@Service("syslogService")
public class SyslogServiceImpl extends ServiceImpl<SyslogDao, SyslogEntity> implements SyslogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SyslogEntity> page = this.page(
                new Query<SyslogEntity>().getPage(params),
                new QueryWrapper<SyslogEntity>()
        );

        return new PageUtils(page);
    }

}