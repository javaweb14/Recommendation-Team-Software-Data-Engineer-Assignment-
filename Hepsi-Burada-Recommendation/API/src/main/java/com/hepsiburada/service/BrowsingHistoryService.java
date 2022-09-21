package com.hepsiburada.service;

import com.hepsiburada.dto.BrowsingHistoryResponseDto;
import com.hepsiburada.dto.DeleteBrowsingHistoryRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


public interface BrowsingHistoryService {
    Optional<BrowsingHistoryResponseDto> getLastTenProductsViewedByUserId(String userId);

    ResponseEntity<DeleteBrowsingHistoryRequestDto> deleteProductByUserId(String userId, String productId);
}
