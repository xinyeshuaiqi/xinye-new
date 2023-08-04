package pers.wmx.eventbus;

import java.util.List;
import java.util.concurrent.Executor;

import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author wangmingxin03
 * Created on 2021-12-13
 */
public class EventBus {
    private Executor executor;

    private ObserverRegistry registry = new ObserverRegistry();

    public EventBus() {
        this(MoreExecutors.directExecutor());
    }

    protected EventBus(Executor executor) { this.executor = executor; }

    public void register(Object observer) {
        registry.register(observer);
    }

    public void post(Object event) {
        List<ObserverAction> observerActions = registry.getMatchedObserverActions(event);
        for (ObserverAction observerAction : observerActions) {
            executor.execute(() -> {
                observerAction.execute(event);
            });
        }
    }
}
