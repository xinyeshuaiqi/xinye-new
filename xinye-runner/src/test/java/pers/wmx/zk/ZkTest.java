package pers.wmx.zk;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangmingxin03
 * Created on 2021-12-03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ZkTest {
    @Autowired
    private ZooKeeper zkClient;

    @Test
    public void testInsert() {
        log.info("testInsert");
        try {
            zkClient.create("/hello1", "zk1".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (Exception e) {
            log.info("exception", e);
        }
    }

    @Test
    public void testQuery() {
        try {
            Stat stat = new Stat();
            byte[] bytes = zkClient.getData("/hello/hello1", false, stat);
        } catch (Exception e) {
        }
    }

    @Test
    public void testWatcher() {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Stat exists = zkClient.exists("/hello", watchedEvent -> {

                // 注册一个监听器
                countDownLatch.countDown();

            });

            countDownLatch.await();
        } catch (Exception e) {
        }
    }

}
