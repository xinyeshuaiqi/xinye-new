package pers.wmx.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmingxin03
 * Created on 2023-12-11
 */
public class Main3 {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);

        System.out.println(list.subList(0, 1));

    }
}
