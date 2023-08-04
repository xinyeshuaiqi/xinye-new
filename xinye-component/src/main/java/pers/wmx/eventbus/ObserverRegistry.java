package pers.wmx.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 管理观察者
 *
 * @author wangmingxin03
 * Created on 2021-12-13
 */
@Slf4j
public class ObserverRegistry {
    private ConcurrentMap<Class<?>, CopyOnWriteArraySet<ObserverAction>> registry =
            new ConcurrentHashMap<>();

    /**
     * 注册观察者
     **/
    public void register(Object observer) {
        // 拿到这个观察者需要的所有观察事件
        Map<Class<?>, Collection<ObserverAction>> observerActions = findAllObserverActions(observer);

        for(Map.Entry<Class<?>, Collection<ObserverAction>> entry : observerActions.entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<ObserverAction> eventActions = entry.getValue();

            CopyOnWriteArraySet<ObserverAction> registryEventActions = registry.get(eventType);
            if (registryEventActions == null) {
                registry.putIfAbsent(eventType, new CopyOnWriteArraySet<>());
                registryEventActions = registry.get(eventType);
            }

            registryEventActions.addAll(eventActions);
        }
    }

    private Map<Class<?>, Collection<ObserverAction>> findAllObserverActions(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = new HashMap<>();
        Class<?> clazz = observer.getClass();

        List<Method> methods =  getAnnotatedMethods(clazz);
        for (Method method : methods) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];

            if (!observerActions.containsKey(eventType)) {
                observerActions.put(eventType, new ArrayList<>());
            }

            observerActions.get(eventType).add(new ObserverAction(observer, method));
        }

        return observerActions;
    }

    /**
     * 获取有 @Subscribe 注解的方法
     **/
    private List<Method> getAnnotatedMethods(Class<?> clazz) {
        List<Method> methods = Lists.newArrayList();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();

                // 方法参数只能有一个 -> 事件类型
                if (parameterTypes.length != 1) {
                    log.error("invalid method :{}", method.toString());
                } else {
                    methods.add(method);
                }
            }
        }

        return methods;
    }

    public List<ObserverAction> getMatchedObserverActions(Object event) {
        log.info("getMatchedObserverActions | registry:{}", JSON.toJSON(registry));

        List<ObserverAction> observerActions = Lists.newArrayList();

        Class<?> clazz = event.getClass();
        for(Map.Entry<Class<?>, CopyOnWriteArraySet<ObserverAction>> entry : registry.entrySet()) {
            Class<?> eventType = entry.getKey();
            CopyOnWriteArraySet<ObserverAction> eventActions = entry.getValue();

            if (clazz.isAssignableFrom(eventType)) {
                observerActions.addAll(eventActions);
            }
        }

        return observerActions;
    }
}
