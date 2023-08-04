package pers.wmx.eventbus;

import java.lang.reflect.Method;

import com.google.common.base.Preconditions;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangmingxin03
 * Created on 2021-12-13
 */
@Slf4j
public class ObserverAction {
    private Object target;

    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = Preconditions.checkNotNull(target);
        this.method = method;
        this.method.setAccessible(true);
    }

    public void execute(Object event) {
        try {
            method.invoke(target, event);
        } catch (Exception e) {
            log.error("execute exception ", e);
        }
    }
}
