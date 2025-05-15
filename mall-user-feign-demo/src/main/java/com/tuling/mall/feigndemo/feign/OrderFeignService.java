package com.tuling.mall.feigndemo.feign;

import com.tuling.common.utils.R;
import com.tuling.mall.feigndemo.config.FeignConfig;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static org.springframework.http.MediaType.*;

/**
 * @author Fox
 */

//FeignConfig局部配置
//@FeignClient(value = "mall-order",path = "/order",configuration = FeignConfig.class)
@FeignClient(value = "mall-order",path = "/order")
public interface OrderFeignService {

    @RequestMapping("/findOrderByUserId/{userId}")
    R findOrderByUserId(@PathVariable("userId") Integer userId);

//    @RequestLine("GET /findOrderByUserId/{userId}")
//    R findOrderByUserId(@Param("userId") Integer userId);
}
