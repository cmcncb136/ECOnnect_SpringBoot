package com.econnect.econnect.challenge;


import com.econnect.econnect.admin.ChallengeStateFullAdminDto;
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

    private Boolean memberCheck;

    @ManyToOne
    private Member member;

    @ManyToOne
    private CheckState checkState;

    @ManyToOne
    private ChallengeInformation challengeInformation;

    @Builder
    public ChallengeState(LocalDate tryDate, String content, String imagePath, LocalDate checkDate, Member member,
                          CheckState checkState, ChallengeInformation challengeInformation, LocalTime tryTime, Boolean memberCheck) {
        this.tryDate = tryDate;
        this.content = content;
        this.imagePath = imagePath;
        this.checkDate = checkDate;
        this.member = member;
        this.checkState = checkState;
        this.challengeInformation = challengeInformation;
        this.tryTime = tryTime;
        this.memberCheck = memberCheck;
    }


    public static ChallengeState toEntity(ChallengeStateDto dto, Member member, ChallengeInformation challengeInformation){
        return ChallengeState.builder()
                .tryDate(LocalDate.parse(dto.getTryDate()))
                .content(dto.getContent())
                .imagePath(dto.getImagePath())
                .checkDate(LocalDate.parse(dto.getCheckDate()))
                .tryTime(LocalTime.parse(dto.getTryTime()))
                .member(member)
                .memberCheck(dto.getMemberCheck())
                .checkState(
                        CheckState.toEntity(dto.getCheckState()))
                .challengeInformation(challengeInformation)
                .build();
    }

    public static ChallengeState toEntity(ChallengeStateFullDto dto, Member member){
        return ChallengeState.builder()
                .tryDate(LocalDate.parse(dto.getTryDate()))
                .content(dto.getContent())
                .imagePath(dto.getImagePath())
                .memberCheck(dto.getMemberCheck())
                .checkDate(LocalDate.parse(dto.getCheckDate()))
                .tryTime(LocalTime.parse(dto.getTryTime()))
                .member(member)
                .checkState(
                        CheckState.toEntity(dto.getCheckState()))
                .challengeInformation(
                        ChallengeInformation.toEntity(dto.getChallengeInformation())
                )
                .build();
    }

    public static ChallengeState toEntity(ChallengeStateFullAdminDto dto){
        return ChallengeState.builder()
                .tryDate(LocalDate.parse(dto.getTryDate()))
                .content(dto.getContent())
                .imagePath(dto.getImagePath())
                .memberCheck(dto.getMemberCheck())
                .checkDate(LocalDate.parse(dto.getCheckDate()))
                .tryTime(LocalTime.parse(dto.getTryTime()))
                .member(
                        Member.toEntity(dto.getMember())
                )
                .checkState(
                        CheckState.toEntity(dto.getCheckState()))
                .challengeInformation(
                        ChallengeInformation.toEntity(dto.getChallengeInformation())
                )
                .build();
    }
}
