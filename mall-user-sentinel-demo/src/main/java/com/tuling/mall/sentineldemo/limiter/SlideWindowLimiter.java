package com.tuling.mall.sentineldemo.limiter;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author chenxuegui
 * @since 2025/5/20
 */
@Slf4j
public class SlideWindowLimiter {

    private int limitCount;//限流上限
    private int limitSec;//限流时间
    private int limitMs;//限流时间

    private long count;//当前请求总数
    private long lastReqMs = System.currentTimeMillis();

    private LinkedList<WindowItem> windows;
    private long windowMs = 0;

    public SlideWindowLimiter(int limitCount, int limitSec) {
        this.limitCount = limitCount;
        this.limitSec = limitSec;
        this.limitMs = limitSec * 1000;
        this.windowMs = limitSec * 1000/100;//每100ms一个窗口
        this.windows = new LinkedList<>();
    }

    public synchronized  boolean tryPass(){
        long now = System.currentTimeMillis();

        while (!this.windows.isEmpty() && (now - windows.peek().getStartTime()) > limitMs){
            this.windows.poll();
        }

        if(windows.isEmpty()){
            count++;
            windows.add(new WindowItem(count));
            return true;
        }
        long qps = windows.getLast().getCount() - windows.getFirst().getCount();
        log.info("tryPass count={},windows={},first={},last={},qps={},limitCount={}",count,windows,windows.getFirst(),windows.getLast(),qps,limitCount);
        boolean pass = qps <= limitCount;

        if(pass){
            count++;
            if((now - windows.getLast().getStartTime() > windowMs)){
                windows.add(new WindowItem(count));
            }
        }

        return pass;

    }

    @Getter
    @ToString
    private class WindowItem{
        private long startTime = System.currentTimeMillis();
        private long count;

        public WindowItem(long count) {
            this.count = count;
        }


    }

    public static void main(String[] args) {
        SlideWindowLimiter  windowLimiter = new SlideWindowLimiter(2,1);

        windowLimiter.tryPass();
        windowLimiter.tryPass();
        windowLimiter.tryPass();
    }
}
