package com.econnect.econnect.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
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
    private Condition condition;
}
