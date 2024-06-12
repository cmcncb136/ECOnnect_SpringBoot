package com.econnect.econnect.admin;

import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private String uid;

    public static AdminDto toDto(Admin admin) {
        return AdminDto.builder()
                .uid(admin.getUid())
                .build();
    }
}

