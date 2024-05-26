package com.econnect.econnect.product;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    //@JsonSerialize(using = LocalDateSerializer.class)
    //@JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public static Product toEntity(ProductDto dto){
        return Product.builder()
                .productId(dto.getProductId())
                .name(dto.getName())
                .description(dto.getDescription())
                .manufacturer(dto.getManufacturer())
                .imgPath(dto.getImgPath())
                .price(dto.getPrice())
                .feedbackPoint(dto.getFeedbackPoint())
                .unitsInStock(dto.getUnitsInStock())
                .registerDate(dto.getRegisterDate())
                .category(Category.toEntity(dto.getCategory()))
                .productCondition(
                        ProductCondition.toEntity(dto.getProductCondition()))
                .build();
    }
}
