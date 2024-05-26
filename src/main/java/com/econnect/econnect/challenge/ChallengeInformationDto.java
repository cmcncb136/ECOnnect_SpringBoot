package com.econnect.econnect.challenge;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeInformationDto {
    private String challengeId;
    private String title;
    private String description;
    private String infoContent;
    private String imgPath;
    private Integer point;
    private Boolean everydayChallenge;

    public static ChallengeInformationDto toDto(ChallengeInformation entity) {
        return ChallengeInformationDto.builder()
                .challengeId(entity.getChallengeId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .infoContent(entity.getInfoContent())
                .imgPath(entity.getImgPath())
                .point(entity.getPoint())
                .everydayChallenge(entity.getEverydayChallenge())
                .build();
    }
}
