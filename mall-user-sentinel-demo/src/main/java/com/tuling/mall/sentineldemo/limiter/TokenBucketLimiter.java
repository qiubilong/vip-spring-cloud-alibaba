package com.tuling.mall.sentineldemo.limiter;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chenxuegui
 * @since 2025/5/21
 * 令牌桶
 */
@Slf4j
public class TokenBucketLimiter {  /* 令牌桶算法 */

    private int capacity;//最大上限
    private float tokenRate;//每ms token生成速率

    private AtomicLong tokens = new AtomicLong(0);//目前token数量

    private volatile long lastGenTime = System.currentTimeMillis();


    public TokenBucketLimiter(int limitCount, int limitSec) {
        this.capacity = limitCount + 2;//允许突发
        this.tokenRate = limitCount * 1.0f / limitSec/1000;
    }

    public  boolean tryPass(){
        long now = System.currentTimeMillis();

        //填充token
        int addTokens = (int) ((now - lastGenTime) * tokenRate);
        if(addTokens>0){
            tokens.set(Math.min(capacity,tokens.get() + addTokens));
            lastGenTime = now;
        }

        //使用token
        log.info("tokens={},addTokens={}",tokens,addTokens);
        if(tokens.get() < 1){
            return false;
        }

        tokens.decrementAndGet();
        return true;
    }

    public static void main(String[] args) throws Exception{

        TokenBucketLimiter limiter = new TokenBucketLimiter(3,1);
        Thread.sleep(1000);


        System.out.println(limiter.tryPass());
        System.out.println(limiter.tryPass());
        System.out.println(limiter.tryPass());
    }
}
