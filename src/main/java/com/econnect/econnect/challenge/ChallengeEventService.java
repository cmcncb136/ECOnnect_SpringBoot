package com.econnect.econnect.challenge;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor //생성자 자동 주입
@Service
public class ChallengeEventService {
    private final ChallengeEventRepository challengeEventRepository;

    public void save(ChallengeEvent challengeEvent) {
        challengeEventRepository.save(challengeEvent);
    }

    public void saveAll(List<ChallengeEvent> challengeEvents) {
        challengeEventRepository.saveAll(challengeEvents);
    }

    public ChallengeEvent getChallengeEvent(Integer id) {
        return challengeEventRepository.findById(id).orElse(null);
    }

    public List<ChallengeEvent> getAllChallengeEvent() {
        return challengeEventRepository.findAll();
    }

    public List<ChallengeEvent> getByDateChallengeEvent(LocalDate date) {
        return challengeEventRepository.findByDate(date);
    }
}
