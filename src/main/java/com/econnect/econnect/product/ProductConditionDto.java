package com.econnect.econnect.product;

import lombok.*;

import java.util.List;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductConditionDto {
    private String productConditionId;
    private String productConditionName;

    public static ProductConditionDto toDto(ProductCondition entity) {
        return ProductConditionDto.builder()
                .productConditionId(entity.getProductConditionId())
                .productConditionName(entity.getProductConditionName())
                .build();
    }
}
