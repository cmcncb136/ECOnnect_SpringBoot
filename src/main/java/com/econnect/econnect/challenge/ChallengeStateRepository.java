package com.econnect.econnect.challenge;

import com.econnect.econnect.member.Member;
import org.hibernate.annotations.Check;
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

    //특정 날에 데이터(컬럼)이 존재하는 판단
    Boolean existsByMemberAndTryDate(Member member, LocalDate tryDate);

    //CheckState가 점검중인결 가져온다.
    List<ChallengeState> findByMemberAndCheckState(Member member, CheckState state);

    //CheckState가 memberCheck로 분류 --> 사용자가 확인했는지 여부
    List<ChallengeState> findByMemberAndCheckStateAndMemberCheck(Member member, CheckState state, Boolean memberCheck);

    //CheckState가 Try인것을 모두 가져온다.
    List<ChallengeState> findByCheckState(CheckState state);

}
