package com.econnect.econnect.challenge;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge_information")
public class ChallengeInformationController {
    private final ChallengeInformationService challengeInformationService;

    @GetMapping("/findAll/")
    public List<ChallengeInformation> findAll() {
        return challengeInformationService.getAllChallengeInformation();
    }

    @GetMapping("/find/")
    public ChallengeInformation find(@RequestParam String challengeId) {
        return challengeInformationService.getChallengeInformationById(challengeId);
    }

    @GetMapping("/find_everyday_challenge/")
    public List<ChallengeInformation> findEverydayChallenge() {
        return challengeInformationService.getEverydayChallengeInformation();
    }
}
