package com.hepsiburada.stream.reader.app.consumer;


import com.hepsiburada.common.dto.ProductViewEvent;
import com.hepsiburada.database.model.BrowsingHistory;
import com.hepsiburada.database.repository.BrowsingHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @Autowired
    BrowsingHistoryRepository browsingHistoryRepository;

    @KafkaListener(topics = "${productView.topic.name}", containerFactory = "productViewKafkaListenerContainerFactory")
    public void greetingListener(ProductViewEvent productViewEvent) {
        log.info("Received productViewEvent message: " + productViewEvent);
        BrowsingHistory browsingHistory = new BrowsingHistory(productViewEvent.getUserId(),productViewEvent.getProductId(),productViewEvent.getProduceTime());
        browsingHistoryRepository.save(browsingHistory);
    }
}
