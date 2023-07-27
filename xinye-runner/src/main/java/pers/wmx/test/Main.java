package pers.wmx.test;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author wangmingxin03
 * Created on 2023-07-27
 */
public class Main {
    public static void main(String[] args) {
        Map<Integer, Long> map = Maps.newHashMap();
        map.put(1, 10L);
        map.put(2, 20L);
        map.put(3, 30L);

        Long a = 1L;
        System.out.println(map.get(a));
        System.out.println(map.get(a.intValue()));
    }
}
