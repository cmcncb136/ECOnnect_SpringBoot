package com.econnect.econnect.challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChallengeEventRepository  extends JpaRepository<ChallengeEvent, Integer> {
    //날짜 조회
    List<ChallengeEvent> findByDate(LocalDate date);
}
