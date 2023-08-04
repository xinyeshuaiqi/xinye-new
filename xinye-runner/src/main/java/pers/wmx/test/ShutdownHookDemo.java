package pers.wmx.test;

/**
 * @author wangmingxin03
 * Created on 2021-12-14
 */
public class ShutdownHookDemo {
    private static class ShutDownHook extends Thread {
        public void run() {
            System.out.println("shut down ... ");
        }
    }

    public static void main(String[] args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new ShutDownHook());
        Thread.sleep(5000);

        System.exit(0);
    }
}
