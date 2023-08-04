package pers.wmx.eventbus.observer;


import lombok.extern.slf4j.Slf4j;
import pers.wmx.springbootfreemarkerdemo.eventbus.Subscribe;

/**
 * @author wangmingxin03
 * Created on 2021-12-13
 */
@Slf4j
public class AObserver {
    @Subscribe
    public void handle(String msg) {
        log.info("AObserver msg:{}", msg);
    }
}
