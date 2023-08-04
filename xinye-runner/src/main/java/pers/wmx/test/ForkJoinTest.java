package pers.wmx.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author: wangmingxin03
 * @date: 2020-08-21
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        List<String> s = new ArrayList<>();
        s.add("1");
        s.add("2");
        s.add("3");
        s.add("4");
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);

        forkJoinPool.submit(() -> {
            s.parallelStream().forEach((number) -> {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) { }
                System.out.println(Thread.currentThread()+"--"+number);
            });
        });
    }
}
