package com.econnect.econnect.challenge;


import com.econnect.econnect.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class ChallengeState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer challengeStateId;

    private LocalDate tryDate;


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
    private ChallengeState challengeState;
}
