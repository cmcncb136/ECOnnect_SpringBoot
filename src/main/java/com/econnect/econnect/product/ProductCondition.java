package com.econnect.econnect.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class ProductCondition {
    @Id
    @Column(length = 255)
    private String productConditionId;

    @Column(length = 255)
    private String productConditionName;

    @OneToMany(mappedBy = "productCondition", cascade = CascadeType.REMOVE)
    private List<Product> productList;

    @Builder
    public ProductCondition(String productConditionId, String productConditionName) {
        this.productConditionId = productConditionId;
        this.productConditionName = productConditionName;
        this.productList = new ArrayList<>();
    }


    public static ProductCondition toEntity(ProductConditionDto dto) {
        return ProductCondition.builder()
                .productConditionId(dto.getProductConditionId())
                .productConditionName(dto.getProductConditionName())
                .build();
    }
}
