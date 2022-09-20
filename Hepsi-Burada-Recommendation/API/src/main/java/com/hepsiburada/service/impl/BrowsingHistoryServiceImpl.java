package com.hepsiburada.service.impl;


import com.hepsiburada.database.model.BrowsingHistory;
import com.hepsiburada.database.repository.BrowsingHistoryRepository;
import com.hepsiburada.dto.BrowsingHistoryResponseDto;
import com.hepsiburada.dto.DeleteBrowsingHistoryRequestDto;
import com.hepsiburada.service.BrowsingHistoryService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hepsiburada.common.util.Constants.PERSONALIZED;

@Service
public class BrowsingHistoryServiceImpl implements BrowsingHistoryService {


    @Autowired
    BrowsingHistoryRepository browsingHistoryRepository;

    @Override
    public Optional<BrowsingHistoryResponseDto> getBrowsingHistoryResponseDto(String userId) {
        List<String> browsingHistoryList = browsingHistoryRepository.
                findByUserId(userId).stream()
                .sorted(Comparator.comparing(o -> o.getProduceTime()))
                .limit(10)
                .map(browsingHistory -> browsingHistory.getProductId())
                .collect(Collectors.toList());

        if(browsingHistoryList.size() < 5) {
           return Optional.of(BrowsingHistoryResponseDto.builder().userId(userId).products(Collections.emptyList()).type(PERSONALIZED).build());
        } else {
            return Optional.of(BrowsingHistoryResponseDto.builder().products(browsingHistoryList).userId(userId).type(PERSONALIZED).build());
        }
    }

    @Override
    public ResponseEntity<DeleteBrowsingHistoryRequestDto> deleteProductByUserId(String userId, String productId) {
        BrowsingHistory browsingHistory = browsingHistoryRepository.findByUserIdAndProductId(userId, productId).orElseThrow(() -> new ResourceNotFoundException(String.format("Browsing history not found for  user id:: %s and product id :: %s ",userId,productId)));
        browsingHistoryRepository.delete(browsingHistory);
        return ResponseEntity.ok(DeleteBrowsingHistoryRequestDto.builder().productId(productId).userId(userId).build());
    }
}
