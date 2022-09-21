package com.hepsiburada.service;

import com.hepsiburada.dto.BrowsingHistoryResponseDto;

import java.util.Optional;

public interface BestSellerProductsService {

    Optional<BrowsingHistoryResponseDto> getBestSellerProducts(String userId);
}
