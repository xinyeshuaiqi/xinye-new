package pers.wmx.draw;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author wangmingxin03
 * Created on 2024-02-04
 */
public class Lottery {
    public static void main(String[] args) {
        String[] names = getNames();
        int count = names.length;
        Random random = new Random();
        int value = IntStream.range(0, 100).map(x -> random.nextInt(count)).sum();
        System.out.println(names[value % count]);
    }

    private static String[] getNames() {
        String names = "陈素伟;陈文龙;冯建勇;郝瑞鹏;何宗奎;洪伟;黄东;李茂英;刘福祥;龙江;罗昊;孙伟鹏;王金塑;汪明鑫;王一迪;汪值;谢尚;闫亚辉;张杜若;张浩楠";
        return names.split(";");
    }
}
