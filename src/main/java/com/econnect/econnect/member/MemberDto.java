package com.econnect.econnect.member;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String uid;
    private String name;
    private String phone;
    private String address;
    private LocalDate joinDate;
    private int point;

    public static MemberDto toDto(Member entity){
        if(entity == null) return null;

        return MemberDto.builder()
                .uid(entity.getUid())
                .name(entity.getName())
                .address(entity.getAddress())
                .joinDate(entity.getJoinDate())
                .point(entity.getPoint())
                .phone(entity.getPhone())
                .build();
    }
}
