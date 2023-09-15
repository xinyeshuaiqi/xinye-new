package pers.wmx.test;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import com.google.common.collect.Maps;

/**
 * @author wangmingxin03
 * Created on 2023-09-15
 */
public class MapTest {

    public static void main(String[] args) {
        Map<Long, Long> itemCountMap = Maps.newHashMap();
        itemCountMap.put(199L, 100000L);
        List<Long> itemIdList = itemCountMap.entrySet().stream()
                .flatMap(e -> LongStream.range(0, e.getValue()).map(c -> e.getKey()).boxed())
                .collect(toList());
        System.out.println(itemIdList);
        Collections.shuffle(itemIdList);
        System.out.println(itemIdList);

    }
}
