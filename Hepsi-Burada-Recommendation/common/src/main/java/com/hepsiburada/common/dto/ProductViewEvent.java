package com.hepsiburada.common.dto;



import lombok.*;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductViewEvent {
    String userId;
    String productId;
    private Date produceTime;
}
