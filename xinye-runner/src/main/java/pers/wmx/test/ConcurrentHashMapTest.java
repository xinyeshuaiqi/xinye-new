package pers.wmx.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangmingxin03
 * Created on 2022-06-07
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1, 2);

        map.get(1);
    }
}
