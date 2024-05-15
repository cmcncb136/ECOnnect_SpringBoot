package com.econnect.econnect.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Category {
    @Id
    @Column(length = 255)
    private String categoryId;

    @Column(length = 255)
    private String  name;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
