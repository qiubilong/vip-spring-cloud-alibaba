package com.tuling.mall.sentineldemo.feign;

import com.tuling.common.utils.R;
import org.springframework.stereotype.Component;

@Component   //必须交给spring 管理
public class FallbackOrderFeignService implements OrderFeignService {
    @Override
    public R findOrderByUserId(Integer userId) {
        return R.error(-1,"=======服务降级了========");
    }
}