package com.hepsiburada.view.producer.app.dto;

import lombok.Data;


@Data
public class ProductViewJson {

    String event;
    String userid;
    String messageid;
    Properties properties;
    Context context;


}
