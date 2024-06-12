package com.econnect.econnect.challenge;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeStateDto {
    private Integer challengeStateId;
    private String tryDate;
    private String tryTime;
    private String content;
    private String imagePath;
    private String checkDate;
    private Boolean memberCheck;

    private String memberId;
    private CheckStateDto checkState;
    private String challengeInformationId;

    public ChallengeStateDto toDto(ChallengeState entity){
        return ChallengeStateDto.builder()
                .challengeStateId(entity.getChallengeStateId())
                .tryDate(entity.getTryDate().toString())
                .tryTime(entity.getTryTime().toString())
                .content(entity.getContent())
                .imagePath(entity.getImagePath())
                .memberCheck(entity.getMemberCheck())
                .checkDate(entity.getCheckDate().toString())
                .memberId(entity.getMember().getUid())
                .checkState(CheckStateDto.toDto(entity.getCheckState()))
                .challengeInformationId(
                        entity.getChallengeInformation().getChallengeId())
                .build();
    }

}
