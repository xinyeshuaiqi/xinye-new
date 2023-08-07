package pers.wmx.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangmingxin03
 * Created on 2021-12-15
 */
@Slf4j
@SpringBootTest
public class KafkaTest {
    @Autowired
    private KafkaProducerHelper kafkaProducerHelper;

    @Test
    public void testSend() throws Exception {
        OrderCreateMsg msg = new OrderCreateMsg(2, 234);
        SendResult sendResult = kafkaProducerHelper.send(msg);
        log.info("sendResult:{}", sendResult);
    }

}
