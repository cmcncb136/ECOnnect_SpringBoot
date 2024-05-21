package com.econnect.econnect.order;

import com.econnect.econnect.member.Member;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTotalDto {
    private Integer totalOrderId;
    private Integer totalCount;
    private Integer totalPrice;
    private LocalDate orderDate;
    private String memberId;

    public static OrderTotalDto toDto(OrderTotal entity) {
        return OrderTotalDto.builder()
                .totalOrderId(entity.getTotalOrderId())
                .totalCount(entity.getTotalCount())
                .totalPrice(entity.getTotalPrice())
                .orderDate(entity.getOrderDate())
                .memberId(entity.getMember().getUid())
                .build();
    }
}
