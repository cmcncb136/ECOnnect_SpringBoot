package com.econnect.econnect.challenge;

import com.econnect.econnect.member.Member;
import com.econnect.econnect.member.MemberRepository;
import com.econnect.econnect.member.MemberService;
import com.econnect.econnect.result.CommonResult;
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
    private final MemberRepository memberRepository;

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

    public List<ChallengeStateFullDto> findByUidAndCheckState(String uid, Integer checkStateId) {
        CheckState checkState = checkStateRepository.findById(checkStateId).orElse(null);
        Member member = memberRepository.findById(uid).orElse(null);
        if(checkState == null || member == null) return null;

        List<ChallengeState> challengeStates =  challengeStateRepository.findByMemberAndCheckState(member, checkState);
        List<ChallengeStateFullDto> dtos = new ArrayList<>();
        for(ChallengeState challengeState : challengeStates)
            dtos.add(ChallengeStateFullDto.toFullDto(
                    challengeState
            ));

        return dtos;
    }

    //점검중이거나 아직 유저가 확인 안 한 challengeState 출력
    public List<ChallengeStateFullDto> findByMemberAndNotMemberCheck(String uid){
        Member member = memberRepository.findById(uid).orElse(null);
        if(member == null) return null;

        List<ChallengeStateFullDto> rst = new ArrayList<>();

        rst.addAll(findByUidAndCheckStateAndMemberCheck(uid, CheckState.SUCCEEDED));
        rst.addAll(findByUidAndCheckStateAndMemberCheck(uid, CheckState.FAIL));
        rst.addAll(findByUidAndCheckStateAndMemberCheck(uid, CheckState.TRY));
        return rst;
    }

    //사용자가 확인하지 않은 challenge를 조회
    public List<ChallengeStateFullDto> findByUidAndCheckStateAndMemberCheck(String uid, Integer checkStateId) {
        return findByUidAndCheckStateAndMemberCheck(uid, checkStateId, false);
    }

    public List<ChallengeStateFullDto> findByUidAndCheckStateAndMemberCheck(String uid, Integer checkStateId, Boolean memberCheck) {
        CheckState checkState = checkStateRepository.findById(checkStateId).orElse(null);
        Member member = memberRepository.findById(uid).orElse(null);
        if(checkState == null || member == null) return null;

        List<ChallengeState> challengeStates =  challengeStateRepository.findByMemberAndCheckStateAndMemberCheck(member, checkState, memberCheck);
        List<ChallengeStateFullDto> dtos = new ArrayList<>();
        for(ChallengeState challengeState : challengeStates)
            dtos.add(ChallengeStateFullDto.toFullDto(
                    challengeState
            ));

        return dtos;

    }

    public CommonResult memberCheck(int challengeStateId){
        int getPoint = 0;
        ChallengeState state = challengeStateRepository.findById(challengeStateId).orElse(null);
        if(state == null) return CommonResult.builder().msg("유효하지 않은 challenge state id입니다.").build();

        int completeState = state.getCheckState().getCompleteState();
        if(completeState != CheckState.FAIL && completeState != CheckState.SUCCEEDED)
            return CommonResult.builder()
                    .msg("점검중입니다.").build();

        if(completeState == CheckState.SUCCEEDED) { //성공이라면 포인트를 증가시킨다.
            Member member = state.getMember();
            getPoint = state.getChallengeInformation().getPoint();
            member.setPoint(member.getPoint() +
                   getPoint);

            memberRepository.save(member);
        }

        state.setMemberCheck(true);
        challengeStateRepository.save(state);

        return CommonResult.builder()
                .success(true)
                .msg(getPoint + "포인트를 얻었습니다.")
                .code(0).build();
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
                            .memberCheck(false)
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
