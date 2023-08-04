package pers.wmx.eventbus;

import java.util.concurrent.Executor;

/**
 * @author wangmingxin03
 * Created on 2021-12-13
 */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
