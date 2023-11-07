package pers.wmx.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangmingxin03
 * Created on 2023-11-07
 */
public class StreamTest {
    public static void main(String[] args) {
        Map<String, List<Long>> appIdLiveStreamListMap = new HashMap<>();
        List<Long> listA = new ArrayList<>();
        listA.add(1L);
        listA.add(8L);
        listA.add(8L);
        listA.add(2L);
        appIdLiveStreamListMap.put("a", listA);

        List<Long> listB = new ArrayList<>();
        listB.add(9L);
        listB.add(1L);
        listB.add(3L);
        listB.add(5L);
        listB.add(10L);
        appIdLiveStreamListMap.put("b", listB);

        List<Long> listC = new ArrayList<>();
        listC.add(9L);
        listC.add(1L);
        listC.add(3L);
        listC.add(5L);
        listC.add(10L);
        listC.add(19L);
        listC.add(11L);
        listC.add(13L);
        listC.add(5L);
        listC.add(10L);
        appIdLiveStreamListMap.put("c", listC);

        List<Long> result = appIdLiveStreamListMap.keySet().stream()
                .map(appIdLiveStreamListMap::get)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
