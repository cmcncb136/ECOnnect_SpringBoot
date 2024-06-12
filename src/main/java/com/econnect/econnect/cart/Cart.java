package com.econnect.econnect.cart;

import com.econnect.econnect.member.Member;
import com.econnect.econnect.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //아무 의미 없는 생성자가 생기지 않도록 방지한다.
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer count;

    private Boolean selected;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Product product;


    @Builder
    public Cart(Integer count, Member member, Product product) {
        this.count = count;
        this.member = member;
        this.product = product;
        this.selected = true;
    }

    @Builder
    public Cart(Integer count, Member member, Product product, Boolean selected) {
        this.count = count;
        this.member = member;
        this.product = product;
        this.selected = selected;
    }



    public static Cart toCart(CartDto dto, Member member, Product product) {
        return Cart.builder()
                .product(product)
                .member(member)
                .count(dto.getCount())
                .build();
    }
}

