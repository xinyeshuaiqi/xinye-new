package pers.wmx.test;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author wangmingxin03
 * Created on 2023-07-27
 */
public class Main1 {
    public static void main(String[] args) {
        Map<Integer, Integer> map = Maps.newHashMap();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 30);

        long value = map.values().stream()
                .mapToLong(Long::valueOf)
                .sum();
        System.out.println(value);
    }
}
