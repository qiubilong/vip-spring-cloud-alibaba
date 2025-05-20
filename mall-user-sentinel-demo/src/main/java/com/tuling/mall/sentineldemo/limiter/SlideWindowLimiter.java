package com.tuling.mall.sentineldemo.limiter;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author chenxuegui
 * @since 2025/5/20
 */
@Slf4j
public class SlideWindowLimiter {

    private static int windowMs = 100;//每100ms一个格子


    private int limitCount;//限流上限
    private int limitSec;//限流时间

    private long count;//当前请求总数
    private long lastReqMs = System.currentTimeMillis();

    private LinkedList<Long> windows;
    private int windowSize = 0;

    public SlideWindowLimiter(int limitCount, int limitSec) {
        this.limitCount = limitCount;
        this.limitSec = limitSec;
        this.windowSize = limitSec * 1000/windowMs;//每秒10个格子
        this.windows = new LinkedList<>();
    }

    public synchronized  boolean tryPass(){
        long now = System.currentTimeMillis();
        count++;

        if(now> lastReqMs + limitSec*1000){
            this.windows = new LinkedList<>();
            lastReqMs = now;
            return true;
        }


        if(now - lastReqMs >=windowMs){
            windows.add(count);
            lastReqMs = now;
        }

        while (this.windows.size()>windowSize){
            this.windows.poll();
        }

        if(windows.isEmpty()){
            return true;
        }

        long qps = windows.getLast() - windows.getFirst();
        log.info("tryPass count={},windows={},last={},first={},qps={},limitCount={}",count,windows,windows.getLast(),windows.getFirst(),qps,limitCount);
        return qps <= limitCount;

    }

    public static void main(String[] args) {
        SlideWindowLimiter  windowLimiter = new SlideWindowLimiter(30,1);

        windowLimiter.tryPass();
        windowLimiter.tryPass();
        windowLimiter.tryPass();
    }
}
