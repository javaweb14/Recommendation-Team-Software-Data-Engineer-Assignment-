package com.hepsiburada.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBrowsingHistoryRequestDto {

    @NotBlank
    @NotNull
    String userId;

    @NotBlank
    @NotNull
    String productId;
}
