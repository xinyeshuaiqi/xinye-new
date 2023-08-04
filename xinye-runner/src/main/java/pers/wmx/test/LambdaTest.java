package pers.wmx.test;

/**
 * @author wangmingxin03
 * Created on 2022-01-25
 */
public class LambdaTest {
    public static void main(String[] args) {
        printString("test", (x) -> System.out.println(x));
    }

    public static void printString(String s, Print<String> print) {
        print.print(s);
    }
}
