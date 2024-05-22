package com.econnect.econnect.carbon_footprint_calculation;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarbonFootprintRank {
    @Id
    private Integer rankId;

    private Integer standard;

    @Column(length = 255)
    private String rank;

    @Column(length = 255)
    private String imgPath;

    @OneToMany(mappedBy = "carbonFootprintRank", cascade = CascadeType.REMOVE)
    private List<CarbonFootprintCalculationResult> carbonFootprintCalculationResultList;


    @Builder
    public CarbonFootprintRank(Integer rankId, Integer standard, String rank, String imgPath) {
        this.rankId = rankId;
        this.standard = standard;
        this.rank = rank;
        this.imgPath = imgPath;
        this.carbonFootprintCalculationResultList = new ArrayList<>();
    }


    public static CarbonFootprintRank toEntity(CarbonFootprintRankDto dto) {
        return CarbonFootprintRank.builder()
                .rankId(dto.getRankId())
                .standard(dto.getStandard())
                .rank(dto.getRank())
                .imgPath(dto.getImgPath())
                .build();
    }
}
