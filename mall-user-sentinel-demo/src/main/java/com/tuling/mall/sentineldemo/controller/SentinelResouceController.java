package com.tuling.mall.sentineldemo.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.tuling.mall.sentineldemo.sentinel.ExceptionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxuegui
 * @since 2025/5/19
 */
@RestController
public class SentinelResouceController {

    @PostConstruct
    public void init(){
     /**   List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setCount(3);// set limit qps to 20
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);*/

    }


    /* 1、限流 - 硬编码 */
    @GetMapping("/entryByCode")
    public String entryByCode(){
        try (Entry entry = SphU.entry("HelloWorld")) {
            // Your business logic here.
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (BlockException e) {
            // Handle rejected request.
            return "BlockException";
        }
        return "ok";
    }

    /* 2、限流 - @SentinelResource注解 - Aop拦截 */
    @GetMapping("/entryBySentinelResource")
    @SentinelResource(value = "@SentinelResource",blockHandler = "blockException",blockHandlerClass = ExceptionUtil.class
                ,fallback = "fallback", fallbackClass = ExceptionUtil.class)
    public String entryBySentinelResource(){

        // Your business logic here.
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }


    /* 3、限流 - mvc自动 - mvc拦截器 */
    @GetMapping("/entryByHandlerInterceptor")
    public String entryByHandlerInterceptor(){

        // Your business logic here.
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
