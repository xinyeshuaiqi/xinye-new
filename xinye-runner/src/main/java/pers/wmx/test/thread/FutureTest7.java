package pers.wmx.test.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author wangmingxin03
 * Created on 2022-09-28
 */
public class FutureTest7 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            int a = 100 / 0;
            return "haha";
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return "exception";
        });

        System.out.println(future.get());
    }
}
