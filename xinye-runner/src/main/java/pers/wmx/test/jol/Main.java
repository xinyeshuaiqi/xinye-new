package pers.wmx.test.jol;

import java.util.concurrent.TimeUnit;

import org.openjdk.jol.info.ClassLayout;

/**
 * JOL(Java Object Layout)
 * 查看对象的内存布局、内存踪迹和引用
 *
 * @author wangmingxin03
 * Created on 2021-11-12
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());  // 001 （无锁）

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable()); // 000 （轻量级锁）
        }

        Thread t1 = new Thread(() -> {
            synchronized (o) {
                System.out.println("t1 lock " + Thread.currentThread().getName());
                System.out.println(ClassLayout.parseInstance(o).toPrintable());  // 010 (重量级锁)
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (o) {
                System.out.println("t2 lock " + Thread.currentThread().getName());
                System.out.println(ClassLayout.parseInstance(o).toPrintable());  // 010 (重量级锁)
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        // 线程都执行完,释放锁
        System.out.println("after thread lock : " + ClassLayout.parseInstance(o).toPrintable()); // 001 （无锁）
    }
}
