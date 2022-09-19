package com.hepsiburada.view.producer.producer;

import com.hepsiburada.view.producer.dto.ProductViewEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaProducer {

    @Value(value = "${message.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, ProductViewEvent> kafkaTemplate;


    public void sendProductViewMessage(ProductViewEvent message) {
        kafkaTemplate.send(topicName, message)
          .addCallback(
            result -> log.info("Message sent to "+ topicName +"  topic: {}", message),
            ex -> log.error("Failed to send message", ex)
          );
    }
}
