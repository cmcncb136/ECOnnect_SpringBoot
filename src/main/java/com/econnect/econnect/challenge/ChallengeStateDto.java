package com.econnect.econnect.challenge;

import com.econnect.econnect.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeStateDto {
    private Integer challengeStateId;
    private LocalDate tryDate;
    private String content;
    private String imagePath;
    private LocalDate checkDate;

    private String memberId;
    private CheckState checkState;
    private ChallengeState challengeState;

    public ChallengeStateDto toDto(ChallengeState entity){
        return ChallengeStateDto.builder()
                .challengeStateId(entity.getChallengeStateId())
                .tryDate(entity.getTryDate())
                .content(entity.getContent())
                .imagePath(entity.getImagePath())
                .checkDate(entity.getCheckDate())
                .memberId(entity.getMember().getUid())
                .checkState(entity.getCheckState())
                .challengeState(entity.getChallengeState())
                .build();
    }

}
