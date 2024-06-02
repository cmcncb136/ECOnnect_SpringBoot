package com.econnect.econnect.challenge;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge_information")
public class ChallengeInformationController {
    private final ChallengeInformationService challengeInformationService;

    @GetMapping("/findAll/")
    public List<ChallengeInformationDto> findAll() {
        ArrayList<ChallengeInformationDto> challengeInformationDtos = new ArrayList<>();
        for(ChallengeInformation info : challengeInformationService.getAllChallengeInformation())
            challengeInformationDtos.add(ChallengeInformationDto.toDto(info));

        return challengeInformationDtos;
    }

    @GetMapping("/find/")
    public ChallengeInformationDto find(@RequestParam String challengeId) {
        return ChallengeInformationDto.toDto(
                        challengeInformationService.getChallengeInformationById(challengeId));
    }

    @GetMapping("/find_everyday_challenge/")
    public List<ChallengeInformationDto> findEverydayChallenge() {
        ArrayList<ChallengeInformationDto> challengeInformationDtos = new ArrayList<>();
        for(ChallengeInformation info : challengeInformationService.getEverydayChallengeInformation())
            challengeInformationDtos.add(ChallengeInformationDto.toDto(info));

        return challengeInformationDtos;
    }

    @GetMapping("/findByChallengeIds/")
    public List<ChallengeInformationDto> findByChallengeIds(@RequestParam List<String> challengeIds) {
        ArrayList<ChallengeInformationDto> challengeInformations = new ArrayList<>();
        for (String challengeId : challengeIds)
            challengeInformations.add(
                    ChallengeInformationDto.toDto(
                            challengeInformationService.getChallengeInformationById(challengeId)));
        return challengeInformations;
    }
}
