package com.tuling.mall.sentineldemo.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tuling.mall.sentineldemo.entity.UserEntity;
import com.tuling.mall.sentineldemo.feign.OrderFeignService;
import com.tuling.mall.sentineldemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuling.common.utils.R;


/**
 * 
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderFeignService orderFeignService;

    @RequestMapping(value = "/findOrderByUserId/{id}")
//    @SentinelResource(value = "findOrderByUserId",
//            blockHandler = "handleException")
    public R  findOrderByUserId(@PathVariable("id") Integer id) {

//        try {
//            // 模拟测试并发线程数限流
//            Thread.sleep(900);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        
        //feign调用
        R result = orderFeignService.findOrderByUserId(id);

        return result;
    }

    public R handleException(@PathVariable("id") Integer id,BlockException exception){
        return R.error(-1,"===被限流降级啦===");
    }

    public R fallback(@PathVariable("id") Integer id,Throwable e){
        return R.error(-1,"===被熔断降级啦==="+e.getMessage());
    }


    @RequestMapping("/info/{id}")
    @SentinelResource(value = "userinfo", blockHandler = "handleException")
    public R info(@PathVariable("id") Integer id){
        UserEntity user = userService.getById(id);

        if(id==4){
            throw new IllegalArgumentException("异常参数");
        }

        return R.ok().put("user", user);
    }





}
