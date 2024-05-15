package com.econnect.econnect.challenge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CheckState {
    @Id
    private int completeState;

    @Column(length = 255)
    private String result;
}
