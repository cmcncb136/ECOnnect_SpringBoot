package com.econnect.econnect.carbon_footprint_calculation;

import com.econnect.econnect.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class CarbonFootprintCalculationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private Integer transportCount;

    private Integer vegetarianCount;

    private Integer recycleCount;

    private Integer total;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Rank rank;
}
