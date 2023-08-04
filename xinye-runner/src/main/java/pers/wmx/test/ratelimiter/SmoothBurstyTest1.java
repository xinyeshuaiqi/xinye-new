package pers.wmx.test.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author: wangmingxin03
 * @date: 2020-07-27
 */
public class SmoothBurstyTest1 {
    public static void main(String[] args) {
        RateLimiter r = RateLimiter.create(5);

        System.out.println("get 5 tokens: " + r.acquire(5) + "s");
        System.out.println("get 1 tokens: " + r.acquire(1) + "s");
        System.out.println("get 1 tokens: " + r.acquire(1) + "s");
        System.out.println("get 1 tokens: " + r.acquire(1) + "s");
        System.out.println("get 1 tokens: " + r.acquire(1) + "s");
        System.out.println("get 1 tokens: " + r.acquire(1) + "s");

        /**
         * 第一个acquire(5) 马上返回
         * 但是后面的acquire(1) 需要等待1s,替上一个请求还债
         * 再到后面的acquire(1) 又趋于平稳
         *
         * get 5 tokens: 0.0s
         * get 1 tokens: 0.998554s
         * get 1 tokens: 0.193392s
         * get 1 tokens: 0.198538s
         * get 1 tokens: 0.194447s
         * get 1 tokens: 0.198013s
         *
         * */

    }

}

