package pers.wmx.test.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wangmingxin03
 * Created on 2022-09-28
 */
public class FutureTest5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 传递线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            return "haha";
        }, executorService);

        System.out.println(future.get());
    }
}
