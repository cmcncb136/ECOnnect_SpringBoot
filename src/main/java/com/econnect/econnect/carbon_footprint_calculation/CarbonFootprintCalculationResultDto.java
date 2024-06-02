package com.econnect.econnect.carbon_footprint_calculation;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarbonFootprintCalculationResultDto {
    private Integer id;
    private String date;
    private Double electricityScore;
    private Double gasScore;
    private Double waterScore;
    private Double total;
    private CarbonFootprintRankDto carbonFootprintRank;
    private String memberId;

    public static CarbonFootprintCalculationResultDto toDto(CarbonFootprintCalculationResult e) {

        return CarbonFootprintCalculationResultDto.builder()
                .id(e.getId())
                .date(e.getDate().toString())
                .electricityScore(e.getElectricityScore())
                .gasScore(e.getGasScore())
                .waterScore(e.getWaterScore())
                .total(e.getTotal())
                .carbonFootprintRank(
                        CarbonFootprintRankDto.toDto(e.getCarbonFootprintRank()))
                .memberId(e.getMember().getUid())
                .build();

    }
}
