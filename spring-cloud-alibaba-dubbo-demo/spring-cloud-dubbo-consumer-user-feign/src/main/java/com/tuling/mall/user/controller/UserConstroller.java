package com.tuling.mall.user.controller;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import com.tuling.mall.entity.User;
import com.tuling.mall.service.UserService;
import com.tuling.mall.user.feign.UserDubboFeignService;
import com.tuling.mall.user.feign.UserFeignService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author Fox
 */
@RestController
@RequestMapping("/user")
public class UserConstroller {

    @DubboReference
    private UserService userService;

    @RequestMapping("/info/{id}")
    public User info(@PathVariable("id") Integer id){
        return userService.getById(id);
    }

    @Autowired
    private UserFeignService userFeignService;

    @RequestMapping("/list")
    public List<User> list(){
        return userFeignService.list();
    }

    @Autowired
    private UserDubboFeignService userDubboFeignService;

    @RequestMapping("/list2")
    public List<User> list2(){

        return userDubboFeignService.list();
    }

}
