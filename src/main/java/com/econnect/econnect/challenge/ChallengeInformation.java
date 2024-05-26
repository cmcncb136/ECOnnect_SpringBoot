package com.econnect.econnect.challenge;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeInformation {
    @Id
    private String challengeId;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String infoContent;

    @Column(length = 255)
    private String imgPath;

    private Integer point;

    private Boolean everydayChallenge;

    @OneToMany(mappedBy = "challengeInformation", cascade = CascadeType.REMOVE)
    List<ChallengeState> challengeStateList;

    @OneToMany(mappedBy = "challengeInformation", cascade = CascadeType.REMOVE)
    List<ChallengeEvent> challengeEventList;

    @Builder
    public ChallengeInformation(String challengeId, String title, String description,
                                String infoContent, String imgPath, Integer point,
                                Boolean everydayChallenge) {
        this.challengeId = challengeId;
        this.title = title;
        this.description = description;
        this.infoContent = infoContent;
        this.imgPath = imgPath;
        this.point = point;
        this.everydayChallenge = everydayChallenge;
        challengeStateList = new ArrayList<>();
        challengeEventList = new ArrayList<>();
    }

    public static ChallengeInformation toEntity(ChallengeInformationDto dto) {
        return ChallengeInformation.builder()
                .challengeId(dto.getChallengeId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .infoContent(dto.getInfoContent())
                .imgPath(dto.getImgPath())
                .point(dto.getPoint())
                .everydayChallenge(dto.getEverydayChallenge())
                .build();
    }
}
