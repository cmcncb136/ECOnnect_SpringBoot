package com.econnect.econnect.challenge;

import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeEventRequestDto {
    private Integer id;
    private String date;
    private String challengeInformationId;
}
