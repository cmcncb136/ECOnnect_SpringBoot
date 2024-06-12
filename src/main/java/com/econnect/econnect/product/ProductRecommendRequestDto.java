package com.econnect.econnect.product;

import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRecommendRequestDto {
    private Integer id;
    private String date;
    private String productId;
}
