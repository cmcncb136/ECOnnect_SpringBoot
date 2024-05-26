package com.econnect.econnect.challenge;


import com.econnect.econnect.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer challengeStateId;

    private LocalDate tryDate;

    private LocalTime tryTime;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 255)
    private String imagePath;

    private LocalDate checkDate;

    @ManyToOne
    private Member member;

    @ManyToOne
    private CheckState checkState;

    @ManyToOne
    private ChallengeInformation challengeInformation;

    @Builder
    public ChallengeState(LocalDate tryDate, String content, String imagePath, LocalDate checkDate, Member member,
                          CheckState checkState, ChallengeInformation challengeInformation, LocalTime tryTime) {
        this.tryDate = tryDate;
        this.content = content;
        this.imagePath = imagePath;
        this.checkDate = checkDate;
        this.member = member;
        this.checkState = checkState;
        this.challengeInformation = challengeInformation;
        this.tryTime = tryTime;
    }


    public static ChallengeState toEntity(ChallengeStateDto dto, Member member, ChallengeInformation challengeInformation){
        return ChallengeState.builder()
                .tryDate(dto.getTryDate())
                .content(dto.getContent())
                .imagePath(dto.getImagePath())
                .checkDate(dto.getCheckDate())
                .tryTime(dto.getTryTime())
                .member(member)
                .checkState(
                        CheckState.toEntity(dto.getCheckState()))
                .challengeInformation(challengeInformation)
                .build();
    }
}
