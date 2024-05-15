package com.econnect.econnect.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Condition {
    @Id
    @Column(length = 255)
    private String conditionId;

    @Column(length = 255)
    private String condition;

    @OneToMany(mappedBy = "condition")
    private List<Product> productList;

}
