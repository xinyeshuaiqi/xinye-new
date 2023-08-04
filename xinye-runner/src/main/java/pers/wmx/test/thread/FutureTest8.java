package pers.wmx.test.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wangmingxin03
 * Created on 2022-09-28
 */
public class FutureTest8 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            int a = 100 / 0;
            return "haha";
        }).handle((res, ex) -> {
            if (ex != null) {
                System.out.println(ex.getMessage());
                return "error";
            }

            return res;
        });

        System.out.println(future.get());
    }
}
