package com.hepsiburada.view.producer.app.util;



import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hepsiburada.common.dto.ProductViewEvent;
import com.hepsiburada.view.producer.app.dto.ProductViewJson;
import com.hepsiburada.view.producer.app.producer.KafkaProducer;
import lombok.SneakyThrows;

import java.io.*;
import java.util.*;

public class JsonOperations {

    public static ProductViewEvent readFirstRowFromJsonJson(String jsonName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = JsonOperations.class.getClassLoader();
        ProductViewJson productViewJson = mapper.readValue(new File(classLoader.getResource(jsonName).getFile()), ProductViewJson.class);
        return ProductViewEvent.builder().productId(productViewJson.getProperties().getProductid()).userId(productViewJson.getUserid()).produceTime(new Date()).build();

    }

    public static void deleteFirstRowFromJson(String jsonName) throws IOException {
        ClassLoader classLoader = JsonOperations.class.getClassLoader();
        Scanner fileScanner = new Scanner(new File(classLoader.getResource(jsonName).getFile()));
        fileScanner.nextLine();
        FileWriter fileStream = new FileWriter(new File(classLoader.getResource(jsonName).getFile()));
        BufferedWriter out = new BufferedWriter(fileStream);
        while(fileScanner.hasNextLine()) {
            String next = fileScanner.nextLine();
            if(next.equals("\n"))
                out.newLine();
            else
                out.write(next);
            out.newLine();
        }
        out.close();
    }

    @SneakyThrows
    public static void readAndPublishKafka(String jsonName, KafkaProducer kafkaProducer) throws IOException {

        ClassLoader classLoader = JsonOperations.class.getClassLoader();
        try (FileInputStream fis = new FileInputStream(classLoader.getResource(jsonName).getFile())) {
            JsonFactory jf = new JsonFactory();
            JsonParser jp = jf.createParser(fis);
            jp.setCodec(new ObjectMapper());
            jp.nextToken();
            while (jp.hasCurrentToken()) {
                ProductViewJson productViewJson = jp.readValueAs(ProductViewJson.class);
                jp.nextToken();
                kafkaProducer.sendProductViewMessage(ProductViewEvent.builder().productId(productViewJson.getProperties().getProductid()).userId(productViewJson.getUserid()).produceTime(new Date()).build());
                Thread.sleep(1000);
            }
        }

    }



}
