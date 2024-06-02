package com.econnect.econnect.challenge;

import com.econnect.econnect.basic.FileService;
import com.econnect.econnect.member.Member;
import com.econnect.econnect.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge_state")
public class ChallengeStateController {
    private final ChallengeStateService challengeStateService;
    private final ChallengeInformationService challengeInformationService;
    private final MemberService memberService;
    private final FileService fileService;
    private final CheckStateRepository checkStateRepository;

    @PostMapping("/today/")
    public List<ChallengeStateFullDto> getTodayChallengeInformation(@RequestParam("uid") String uid) {
        Member member = memberService.getMember(uid);
        if(member == null) return null;
        //만약 오늘 챌린지가 추가되지 않았다면
        if(!challengeStateService.existTodayChallengeState(member))
            challengeStateService.addTodayChallengeState(member);//추가해 준다.


        List<ChallengeState> stateList = challengeStateService.getByMemberAndTryDate(member, LocalDate.now());
        if(stateList == null) return null;

        List<ChallengeStateFullDto> stateFullDtos = new ArrayList<>();
        for(ChallengeState state : stateList)
            stateFullDtos.add(ChallengeStateFullDto.toFullDto(state));

        return stateFullDtos;
    }


    @PostMapping("/update/")
    public boolean updateChallengeState(@RequestParam("uid") String uid,
                                        @RequestBody ChallengeStateDto challengeStateDto,
                                        @RequestPart("image") MultipartFile image) {
        if(!memberService.existsMember(uid)
        || challengeStateDto == null
        || image == null)  return false;

        //경로를 구한다. TODO. 이미지 저장 잘 되는지 확인 필요
        String path = "subscribe_state/" + uid;
        String name = "/" + challengeStateDto.getChallengeStateId()
                + image.getOriginalFilename().substring(
                        image.getOriginalFilename().lastIndexOf(".") + 1);
        try {
            //파일 저장
            fileService.uploadFile(path, name, image);
        } catch (IOException e) {
            return false;
        }

        //Dto Entity로 변환
        ChallengeState state = ChallengeState.toEntity(
                challengeStateDto,
                memberService.getMember(uid),
                challengeInformationService.getChallengeInformationById(
                        challengeStateDto.getChallengeInformationId())
        );

        state.setImagePath(path + name); //이미지 경로 저장
        state.setCheckState( //상태 정보 변경
                checkStateRepository.findById(State.TRY.getValue()).orElse(null)
        );
        state.setTryTime(LocalTime.now()); //시간 정보 최신화

        challengeStateService.updateChallengeState(state); //갱신
        return true;
    }
}
