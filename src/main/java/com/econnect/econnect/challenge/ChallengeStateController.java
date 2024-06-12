package com.econnect.econnect.challenge;

import com.econnect.econnect.basic.FTPService;
import com.econnect.econnect.member.Member;
import com.econnect.econnect.member.MemberService;
import com.econnect.econnect.result.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
   private final FTPService ftpService;
    private final CheckStateRepository checkStateRepository;
    private final ChallengeStateRepository challengeStateRepository;

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

    @PostMapping("/memberCheck/")
    public CommonResult memberCheck(@RequestParam("challengeStateId") Integer challengeSateId) {
        return challengeStateService.memberCheck(challengeSateId);
    }

    @PostMapping("/checking/") //점검중이거나 아직 유저가 확인 안 한것
    public List<ChallengeStateFullDto> checking(@RequestParam("uid") String uid) {
        return challengeStateService.findByMemberAndNotMemberCheck(uid);
    }



    @PostMapping("/try/")
    public boolean updateChallengeState(@RequestParam("uid") String uid,
                                        @RequestParam("challengeStateId") Integer challengeStateId,
                                        @RequestPart("image") MultipartFile image) {
        if(!memberService.existsMember(uid) || image == null)  return false;
//        try {
//            image.transferTo(new File("C:\\Users\\KJW\\Documents\\PicPick\\buffer\\" + image.getOriginalFilename()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        ChallengeState state = challengeStateRepository.findById(challengeStateId).orElse(null);
        if(state == null) return false;


        //경로르 구한다.
        String name = challengeStateId + "."
                + image.getOriginalFilename().substring(
                        image.getOriginalFilename().lastIndexOf(".") + 1);

        System.out.println("Path : " + name);


        //파일 저장
        if(!ftpService.uploadFileToFTP(image, uid, name))
            return false;


        state.setImagePath("img/challenge_state/" + uid + "/" + name); //이미지 경로 저장
        state.setCheckState( //상태 정보 변경
                checkStateRepository.findById(CheckState.TRY).orElse(null)
        );

        state.setTryTime(LocalTime.now()); //시간 정보 최신화

        challengeStateService.updateChallengeState(state); //갱신
        return true;
    }
}
