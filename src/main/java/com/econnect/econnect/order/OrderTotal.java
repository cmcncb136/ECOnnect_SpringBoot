package com.econnect.econnect.order;

import com.econnect.econnect.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer totalOrderId;

    private Integer totalCount;

    private Integer totalPrice;

    private LocalDate orderDate;

    @OneToMany(mappedBy = "orderTotal" , cascade = CascadeType.REMOVE) //mmpaedBy는 ManyToOne에 선언한 이름의 변수와 같아야 한다!
    private List<OrderDetail> detailOrderList;

    @ManyToOne
    private Member member;

    @Builder
    public OrderTotal(Integer totalOrderId, Integer totalCount, Integer totalPrice,
                      LocalDate orderDate, Member member) {
        this.totalOrderId = totalOrderId;
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.detailOrderList = new ArrayList<>();
        this.member = member;
    }

    public static OrderTotal toEntity(OrderTotalDto dto, Member member) {
        return OrderTotal.builder()
                .totalOrderId(dto.getTotalOrderId())
                .totalCount(dto.getTotalCount())
                .totalPrice(dto.getTotalPrice())
                .orderDate(dto.getOrderDate())
                .member(member)
                .build();
    }
}
