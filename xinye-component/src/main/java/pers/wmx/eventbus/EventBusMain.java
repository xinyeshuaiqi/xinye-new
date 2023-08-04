package pers.wmx.eventbus;

import java.util.concurrent.Executors;

import pers.wmx.eventbus.event.OrderPlaceEvent;
import pers.wmx.eventbus.observer.AObserver;
import pers.wmx.eventbus.observer.BObserver;
import pers.wmx.eventbus.observer.CObserver;

/**
 * @author wangmingxin03
 * Created on 2021-12-13
 */
public class EventBusMain {
    private static final EventBus EVENT_BUS =
            new AsyncEventBus(Executors.newFixedThreadPool(10));

    public static void main(String[] args) {
        // 注册三个观察者 扔到本地注册表
        EVENT_BUS.register(new AObserver());
        EVENT_BUS.register(new BObserver());
        EVENT_BUS.register(new CObserver());

        EVENT_BUS.post("haha");
        EVENT_BUS.post(666);
        EVENT_BUS.post(new OrderPlaceEvent(123, 777));
    }

}
