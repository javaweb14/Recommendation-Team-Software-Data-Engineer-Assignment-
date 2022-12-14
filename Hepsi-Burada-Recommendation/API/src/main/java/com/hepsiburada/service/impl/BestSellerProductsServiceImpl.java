package com.hepsiburada.service.impl;

import com.hepsiburada.database.model.BrowsingHistory;
import com.hepsiburada.database.model.OrderItems;
import com.hepsiburada.database.model.Products;
import com.hepsiburada.database.repository.BrowsingHistoryRepository;
import com.hepsiburada.database.repository.OrderItemsRepository;
import com.hepsiburada.database.repository.ProductsRepository;
import com.hepsiburada.dto.BrowsingHistoryDto;
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

        List<BrowsingHistoryDto> browsingHistoryDtos = leftJoinBrowsingHistory(browsingHistoryRepository.findByUserId(userId),productsRepository.findAll());

        List<String> atMostThreeCategories  = browsingHistoryDtos.stream().collect(Collectors.groupingBy(browsingHistoryDto -> browsingHistoryDto.getProductsDto().getCategoryId(), Collectors.counting())).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3).map(stringLongEntry -> stringLongEntry.getKey()).collect(Collectors.toList());

        if (atMostThreeCategories.isEmpty()) {
            List<String> bestSellerTenProductsWithoutFilter = orderItemsRepository.findAll().stream().collect(Collectors.groupingBy(OrderItems::getProductId, Collectors.summingDouble(OrderItems::getQuantity))).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(MAX_PRODUCT_SIZE).map(stringDoubleEntry -> stringDoubleEntry.getKey()).collect(Collectors.toList());
            return getBrowsingHistoryResponseDto(userId, bestSellerTenProductsWithoutFilter, NON_PERSONALIZED);
        }

        List<OrderItemsDto> allOrderItems = leftJoinOrderItems(orderItemsRepository.findAll(), productsRepository.findAll());
        List<String> bestSellerTenProducts = allOrderItems.stream().filter(orderItemsDto -> atMostThreeCategories.contains(orderItemsDto.getProductsDto().getCategoryId())).collect(Collectors.groupingBy(OrderItemsDto::getProductId, Collectors.summingInt(orderItemsDto -> (int) orderItemsDto.getQuantity()))).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(MAX_PRODUCT_SIZE).map(stringIntegerEntry -> stringIntegerEntry.getKey()).collect(Collectors.toList());


        return getBrowsingHistoryResponseDto(userId, bestSellerTenProducts, PERSONALIZED);
    }

    private Optional<BrowsingHistoryResponseDto> getBrowsingHistoryResponseDto(String userId, List<String> bestSellerTenProducts, String type) {
        if (bestSellerTenProducts.size() < MINIMUM_PRODUCT_SIZE) {
            return Optional.of(BrowsingHistoryResponseDto.builder().userId(userId).products(Collections.emptyList()).type(type).build());
        } else {
            return Optional.of(BrowsingHistoryResponseDto.builder().products(bestSellerTenProducts).userId(userId).type(type).build());
        }
    }

    private List<OrderItemsDto> leftJoinOrderItems(List<OrderItems> orderItems, List<Products> products) {
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

    private List<BrowsingHistoryDto> leftJoinBrowsingHistory(List<BrowsingHistory> browsingHistories, List<Products> products) {

        Map<String, Products> productId = products.stream().collect(Collectors.toMap(Products::getProductId, Function.identity()));

        return browsingHistories.stream()
                .map(browsingHistory -> {
                    BrowsingHistoryDto browsingHistoryDto = new BrowsingHistoryDto();
                    // set all fields from browsingHistory -> browsingHistoryDto
                    browsingHistoryDto.setProductId(browsingHistory.getProductId());
                    browsingHistoryDto.setProduceTime(browsingHistory.getProduceTime());
                    browsingHistoryDto.setUserId(browsingHistory.getUserId());

                    Products product = productId.get(browsingHistory.getProductId());

                    if (product != null) {
                        ProductsDto productsDto = new ProductsDto();
                        // set all fields from product -> productsDto
                        productsDto.setCategoryId(product.getCategoryId());
                        productsDto.setProductId(product.getProductId());

                        browsingHistoryDto.setProductsDto(productsDto);
                    }

                    return browsingHistoryDto;
                })
                .collect(Collectors.toList());
    }



}
