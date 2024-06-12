package com.econnect.econnect.cart;

import com.econnect.econnect.product.ProductDto;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private int id;
    private int count;
    @Builder.Default
    private boolean selected = true;
    private String memberId;
    private ProductDto product;

    public static CartDto toDto(Cart entity) {
        return CartDto.builder()
                .product(
                        ProductDto.toDto(entity.getProduct())
                ).id(entity.getId())
                .count(entity.getCount())
                .memberId(entity.getMember().getUid())
                .selected(entity.getSelected())
                .build();
    }
}
