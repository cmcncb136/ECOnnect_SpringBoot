package com.econnect.econnect.challenge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckState {
    @Id
    private Integer completeState;

    @Column(length = 255)
    private String result;

    @Builder
    public CheckState(Integer completeState, String result) {
        this.completeState = completeState;
        this.result = result;
    }
}
