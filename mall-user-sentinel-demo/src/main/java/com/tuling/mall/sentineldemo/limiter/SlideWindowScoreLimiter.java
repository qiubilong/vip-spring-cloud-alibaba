package com.tuling.mall.sentineldemo.limiter;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author chenxuegui
 * @since 2025/5/20
 * 滑动窗口
 */
@Slf4j
public class SlideWindowScoreLimiter {  /* 类似sortedset */
    private int limitCount;//限流上限
    private int limitMs;//限流时间

    private LinkedList<Long> reqList;

    public SlideWindowScoreLimiter(int limitCount, int limitSec) {
        this.limitCount = limitCount;
        this.limitMs = limitSec * 1000;
        this.reqList = new LinkedList<>();
    }

    public synchronized  boolean tryPass(){
        long now = System.currentTimeMillis();

        //清除过期数据，滑动窗口
        while (!reqList.isEmpty() && (now - reqList.peek() > limitMs )){
            reqList.poll();
        }
        log.info("tryPass reqs.size()={},reqs={},", reqList.size(), reqList);

        //判断当前请求数
        if(reqList.size() >limitCount){
            return false;
        }

        reqList.offer(now);
        return true;

    }

    public static void main(String[] args) {
        SlideWindowScoreLimiter windowLimiter = new SlideWindowScoreLimiter(30,1);

        windowLimiter.tryPass();
        windowLimiter.tryPass();
        windowLimiter.tryPass();
    }
}
