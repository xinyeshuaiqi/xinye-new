package pers.wmx.kafka;


import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author wangmingxin03
 * Created on 2021-12-16
 */
public class SimpleKafkaProducer {
    private static final String SERVER_LIST = "39.97.47.254:9092,39.97.47.254:9093";

    private static final String TOPIC_NAME = "xinye";

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_LIST);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "888");
        Future<RecordMetadata> result = producer.send(record);

        RecordMetadata recordMetadata = result.get();
        int partition = recordMetadata.partition();
        long offset = recordMetadata.offset();
        // log.info("produce msg | partition:{}, offset:{}", partition, offset);
    }
}
