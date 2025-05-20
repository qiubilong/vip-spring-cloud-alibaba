package com.tuling.mall.sentineldemo.controller;

import com.tuling.mall.sentineldemo.limiter.FixCountLimiter;
import com.tuling.mall.sentineldemo.limiter.SlideWindowLimiter;
import com.tuling.mall.sentineldemo.limiter.SlideWindowScoreLimiter;
import com.tuling.mall.sentineldemo.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxuegui
 * @since 2025/5/20
 */
@RestController
public class MyLimiterController {


    FixCountLimiter limiter = new FixCountLimiter(30,1);
    @GetMapping("/fixCountLimiter")
    public String fixCountLimiter() throws IllegalAccessException {
        if(!limiter.tryPass()){
            throw new IllegalAccessException("=== 被限流 ===");
        }
        UserServiceImpl.doBiz();
        return "ok";
    }


    SlideWindowScoreLimiter windowLimiter = new SlideWindowScoreLimiter(20,1);
    @GetMapping("/slideWindowLimiter")
    public String slideWindowLimiter() throws IllegalAccessException {
        if(!windowLimiter.tryPass()){
            throw new IllegalAccessException("=== 被限流 ===");
        }
        UserServiceImpl.doBiz();
        return "ok";
    }



}
