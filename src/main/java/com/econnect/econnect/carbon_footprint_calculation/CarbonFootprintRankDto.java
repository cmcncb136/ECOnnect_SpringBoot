package com.econnect.econnect.carbon_footprint_calculation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarbonFootprintRankDto{
    private Integer rankId;
    private Integer standard;
    private String rank;
    private String imgPath;

    public static CarbonFootprintRankDto toDto(CarbonFootprintRank entity) {
        return CarbonFootprintRankDto.builder()
                .rankId(entity.getRankId())
                .standard(entity.getStandard())
                .rank(entity.getRank())
                .imgPath(entity.getImgPath())
                .build();
    }
}
