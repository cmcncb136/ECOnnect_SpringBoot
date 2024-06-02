package com.econnect.econnect.challenge;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @ManyToOne
    private ChallengeInformation challengeInformation;

    @Builder
    public ChallengeEvent(LocalDate date, ChallengeInformation challengeInformation) {
        this.date = date;
        this.challengeInformation = challengeInformation;
    }

    public static ChallengeEvent toEntity(ChallengeEventDto dto) {
        return ChallengeEvent.builder()
                .date(LocalDate.parse(dto.getDate()))
                .challengeInformation(ChallengeInformation.toEntity(
                        dto.getChallengeInformation()
                ))
                .build();
    }
}
