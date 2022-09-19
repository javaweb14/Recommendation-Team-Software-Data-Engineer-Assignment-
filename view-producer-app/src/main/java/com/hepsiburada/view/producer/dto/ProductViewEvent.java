package com.hepsiburada.view.producer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductViewEvent {
    String userId;
    String productId;
    private Date produceTime;
}
