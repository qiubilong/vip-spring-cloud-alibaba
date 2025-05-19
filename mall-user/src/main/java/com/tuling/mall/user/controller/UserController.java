package com.tuling.mall.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tuling.common.utils.R;
import org.springframework.web.client.RestTemplate;

/**
 * 
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/findOrderByUserId/{id}")
    public R  findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:"+id+"查询订单信息");
        // restTemplate调用,url写死
        //String url = "http://localhost:8020/order/findOrderByUserId/"+id;

        // ribbon实现，restTemplate需要添加@LoadBalanced注解
        // mall-order  ip:port
        String url = "http://mall-order/order/findOrderByUserId/"+id;

        R result = restTemplate.getForObject(url,R.class);
        return result;
    }



}
