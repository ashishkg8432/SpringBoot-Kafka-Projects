package com.example.springbootkafka.kafka;

import com.example.springbootkafka.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {


    @Value("${spring.kafka.topic-json.name}")
    private String jsonTopicName;

    private static final Logger logger= LoggerFactory.getLogger(JsonKafkaProducer.class);

    KafkaTemplate<String, User> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User data)
    {
        logger.info(String.format("Message sent-> %s",data.toString()));
        Message<User> message= MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC,jsonTopicName)
                .build();
        kafkaTemplate.send(message);
    }
}
