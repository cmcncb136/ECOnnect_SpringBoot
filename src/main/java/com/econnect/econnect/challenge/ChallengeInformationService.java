package com.econnect.econnect.challenge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //생성자 자동 주입
@Service
public class ChallengeInformationService {
    private final ChallengeInformationRepository challengeInformationRepository;

    public void save(ChallengeInformation challengeInformation) {
        challengeInformationRepository.save(challengeInformation);
    }

    public void saveAll(List<ChallengeInformation> challengeInformationList) {
        challengeInformationRepository.saveAll(challengeInformationList);
    }

    public ChallengeInformation getChallengeInformationById(String id) {
        return challengeInformationRepository.findById(id).orElse(null);
    }

    public List<ChallengeInformation> getAllChallengeInformation() {
        return challengeInformationRepository.findAll();
    }

    //일일 챌린지 가져오기
    public List<ChallengeInformation> getEverydayChallengeInformation() {
        return challengeInformationRepository.findByEverydayChallenge(true);
    }
}
