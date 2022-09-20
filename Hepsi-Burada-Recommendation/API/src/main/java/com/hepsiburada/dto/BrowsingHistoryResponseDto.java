package com.hepsiburada.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BrowsingHistoryResponseDto {
    String userId;
    String type;
    List<String> products;
}
