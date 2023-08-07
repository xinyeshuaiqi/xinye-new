package pers.wmx.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;


/**
 * @author wangmingxin03
 * Created on 2021-12-15
 */
@Slf4j
@Service
public class KafkaConsumerHelper {
    private static final String TOPIC_NAME = "test-1";

    // 直接把Spring Boot应用起起来等待消费就行啦
    @KafkaListener(topics = TOPIC_NAME, groupId = "order-create")
    public void onMessage(OrderCreateMsg orderCreateMsg) {
        log.info("consume msg:{}", JSON.toJSONString(orderCreateMsg));
    }
}
