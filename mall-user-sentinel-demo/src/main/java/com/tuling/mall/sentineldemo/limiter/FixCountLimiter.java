package com.tuling.mall.sentineldemo.limiter;

/**
 * @author chenxuegui
 * @since 2025/5/20
 */
public class FixCountLimiter {

    private int count;

    private long startTime = System.currentTimeMillis();

    private int limitCount;//限流上限
    private int limitSec;//限流时间

    public FixCountLimiter(int limitCount, int limitSec) {
        this.limitCount = limitCount;
        this.limitSec = limitSec;
    }

    public synchronized  boolean tryPass(){
        long now = System.currentTimeMillis();
        if(now < (startTime + limitSec*1000) ){
            count++;//当前请求数
            return count<= limitCount;
        }else {
            startTime = now;
            count = 1;
        }
        return true;
    }
}
