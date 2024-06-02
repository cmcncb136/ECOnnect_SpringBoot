package com.econnect.econnect.challenge;

import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeStateFullDto {
    private Integer challengeStateId;
    private String tryDate;
    private String tryTime;
    private String content;
    private String imagePath;
    private String checkDate;

    private String memberId;
    private CheckStateDto checkState;
    private ChallengeInformationDto challengeInformation;

    public static ChallengeStateFullDto toFullDto(ChallengeState entity){
        return ChallengeStateFullDto.builder()
                .challengeStateId(entity.getChallengeStateId())
                .tryDate(entity.getTryDate().toString())
                .tryTime(entity.getTryTime().toString())
                .content(entity.getContent())
                .imagePath(entity.getImagePath())
                .checkDate(entity.getCheckDate() != null ? entity.getCheckDate().toString() : LocalDate.now().toString())
                .memberId(entity.getMember().getUid())
                .checkState(CheckStateDto.toDto(entity.getCheckState()))
                .challengeInformation(
                        ChallengeInformationDto.toDto(
                        entity.getChallengeInformation()))
                .build();
    }

}
