package com.econnect.econnect.member;

import com.econnect.econnect.carbon_footprint_calculation.CarbonFootprintCalculationResult;
import com.econnect.econnect.cart.Cart;
import com.econnect.econnect.challenge.ChallengeState;
import com.econnect.econnect.order.OrderTotal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //아무 의미 없는 생성자가 생기지 않도록 방지한다.
public class Member {
    @Id
    @Column(length = 255)
    private String uid;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String address;

    @Column(length = 255)
    private String phone;

    private LocalDate joinDate;

    private int point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<CarbonFootprintCalculationResult> carbonFootprintCalculationResultsList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<ChallengeState> challengeStatesList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<OrderTotal> totalOrderList;

    @OneToMany(mappedBy = "member", cascade =  CascadeType.REMOVE)
    private List<Cart> cartList;


    @Builder
    public Member(String uid, String name, String address,
                  String phone, int point) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.joinDate = LocalDate.now();
        this.phone = phone;
        this.point = point;
        carbonFootprintCalculationResultsList = new ArrayList<>();
        challengeStatesList = new ArrayList<>();
        totalOrderList = new ArrayList<>();
        cartList = new ArrayList<>();
    }


    public static Member toEntity(MemberDto dto){
        if(dto == null) return null;

        return Member.builder()
                .uid(dto.getUid())
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .point(0)
                .build();
    }

}
