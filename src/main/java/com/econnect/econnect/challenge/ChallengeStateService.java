package com.econnect.econnect.challenge;

import com.econnect.econnect.member.Member;
import com.econnect.econnect.member.MemberRepository;
import com.econnect.econnect.member.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor //생성자 자동 주입
@Service
public class ChallengeStateService {
    private final ChallengeStateRepository challengeStateRepository;
    private final ChallengeInformationRepository challengeInformationRepository;
    private final ChallengeEventRepository challengeEventRepository;
    private final CheckStateRepository checkStateRepository;

    public void save(ChallengeState challengeState) {
        challengeStateRepository.save(challengeState);
    }

    public void saveAll(List<ChallengeState> challengeStates) {
        challengeStateRepository.saveAll(challengeStates);
    }

    public ChallengeState getChallengeState(Integer id) {
        return challengeStateRepository.findById(id).orElse(null);
    }

    public List<ChallengeState> getChallengeStates() {
        return challengeStateRepository.findAll();
    }

    public List<ChallengeState> getByMemberChallengeState(Member member) {
        if(member == null) return null;

        return challengeStateRepository.findByMember(member);
    }


    public Boolean existTodayChallengeState(Member member) {
        if(member == null) return null;

        return challengeStateRepository.existsByMemberAndTryDate(
                member, LocalDate.now()
        );
    }

    @Transactional
    public ChallengeState updateChallengeState(ChallengeState challengeState) {
        return challengeStateRepository.save(challengeState);
    }

    public List<ChallengeState> getByMemberAndTryDate(Member member, LocalDate tryDate) {
        return challengeStateRepository.findByMemberAndTryDate(member, tryDate);
    }


    //오늘 날짜 챌린지 정보 추가
    public void addTodayChallengeState(Member member) {
        if (member == null) return;

        //기본 챌린지를 가져온다.
        List<ChallengeInformation> challenges
                = challengeInformationRepository.findByEverydayChallenge(true);

        //오늘 이벤트 챌린지를 가져온다.
        List<ChallengeEvent> events = challengeEventRepository.findByDate(LocalDate.now());

        //ChallengeInformation만 추출한다.
        for(ChallengeEvent event : events)
            challenges.add(event.getChallengeInformation());

        //ChallengeState 생성
        List<ChallengeState> states = new ArrayList<>();
        for(ChallengeInformation info : challenges){
            states.add(
                    new ChallengeState().builder()
                            .tryDate(LocalDate.now())
                            .tryTime(LocalTime.now())
                            .content(null)
                            .challengeInformation(info)
                            .imagePath("")
                            .checkDate(null)
                            .checkState(
                                    checkStateRepository.findById(0).orElse(null)
                            ).member(member)
                            .build()
            );
        }


        //저장
        challengeStateRepository.saveAll(states);
    }
}
