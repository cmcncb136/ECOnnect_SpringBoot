package com.econnect.econnect.member;

import com.econnect.econnect.carbon_footprint_calculation.CarbonFootprintCalculationResult;
import com.econnect.econnect.challenge.ChallengeState;
import com.econnect.econnect.order.OrderTotal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @Column(length = 255)
    private Integer id;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String address;

    private LocalDate joinDate;

    private int point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<CarbonFootprintCalculationResult> carbonFootprintCalculationResultsList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<ChallengeState> challengeStatesList;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<OrderTotal> totalOrderList;
}
