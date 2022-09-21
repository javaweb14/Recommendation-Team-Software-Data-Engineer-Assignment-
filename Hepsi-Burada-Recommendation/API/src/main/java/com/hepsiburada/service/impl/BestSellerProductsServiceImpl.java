package com.hepsiburada.service.impl;

import com.hepsiburada.database.model.BrowsingHistory;
import com.hepsiburada.database.model.OrderItems;
import com.hepsiburada.database.model.Products;
import com.hepsiburada.database.repository.BrowsingHistoryRepository;
import com.hepsiburada.database.repository.OrderItemsRepository;
import com.hepsiburada.database.repository.ProductsRepository;
import com.hepsiburada.dto.BrowsingHistoryResponseDto;
import com.hepsiburada.dto.OrderItemsDto;
import com.hepsiburada.dto.ProductsDto;
import com.hepsiburada.service.BestSellerProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.hepsiburada.common.util.Constants.*;

@Service
public class BestSellerProductsServiceImpl implements BestSellerProductsService {

    @Autowired
    BrowsingHistoryRepository browsingHistoryRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Override
    public Optional<BrowsingHistoryResponseDto> getBestSellerProducts(String userId) {

        List<String> atMostThreeProducts = browsingHistoryRepository.findByUserId(userId).stream().collect(Collectors.groupingBy(BrowsingHistory::getProductId, Collectors.counting())).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3).map(stringLongEntry -> stringLongEntry.getKey()).collect(Collectors.toList());

        if (atMostThreeProducts.isEmpty()) {
            List<String> bestSellerTenProductsWithoutFilter = orderItemsRepository.findAll().stream().collect(Collectors.groupingBy(OrderItems::getProductId, Collectors.summingDouble(OrderItems::getQuantity))).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(MAX_PRODUCT_SIZE).map(stringDoubleEntry -> stringDoubleEntry.getKey()).collect(Collectors.toList());
            return Optional.of(BrowsingHistoryResponseDto.builder().products(bestSellerTenProductsWithoutFilter).userId(userId).type(NON_PERSONALIZED).build());
        }


        List<String> atMostThreeCategories = atMostThreeProducts.stream().map(s -> productsRepository.findByProductId(s)).map(products -> products.get().getCategoryId()).collect(Collectors.toList());

        List<OrderItemsDto> allOrderItems = leftJoin(orderItemsRepository.findAll(), productsRepository.findAll());
        List<String> bestSellerTenProducts = allOrderItems.stream().filter(orderItemsDto -> atMostThreeCategories.contains(orderItemsDto.getProductsDto().getCategoryId())).collect(Collectors.groupingBy(OrderItemsDto::getProductId, Collectors.summingInt(orderItemsDto -> (int) orderItemsDto.getQuantity()))).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(MAX_PRODUCT_SIZE).map(stringIntegerEntry -> stringIntegerEntry.getKey()).collect(Collectors.toList());


        if (bestSellerTenProducts.size() < MINIMUM_PRODUCT_SIZE) {
            return Optional.of(BrowsingHistoryResponseDto.builder().userId(userId).products(Collections.emptyList()).type(PERSONALIZED).build());
        } else {
            return Optional.of(BrowsingHistoryResponseDto.builder().products(bestSellerTenProducts).userId(userId).type(PERSONALIZED).build());
        }
    }

    private List<OrderItemsDto> leftJoin(List<OrderItems> orderItems, List<Products> products) {
        Map<String, Products> productId = products.stream().collect(Collectors.toMap(Products::getProductId, Function.identity()));

        return orderItems.stream()
                .map(orderItem -> {
                    OrderItemsDto orderItemsDto = new OrderItemsDto();
                    // set all fields from orderItem -> orderItemsDto
                    orderItemsDto.setProductId(orderItem.getProductId());
                    orderItemsDto.setQuantity(orderItem.getQuantity());

                    Products product = productId.get(orderItem.getProductId());

                    if (product != null) {
                        ProductsDto productsDto = new ProductsDto();
                        // set all fields from product -> productsDto
                        productsDto.setCategoryId(product.getCategoryId());
                        productsDto.setProductId(product.getProductId());

                        orderItemsDto.setProductsDto(productsDto);
                    }

                    return orderItemsDto;
                })
                .collect(Collectors.toList());
    }



}
