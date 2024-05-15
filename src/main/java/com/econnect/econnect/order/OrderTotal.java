package com.econnect.econnect.order;

import com.econnect.econnect.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
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
}
