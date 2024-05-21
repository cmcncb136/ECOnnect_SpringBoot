package com.econnect.econnect.carbon_footprint_calculation;

import com.econnect.econnect.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarbonFootprintCalculationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private Double electricityScore;

    private Double gasScore;

    private Double waterScore;

    private Double total;

    @ManyToOne
    private Member member;

    @ManyToOne
    private CarbonFootprintRank carbonFootprintRank;

    public CarbonFootprintCalculationResult(
            Integer id, LocalDate date, Double electricityScore, Double gasScore, Double waterScore, Double total,
            Member member, CarbonFootprintRank carbonFootprintRank) {
        this.id = id;
        this.date = date;
        this.electricityScore = electricityScore;
        this.gasScore = gasScore;
        this.waterScore = waterScore;
        this.total = total;
        this.member = member;
        this.carbonFootprintRank = carbonFootprintRank;
    }
}
