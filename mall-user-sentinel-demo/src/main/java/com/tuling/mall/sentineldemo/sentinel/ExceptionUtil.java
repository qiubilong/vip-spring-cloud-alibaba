package com.tuling.mall.sentineldemo.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tuling.common.utils.R;

/**
 * @author Fox
 */
public class ExceptionUtil {

    /**
     * 注意： 必须为 static 函数
     */
    public static R fallback(Integer id, Throwable e){
        return R.error(-1,"===被异常降级啦===");
    }

    public static R handleException(Integer id, BlockException e){
        return R.error(-2,"===被限流啦===");
    }
}