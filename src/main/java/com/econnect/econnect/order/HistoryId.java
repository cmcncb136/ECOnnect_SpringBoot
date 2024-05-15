package com.econnect.econnect.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class HistoryId implements Serializable {
    private Integer orderTotalId;

    @Column(length = 255)
    private String productId;
}
