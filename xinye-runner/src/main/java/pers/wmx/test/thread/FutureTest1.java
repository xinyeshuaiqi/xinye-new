package pers.wmx.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wangmingxin03
 * Created on 2022-09-28
 */
public class FutureTest1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<?>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<?> future = executorService.submit(new CallableTask());
            futureList.add(future);
        }

        futureList.forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return 1;
        }
    }
}
