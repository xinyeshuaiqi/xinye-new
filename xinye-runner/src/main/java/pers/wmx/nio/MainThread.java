package pers.wmx.nio;

/**
 * @author wangmingxin03
 * Created on 2023-07-26
 */
public class MainThread {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " enter ...");
        SelectorThreadGroup selectorThreadGroup = new SelectorThreadGroup(5);
        selectorThreadGroup.bind(8886);
    }

}
