package pers.wmx.test.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author: wangmingxin03
 * @date: 2020-07-27
 */
public class SmoothBurstyTest {
    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(5);

        while (true) {
            System.out.println("get 1 tokens: " + limiter.acquire() + "s");
        }


        /**
         * 基本上是0.2s执行一次
         *
         * get 1 tokens: 0.0s
         * get 1 tokens: 0.198228s
         * get 1 tokens: 0.19413s
         * get 1 tokens: 0.196393s
         * get 1 tokens: 0.197964s
         * get 1 tokens: 0.196329s
         * get 1 tokens: 0.196447s
         * get 1 tokens: 0.1957s
         * get 1 tokens: 0.197344s
         * get 1 tokens: 0.195927s
         * get 1 tokens: 0.194946s
         * get 1 tokens: 0.196722s
         *
         * */
    }

}
