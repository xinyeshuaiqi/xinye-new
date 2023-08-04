package pers.wmx.test.ratelimiter;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author: wangmingxin03
 * @date: 2020-07-27
 */
public class SmoothWarmingUpTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(2,
                3, TimeUnit.SECONDS);

        System.out.println("get 1 tokens: " + rateLimiter.acquire(1) + "s");
        System.out.println("get 1 tokens: " + rateLimiter.acquire(1) + "s");
        System.out.println("get 1 tokens: " + rateLimiter.acquire(1) + "s");
        System.out.println("get 1 tokens: " + rateLimiter.acquire(1) + "s");
        System.out.println("get 1 tokens: " + rateLimiter.acquire(1) + "s");
        System.out.println("get 1 tokens: " + rateLimiter.acquire(1) + "s");
        System.out.println("get 1 tokens: " + rateLimiter.acquire(1) + "s");
        System.out.println("get 1 tokens: " + rateLimiter.acquire(1) + "s");


        /**
         * 起初 速率较慢，慢慢提升，趋于稳定  0.5s一个令牌
         *
         * get 1 tokens: 0.0s
         * get 1 tokens: 1.331792s
         * get 1 tokens: 0.994073s
         * get 1 tokens: 0.661631s
         * get 1 tokens: 0.495211s
         * get 1 tokens: 0.495872s
         * get 1 tokens: 0.495754s
         * get 1 tokens: 0.495933s
         * */
    }

}
