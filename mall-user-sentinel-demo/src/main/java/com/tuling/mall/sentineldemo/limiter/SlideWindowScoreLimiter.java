package com.tuling.mall.sentineldemo.limiter;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author chenxuegui
 * @since 2025/5/20
 * 滑动窗口
 */
@Slf4j
public class SlideWindowScoreLimiter {


    private int limitCount;//限流上限
    private int limitSec;//限流时间
    private int limitMs;//限流时间

    private LinkedList<Long> reqTimes;

    public SlideWindowScoreLimiter(int limitCount, int limitSec) {
        this.limitCount = limitCount;
        this.limitSec = limitSec;
        this.limitMs = limitSec * 1000;
        this.reqTimes = new LinkedList<>();
    }

    public synchronized  boolean tryPass(){
        long now = System.currentTimeMillis();

        //清除过期数据，滑动窗口
        while (!reqTimes.isEmpty() && (now - reqTimes.peek() > limitMs )){
            reqTimes.poll();
        }
        log.info("tryPass reqs.size()={},reqs={},", reqTimes.size(), reqTimes);

        //判断当前请求数
        if(reqTimes.size() >limitCount){
            return false;
        }

        reqTimes.offer(now);
        return true;

    }

    public static void main(String[] args) {
        SlideWindowScoreLimiter windowLimiter = new SlideWindowScoreLimiter(30,1);

        windowLimiter.tryPass();
        windowLimiter.tryPass();
        windowLimiter.tryPass();
    }
}
