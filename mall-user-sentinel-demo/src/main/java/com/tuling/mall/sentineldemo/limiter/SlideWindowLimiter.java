package com.tuling.mall.sentineldemo.limiter;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author chenxuegui
 * @since 2025/5/20
 * 滑动窗口
 */
@Slf4j
public class SlideWindowLimiter {

    private int limitCount;//限流上限
    private int limitMs;//限流时间

    private long count;

    private LinkedList<WindowItem> windows;
    private long windowPeriodMs = 0;

    public SlideWindowLimiter(int limitCount, int limitSec) {
        this.limitCount = limitCount;
        this.limitMs = limitSec * 1000;
        this.windowPeriodMs = limitSec * 1000/100;//每100ms一个窗口
        this.windows = new LinkedList<>();
    }

    public synchronized  boolean tryPass(){
        long now = System.currentTimeMillis();

        //清除过期数据，滑动窗口
        while (!this.windows.isEmpty() && (now - windows.peek().getStartTime()) > limitMs){
            this.windows.poll();
        }

        if(windows.isEmpty()){
            count++;
            windows.add(new WindowItem(count));
            return true;
        }

        //累计当前总请求
        long qps = windows.getLast().getCount() - windows.getFirst().getCount();
        log.info("tryPass count={},windows={},first={},last={},qps={},limitCount={}",count,windows,windows.getFirst(),windows.getLast(),qps,limitCount);
        boolean pass = qps <= limitCount;

        if(pass){
            count++;//当前请求总数

            //达到间隔，新增窗口
            if((now - windows.getLast().getStartTime() > windowPeriodMs)){
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
