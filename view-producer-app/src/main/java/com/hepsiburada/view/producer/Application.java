package com.hepsiburada.view.producer;

import com.hepsiburada.view.producer.producer.KafkaProducer;
import com.hepsiburada.view.producer.util.JsonOperations;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class Application {

	@SneakyThrows
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		KafkaProducer kafkaProducer = context.getBean(KafkaProducer.class);
		String jsonFileName = context.getBeanFactory().resolveEmbeddedValue("${json.file.name}");
		JsonOperations.readAndPublishKafka(jsonFileName,kafkaProducer);
	}

}
