package com.econnect.econnect.challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeInformationRepository extends JpaRepository<ChallengeInformation, String> {
    //일일 챌린지
    List<ChallengeInformation> findByEverydayChallenge(Boolean everydayChallenge);


}
