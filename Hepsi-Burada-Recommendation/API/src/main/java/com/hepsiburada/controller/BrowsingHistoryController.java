package com.hepsiburada.controller;

import com.hepsiburada.common.util.StringOperationUtil;
import com.hepsiburada.dto.BrowsingHistoryResponseDto;
import com.hepsiburada.dto.DeleteBrowsingHistoryRequestDto;
import com.hepsiburada.service.BrowsingHistoryService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/v1")
@Valid
public class BrowsingHistoryController {

    @Autowired
    BrowsingHistoryService browsingHistoryService;

    @GetMapping("/browsingHistory")
    public ResponseEntity<BrowsingHistoryResponseDto> getBrowsingHistorByUserId(@RequestParam String userId) {

        StringOperationUtil.checkStringEmptyOrNull(userId);
        BrowsingHistoryResponseDto browsingHistoryResponseDto = browsingHistoryService.getLastTenProductsViewedByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("browsingHistory Response Dto not found for this user id :: " + userId));
        return ResponseEntity.ok().body(browsingHistoryResponseDto);
    }

    @DeleteMapping("/browsingHistory")
    public ResponseEntity<DeleteBrowsingHistoryRequestDto> deleteBrowsingHistory(@RequestBody DeleteBrowsingHistoryRequestDto deleteBrowsingHistoryRequestDto) {
       return browsingHistoryService.deleteProductByUserId(deleteBrowsingHistoryRequestDto.getUserId(), deleteBrowsingHistoryRequestDto.getProductId());

    }


}
