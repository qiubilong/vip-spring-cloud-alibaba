package com.tuling.mall.sentineldemo.feign;

import com.tuling.common.utils.R;
import com.tuling.mall.sentineldemo.feign.OrderFeignService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FallbackOrderFeignServiceFactory implements FallbackFactory<OrderFeignService> {
    @Override
    public OrderFeignService create(Throwable throwable) {

        return new OrderFeignService() {
            @Override
            public R findOrderByUserId(Integer userId) {
                return R.error(-1,"=======服务降级了========");
            }
        };
    }
}