package com.econnect.econnect.admin;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {
    @Id
    private String uid;

    @Builder
    public Admin(String uid) {
        this.uid = uid;
    }

    public static Admin toEntity(AdminDto dto){
        return Admin.builder()
                .uid(dto.getUid())
                .build();
    }
}

