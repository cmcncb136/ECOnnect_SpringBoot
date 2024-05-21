package com.econnect.econnect.challenge;


import com.econnect.econnect.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public ChallengeState(Integer challengeStateId, LocalDate tryDate, String content, String imagePath, LocalDate checkDate, Member member,
                          CheckState checkState, ChallengeState challengeState) {
        this.challengeStateId = challengeStateId;
        this.tryDate = tryDate;
        this.content = content;
        this.imagePath = imagePath;
        this.checkDate = checkDate;
        this.member = member;
        this.checkState = checkState;
        this.challengeState = challengeState;
    }
}
