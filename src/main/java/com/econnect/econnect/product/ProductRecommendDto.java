package com.econnect.econnect.product;


import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRecommendDto {
    private Integer id;
    private String date;
    private ProductDto product;

    public static ProductRecommendDto otDto(ProductRecommend productRecommend) {
        return ProductRecommendDto.builder()
                .product(ProductDto.toDto(productRecommend.getProduct()))
                .date(productRecommend.getDate().toString())
                .build();
    }
}
