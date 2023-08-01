package pers.wmx.nio1;

/**
 * @author wangmingxin03
 * Created on 2023-07-26
 */
public class MainThread {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " enter ...");
        SelectorThreadGroup boss = new SelectorThreadGroup(2);
        SelectorThreadGroup worker = new SelectorThreadGroup(4);
        boss.setWorker(worker);

        boss.bind(8886);
        boss.bind(8887);
        boss.bind(8888);
    }

}
