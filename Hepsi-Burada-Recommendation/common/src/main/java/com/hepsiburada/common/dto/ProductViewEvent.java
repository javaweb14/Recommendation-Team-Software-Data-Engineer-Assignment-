package com.hepsiburada.common.dto;



import lombok.*;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductViewEvent {
    private String userId;
    private String productId;
    private Date produceTime;

    @Override
    public String toString() {
        return "ProductViewEvent{" +
                "userId='" + userId + '\'' +
                ", productId='" + productId + '\'' +
                ", produceTime=" + produceTime +
                '}';
    }
}
