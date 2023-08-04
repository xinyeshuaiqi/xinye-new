package pers.wmx.test.jol;

import java.util.concurrent.TimeUnit;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangmingxin03
 * Created on 2021-11-19
 */
public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        // 偏向锁是过几秒钟才启动，Jvm启动会有大量线程开启偏向锁延迟些启用
        // 所以可能大家试过好多次，都是无锁直接到轻量级锁或者重量级锁。。。

        // 尝试睡几秒，等一波偏向锁升级
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());  // 101 （偏向锁）

        // 只要主线程上偏向锁
        synchronized(o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());  // 101 （偏向锁）
        }

        Thread t1 = new Thread(() -> {
            synchronized (o) {
                System.out.println("t1 lock " + Thread.currentThread().getName());
                System.out.println(ClassLayout.parseInstance(o).toPrintable());  // 000 (偏向锁撤销, 升级轻量级锁)
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (o) {
                System.out.println("t2 lock " + Thread.currentThread().getName());
                System.out.println(ClassLayout.parseInstance(o).toPrintable());  // 010 (重量级锁)
            }
        });
        t2.start();

    }
}
