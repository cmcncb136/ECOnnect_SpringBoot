package com.econnect.econnect.challenge;

import com.econnect.econnect.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChallengeStateRepository extends JpaRepository<ChallengeState, Integer> {
    //맴버 조회
    List<ChallengeState> findByMember(Member member);

    //멤버 & 날짜 조회
    List<ChallengeState> findByMemberAndTryDate(Member member, LocalDate tryDate);

    Boolean existsByMemberAndTryDate(Member member, LocalDate tryDate);
}
