package com.hepsiburada.view.producer.app;


import com.hepsiburada.view.producer.app.producer.KafkaProducer;
import com.hepsiburada.view.producer.app.util.JsonOperations;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
