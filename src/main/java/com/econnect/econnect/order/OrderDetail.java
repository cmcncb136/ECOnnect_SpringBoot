package com.econnect.econnect.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class OrderDetail {
    @EmbeddedId
    private HistoryId historyId;

    private Integer count;

    private Integer totalPrice;

    @ManyToOne
    private OrderTotal orderTotal;
}
