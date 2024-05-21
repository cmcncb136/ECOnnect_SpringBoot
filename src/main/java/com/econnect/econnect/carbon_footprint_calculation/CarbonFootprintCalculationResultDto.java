package com.econnect.econnect.carbon_footprint_calculation;

import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarbonFootprintCalculationResultDto {
    private Integer id;
    private LocalDate date;
    private Double electricityScore;
    private Double gasScore;
    private Double waterScore;
    private Double total;

    private CarbonFootprintRank carbonFootprintRank;
    private String memberId;

    public static CarbonFootprintCalculationResultDto toDto(CarbonFootprintCalculationResult e) {
        return CarbonFootprintCalculationResultDto.builder()
                .id(e.getId())
                .date(e.getDate())
                .electricityScore(e.getElectricityScore())
                .gasScore(e.getGasScore())
                .waterScore(e.getWaterScore())
                .total(e.getTotal())
                .carbonFootprintRank(e.getCarbonFootprintRank())
                .memberId(e.getMember().getUid())
                .build();
    }
}
