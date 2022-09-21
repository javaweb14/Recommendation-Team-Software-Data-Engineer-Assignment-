package com.hepsiburada.dto;

import lombok.Data;

@Data
public class OrderItemsDto {

    private String productId;

    private float quantity;

    private ProductsDto productsDto;
}
