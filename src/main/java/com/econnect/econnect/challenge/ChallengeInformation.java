package com.econnect.econnect.challenge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeInformation {
    @Id
    private String challengeId;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String infoContent;

    @Column(length = 255)
    private String imgPath;

    private Integer point;

    @Builder
    public ChallengeInformation(String challengeId, String title, String description,
                                String infoContent, String imgPath, Integer point) {
        this.challengeId = challengeId;
        this.title = title;
        this.description = description;
        this.infoContent = infoContent;
        this.imgPath = imgPath;
        this.point = point;
    }
}
