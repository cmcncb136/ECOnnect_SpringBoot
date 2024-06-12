package com.econnect.econnect.product;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @ManyToOne
    private Product product;

    @Builder
    public ProductRecommend(LocalDate date, Product product) {
        this.date = date;
        this.product = product;
    }

    public static ProductRecommend toEntity(ProductRecommendDto dto) {
        return ProductRecommend.builder()
                .date(LocalDate.parse(dto.getDate()))
                .product(Product.toEntity(dto.getProduct()))
                .build();
    }
}
