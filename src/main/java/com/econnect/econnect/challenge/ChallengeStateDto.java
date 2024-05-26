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
    private LocalDate tryDate;
    private LocalTime tryTime;
    private String content;
    private String imagePath;
    private LocalDate checkDate;

    private String memberId;
    private CheckStateDto checkState;
    private String challengeInformationId;

    public ChallengeStateDto toDto(ChallengeState entity){
        return ChallengeStateDto.builder()
                .challengeStateId(entity.getChallengeStateId())
                .tryDate(entity.getTryDate())
                .tryTime(entity.getTryTime())
                .content(entity.getContent())
                .imagePath(entity.getImagePath())
                .checkDate(entity.getCheckDate())
                .memberId(entity.getMember().getUid())
                .checkState(CheckStateDto.toDto(entity.getCheckState()))
                .challengeInformationId(
                        entity.getChallengeInformation().getChallengeId())
                .build();
    }

}
