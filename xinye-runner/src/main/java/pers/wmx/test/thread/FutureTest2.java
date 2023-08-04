package pers.wmx.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author wangmingxin03
 * Created on 2022-09-28
 */
public class FutureTest2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask futureTask = new FutureTask(new CallableTask());
        new Thread(futureTask).start();

        System.out.println(futureTask.get(3500, TimeUnit.MILLISECONDS));
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return 1;
        }
    }
}
