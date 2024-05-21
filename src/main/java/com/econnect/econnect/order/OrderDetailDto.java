package com.econnect.econnect.order;


import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.Order;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private HistoryId historyId;
    private Integer count;
    private Integer totalPrice;
    private Integer  orderTotalId;

    public static OrderDetailDto toDto(OrderDetail entity) {
        return OrderDetailDto.builder()
                .historyId(entity.getHistoryId())
                .count(entity.getCount())
                .totalPrice(entity.getTotalPrice())
                .orderTotalId(entity.getOrderTotal().getTotalOrderId())
                .build();
    }
}
