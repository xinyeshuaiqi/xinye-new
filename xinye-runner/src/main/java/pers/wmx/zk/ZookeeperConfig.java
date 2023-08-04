package pers.wmx.zk;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangmingxin03
 * Created on 2021-12-03
 */
@Configuration
public class ZookeeperConfig {
    private static final String connectString = "39.97.47.254:2182";

    private static final int timeout = 4000;

    @Bean(name = "zkClient")
    public ZooKeeper zkClient() {
        ZooKeeper zooKeeper = null;

        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);

            zooKeeper = new ZooKeeper(connectString, timeout, event -> {
                if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                    countDownLatch.countDown();
                }
            });

            countDownLatch.await();

        } catch (Exception e) {
        }

        return zooKeeper;
    }
}
