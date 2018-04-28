package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Winby
 * @date 2018/4/8  15:47
 * @update
 * @since v1.0.0
 */
@Component
public class MsgConsumer {
    //    @KafkaListener(topics = {"topic1","topic2","topic3"})
//    @KafkaListener(topics = {"topic1", "topic2"})
//    @KafkaListener(topics = {"${spring.kafka.topic1}","${spring.kafka.topic2}","${spring.kafka.topic3}"})


        @KafkaListener(topics = {"${spring.kafka.topic1}","${spring.kafka.topic2}","${spring.kafka.topic3}"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println(record.topic() + "消息被消费" + message);
//            System.out.println("listen1 " + message+"________"+System.currentTimeMillis());
        }
    }
//    public void processMessage(String content) {
//
//
//    }
}
