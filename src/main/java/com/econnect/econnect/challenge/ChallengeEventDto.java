package com.econnect.econnect.challenge;

import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeEventDto {
    private Integer id;
    private ChallengeInformationDto challengeInformation;
    private LocalDate date;

    public static ChallengeEventDto toDto(ChallengeEvent entity) {
        return ChallengeEventDto.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .challengeInformation(ChallengeInformationDto.toDto(entity.getChallengeInformation()))
                .build();
    }
}
