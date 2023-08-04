package pers.wmx.eventbus.observer;


import lombok.extern.slf4j.Slf4j;
import pers.wmx.eventbus.Subscribe;
import pers.wmx.eventbus.event.OrderPlaceEvent;

/**
 * @author wangmingxin03
 * Created on 2021-12-13
 */
@Slf4j
public class CObserver {
    @Subscribe
    public void handle(String msg) {
        log.info("CObserver msg:{}", msg);
    }

    @Subscribe
    public void handle(Integer msg) {
        log.info("CObserver msg:{}", msg);
    }

    @Subscribe
    public void handle(OrderPlaceEvent event) {
        log.info("CObserver | userId:{}, orderId:{}",
                event.getUserId(), event.getOrderId());
    }
}
