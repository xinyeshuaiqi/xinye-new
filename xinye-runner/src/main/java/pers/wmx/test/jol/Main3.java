package pers.wmx.test.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangmingxin03
 * Created on 2021-11-19
 */
public class Main3 {
    public static void main(String[] args) {
        // 关闭偏向锁延迟开启
        // -XX:BiasedLockingStartupDelay=0

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());  // 101 （偏向锁）

        // 只要主线程上偏向锁
        synchronized(o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());  // 101 （偏向锁）
        }
    }
}
