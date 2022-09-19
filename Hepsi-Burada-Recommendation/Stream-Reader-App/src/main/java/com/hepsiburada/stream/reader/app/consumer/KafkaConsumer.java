package com.hepsiburada.stream.reader.app.consumer;


import com.hepsiburada.common.dto.ProductViewEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "${productView.topic.name}", containerFactory = "productViewKafkaListenerContainerFactory")
    public void greetingListener(ProductViewEvent productViewEvent) {
        log.info("Received productViewEvent message: " + productViewEvent.getProductId());
    }
}
