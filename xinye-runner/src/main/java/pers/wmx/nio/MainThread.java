package pers.wmx.nio;

/**
 * @author wangmingxin03
 * Created on 2023-07-26
 */
public class MainThread {

    public static void main(String[] args) {
        SelectorThreadGroup selectorThreadGroup = new SelectorThreadGroup(3);
        selectorThreadGroup.bind(8886);
    }

}
