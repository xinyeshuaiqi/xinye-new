package pers.wmx.kafka;

import javax.annotation.Resource;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangmingxin03
 * Created on 2021-12-15
 */
@Slf4j
@Service
public class KafkaProducerHelper {
    private static final String TOPIC_NAME = "kafka-0807";

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult send(OrderCreateMsg orderCreateMsg) throws Exception {
        return kafkaTemplate.send(TOPIC_NAME, orderCreateMsg).get();
    }

    public ListenableFuture<SendResult<Object, Object>> asyncSend(OrderCreateMsg orderCreateMsg) {
        return kafkaTemplate.send(TOPIC_NAME, orderCreateMsg);
    }

}
