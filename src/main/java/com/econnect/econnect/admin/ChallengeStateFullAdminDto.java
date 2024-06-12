package com.econnect.econnect.admin;

import com.econnect.econnect.challenge.ChallengeInformationDto;
import com.econnect.econnect.challenge.ChallengeState;
import com.econnect.econnect.challenge.ChallengeStateFullDto;
import com.econnect.econnect.challenge.CheckStateDto;
import com.econnect.econnect.member.Member;
import com.econnect.econnect.member.MemberDto;
import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeStateFullAdminDto {
    private Integer challengeStateId;
    private String tryDate;
    private String tryTime;
    private String content;
    private String imagePath;
    private String checkDate;
    private Boolean memberCheck;

    private MemberDto member;
    private CheckStateDto checkState;
    private ChallengeInformationDto challengeInformation;


    public static ChallengeStateFullAdminDto toFullAdminDto(ChallengeState entity){
        return ChallengeStateFullAdminDto.builder()
                .challengeStateId(entity.getChallengeStateId())
                .tryDate(entity.getTryDate().toString())
                .tryTime(entity.getTryTime().toString())
                .content(entity.getContent())
                .imagePath(entity.getImagePath())
                .memberCheck(entity.getMemberCheck())
                .checkDate(entity.getCheckDate() != null ? entity.getCheckDate().toString() : LocalDate.now().toString())
                .member(MemberDto.toDto(entity.getMember()))
                .checkState(CheckStateDto.toDto(entity.getCheckState()))
                .challengeInformation(
                        ChallengeInformationDto.toDto(
                                entity.getChallengeInformation()))
                .build();
    }
}
