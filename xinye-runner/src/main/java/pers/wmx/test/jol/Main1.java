package pers.wmx.test.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * JOL(Java Object Layout)
 * 查看对象的内存布局、内存踪迹和引用
 *
 * @author wangmingxin03
 * Created on 2021-11-12
 */
public class Main1 {

    public static void main(String[] args) {
        A a = new A();
        System.out.println("a : " + ClassLayout.parseInstance(a).toPrintable());
    }

    static class A {
        private int x = 10;
    }
}
