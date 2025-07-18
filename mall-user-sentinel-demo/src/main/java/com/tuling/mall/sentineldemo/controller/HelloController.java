package com.tuling.mall.sentineldemo.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class HelloController {

    private static final String RESOURCE_NAME = "HelloWorld";

    @RequestMapping(value = "/hello")
    public String hello() {
        try (Entry entry = SphU.entry(RESOURCE_NAME)) { /* 资源 */
            // 被保护的逻辑
            log.info("hello world");
            return "hello world";
        } catch (BlockException ex) {/* 流控异常 */
            // 处理被流控的逻辑
            log.info("blocked!");
            return "被流控了";
        }

    }

    /**
     * 定义流控规则
     */
    @PostConstruct
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //设置受保护的资源
        rule.setResource(RESOURCE_NAME);
        // 设置流控规则 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护的资源阈值
        rule.setCount(1);/* qps=1 */
        rules.add(rule);
        // 加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

    @SentinelResource(value = RESOURCE_NAME,
            blockHandler = "handleException", /* 流控异常 */
        fallback = "fallbackException") /* 其他异常  */
    @RequestMapping("/hello2")
    public String hello2() {

        int i = 1 / 0;

        return "helloworld";
    }

    public String handleException(BlockException ex){
        return "被流控了";
    }

    public String fallbackException(Throwable t){
        return "被异常降级了";
    }

}