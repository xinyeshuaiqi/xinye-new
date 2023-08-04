package pers.wmx.test.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author wangmingxin03
 * Created on 2022-09-28
 */
public class FutureTest6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            System.out.println("future1");

            return "haha";
        });

        System.out.println("xixi");

        CompletableFuture<Void> future2 = future1.thenApplyAsync(str -> "future2")
                .thenAcceptAsync(System.out::println);

        CompletableFuture<Void> future3 = future1.thenApplyAsync(str -> "future3")
                .thenAcceptAsync(System.out::println);


        future2.get();
    }
}
