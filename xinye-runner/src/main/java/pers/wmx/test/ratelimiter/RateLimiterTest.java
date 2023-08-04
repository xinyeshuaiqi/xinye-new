package pers.wmx.test.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author: wangmingxin03
 * @date: 2020-07-27
 */
public class RateLimiterTest {
    public static void main(String[] args) {
        //新建一个每秒限制5个的令牌桶
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(System.currentTimeMillis() / 1000);
        if (limiter.tryAcquire(20)) {  //欠20个
            System.out.println(System.currentTimeMillis() / 1000);
        }

        double waitTime = limiter.acquire(10);  //阻塞
        System.out.println(waitTime);
        System.out.println(System.currentTimeMillis() / 1000);

        /**
         * 1595844686
         * 1595844686
         * 3.999002  约4s
         * 1595844690
         * */
    }

}
