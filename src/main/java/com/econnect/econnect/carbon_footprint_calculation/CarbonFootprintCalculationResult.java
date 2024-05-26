package com.econnect.econnect.carbon_footprint_calculation;

import com.econnect.econnect.member.Member;
import jakarta.persistence.*;
import lombok.*;

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

    @Builder
    public CarbonFootprintCalculationResult(
            LocalDate date, Double electricityScore, Double gasScore, Double waterScore, Double total,
            Member member, CarbonFootprintRank carbonFootprintRank) {
        this.date = date;
        this.electricityScore = electricityScore;
        this.gasScore = gasScore;
        this.waterScore = waterScore;
        this.total = total;
        this.member = member;
        this.carbonFootprintRank = carbonFootprintRank;
    }

    public static CarbonFootprintCalculationResult toEntity(CarbonFootprintCalculationResultDto dto, Member member) {
        return CarbonFootprintCalculationResult.builder()
                .date(dto.getDate())
                .electricityScore(dto.getElectricityScore())
                .gasScore(dto.getGasScore())
                .waterScore(dto.getWaterScore())
                .total(dto.getTotal())
                .carbonFootprintRank(
                        CarbonFootprintRank.toEntity(dto.getCarbonFootprintRank()))
                .member(member)
                .build();
    }
}
