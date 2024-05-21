package com.econnect.econnect.product;


import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productId;
    private String name;
    private String description;
    private String manufacturer;
    private String imgPath;
    private Integer price;
    private Integer feedbackPoint;
    private Integer unitsInStock;
    private LocalDate registerDate;
    private Category category;
    private ProductCondition productCondition;

    public static ProductDto toDto(Product entity){
        return ProductDto.builder()
                .productId(entity.getProductId())
                .name(entity.getName())
                .description(entity.getDescription())
                .manufacturer(entity.getManufacturer())
                .imgPath(entity.getImgPath())
                .price(entity.getPrice())
                .feedbackPoint(entity.getFeedbackPoint())
                .unitsInStock(entity.getUnitsInStock())
                .registerDate(entity.getRegisterDate())
                .category(entity.getCategory())
                .productCondition(entity.getProductCondition())
                .build();
    }
}
