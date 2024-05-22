package com.econnect.econnect.order;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //아무 의미 없는 생성자가 생기지 않도록 방지한다.
public class OrderDetail {
    @EmbeddedId
    private HistoryId historyId;

    private Integer count;

    private Integer totalPrice;

    @ManyToOne
    private OrderTotal orderTotal;

    @Builder
    public OrderDetail(HistoryId historyId, Integer count, Integer totalPrice, OrderTotal orderTotal) {
        this.historyId = historyId;
        this.count = count;
        this.totalPrice = totalPrice;
        this.orderTotal = orderTotal;
    }

    public static OrderDetail toEntity(OrderDetailDto dto, OrderTotal orderTotal) {
        return OrderDetail.builder()
                .historyId(dto.getHistoryId())
                .count(dto.getCount())
                .totalPrice(dto.getTotalPrice())
                .orderTotal(orderTotal)
                .build();
    }
}
