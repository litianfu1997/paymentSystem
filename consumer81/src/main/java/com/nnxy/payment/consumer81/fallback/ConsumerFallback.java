package com.nnxy.payment.consumer81.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nnxy.common.utils.R;
import com.nnxy.payment.consumer81.entitys.AccountEntity;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2020-04-14 18:42
 */
public class ConsumerFallback {
    /**
     * 异常处理
     * @param e
     * @return
     */
    public static R consumerBlockHandlerMethod(BlockException e){
        return R.error(444,"目前服务器太拥挤了，请稍后再试");
    }

    /**
     * 降级处理
     * @param e
     * @return
     */
    public static R consumerFallBackMethod(Throwable e){
        return R.error(444,"服务器冒烟了，请稍后再试");
    }



}
