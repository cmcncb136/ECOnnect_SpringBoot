package com.econnect.econnect.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class HistoryId implements Serializable {
    private Integer orderTotalId;

    @Column(length = 255)
    private String productId;
}
