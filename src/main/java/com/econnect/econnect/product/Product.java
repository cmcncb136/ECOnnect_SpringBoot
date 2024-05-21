package com.econnect.econnect.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @Column(length = 255)
    private String productId;

    @Column(length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String manufacturer;

    @Column(length = 255)
    private String imgPath;

    private Integer price;
    private Integer feedbackPoint;
    private Integer unitsInStock;

    private LocalDate registerDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private ProductCondition productCondition;

    @Builder
    public Product(String productId, String name,
                   String description, String manufacturer,
                   String imgPath, Integer price,
                   Integer feedbackPoint, Integer unitsInStock,
                   LocalDate registerDate, Category category,
                   ProductCondition productCondition) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.imgPath = imgPath;
        this.price = price;
        this.feedbackPoint = feedbackPoint;
        this.unitsInStock = unitsInStock;
        this.registerDate = registerDate;
        this.category = category;
        this.productCondition = productCondition;
    }
}
