package com.hepsiburada.controller;

import com.hepsiburada.dto.BrowsingHistoryResponseDto;
import com.hepsiburada.dto.DeleteBrowsingHistoryRequestDto;
import com.hepsiburada.service.BrowsingHistoryService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BrowsingHistoryController {

    @Autowired
    BrowsingHistoryService browsingHistoryService;

    @GetMapping("/browsingHistory")
    public ResponseEntity<BrowsingHistoryResponseDto> getBrowsingHistorByUserId(@RequestParam String userId)
            {
        BrowsingHistoryResponseDto browsingHistoryResponseDto = browsingHistoryService.getBrowsingHistoryResponseDto(userId)
                .orElseThrow(() -> new ResourceNotFoundException("browsingHistory Response Dto not found for this user id :: " + userId));
        return ResponseEntity.ok().body(browsingHistoryResponseDto);
    }

    @DeleteMapping("/browsingHistory")
    public ResponseEntity<DeleteBrowsingHistoryRequestDto> deleteEmployee(@RequestBody @Validated DeleteBrowsingHistoryRequestDto deleteBrowsingHistoryRequestDto) {
       return browsingHistoryService.deleteProductByUserId(deleteBrowsingHistoryRequestDto.getUserId(), deleteBrowsingHistoryRequestDto.getProductId());

    }


}
