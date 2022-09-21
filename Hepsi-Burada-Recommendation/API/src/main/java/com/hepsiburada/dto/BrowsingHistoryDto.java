package com.hepsiburada.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BrowsingHistoryDto {

    private String userId;

    private String productId;

    private Date produceTime;

    private ProductsDto productsDto;
}
