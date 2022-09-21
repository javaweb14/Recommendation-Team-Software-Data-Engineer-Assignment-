package com.hepsiburada.controller;

import com.hepsiburada.common.util.StringOperationUtil;
import com.hepsiburada.dto.BrowsingHistoryResponseDto;
import com.hepsiburada.service.BestSellerProductsService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BestSellerProductsController {

    @Autowired
    BestSellerProductsService bestSellerProductsService;

    @GetMapping("/bestSellerProducts")
    public ResponseEntity<BrowsingHistoryResponseDto> getBestSellerProducts(@RequestParam String userId)
    {
        StringOperationUtil.checkStringEmptyOrNull(userId);
        BrowsingHistoryResponseDto browsingHistoryResponseDto = bestSellerProductsService.getBestSellerProducts(userId)
                .orElseThrow(() -> new ResourceNotFoundException("browsingHistory Response Dto not found for this user id :: " + userId));
        return ResponseEntity.ok().body(browsingHistoryResponseDto);
    }

}
