package com.econnect.econnect.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @Column(length = 255)
    private String categoryId;

    @Column(length = 255)
    private String  name;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    @Builder
    public Category(String categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
        productList = new ArrayList<>();
    }
}
