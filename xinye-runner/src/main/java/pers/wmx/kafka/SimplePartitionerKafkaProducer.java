package pers.wmx.kafka;


import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author wangmingxin03
 * Created on 2021-12-16
 */
public class SimplePartitionerKafkaProducer {
    private static final String SERVER_LIST = "39.97.47.254:9092,39.97.47.254:9093";

    private static final String TOPIC_NAME = "xinye";

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_LIST);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 指定分区策略 partitioner.class，默认的 DefaultPartitioner
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, DefaultPartitioner.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "1", "888");  // 指定了key,固定打到一个分区
        // ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "2", "888");  固定打到一个分区
        Future<RecordMetadata> result = producer.send(record);

        RecordMetadata recordMetadata = result.get();
        int partition = recordMetadata.partition();
        long offset = recordMetadata.offset();
        // log.info("produce msg | partition:{}, offset:{}", partition, offset);
    }
}
