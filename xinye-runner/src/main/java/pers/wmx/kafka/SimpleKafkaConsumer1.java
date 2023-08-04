package pers.wmx.kafka;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * @author wangmingxin03
 * Created on 2021-12-16
 */
public class SimpleKafkaConsumer1 {
    private static final String SERVER_LIST = "39.97.47.254:9092,39.97.47.254:9093";

    private static final String TOPIC_NAME = "xinye";

    // 再写一个手动提交的Consumer
    // 自动提交有丢失数据和重复消费的风险
    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_LIST);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 设置消费组
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "WMX");

        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        // 设置offset自动提交
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 消费指定topic
        consumer.subscribe(Collections.singletonList(TOPIC_NAME), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                // TODO
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                // TODO
            }
        });

        while (true) {
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(0).toMillis());

            if (!records.isEmpty()) {
                Iterator<ConsumerRecord<String, String>> iterator = records.iterator();

                while(iterator.hasNext()) {
                    ConsumerRecord<String, String> record = iterator.next();

                    // handle msg ...

                    // log.info("consume msg | key:{}, value:{}, partition:{}, offset:{}",record.key(), record.value(), record.partition(), record.offset());
                }

                // 批次处理完手动提交一波
                // 这里的手动提交策略，还可以处理一条提交一条更安全
                // 批次处理的话，如果中间挂了，下次又重新拉取会重复消费
                consumer.commitSync();
            }
        }

    }
}
