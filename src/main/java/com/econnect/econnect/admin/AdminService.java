package com.econnect.econnect.admin;

import com.econnect.econnect.challenge.ChallengeState;
import com.econnect.econnect.challenge.ChallengeStateRepository;
import com.econnect.econnect.challenge.CheckState;
import com.econnect.econnect.challenge.CheckStateRepository;
import com.econnect.econnect.result.CommonResult;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final ChallengeStateRepository challengeStateRepository;
    private final CheckStateRepository checkStateRepository;

    public boolean existAdmin(String uid) {
        return adminRepository.existsById(uid);
    }

    public List<ChallengeStateFullAdminDto> findTryMemberChallengeState() {
        //Try 객체를 가져온다.
        CheckState checkStateTry = checkStateRepository.findById(CheckState.TRY).orElse(null);
        if(checkStateTry == null) return null;

        //현재 try인 정보를 모두 가져온다.
        List<ChallengeState> challengeStates = challengeStateRepository.findByCheckState(checkStateTry);

        //dto로 변환해 반환한다.
        return entityToFullAdminDto(challengeStates);
    }

    public List<ChallengeStateFullAdminDto> entityToFullAdminDto(List<ChallengeState> challengeStates) {
        List<ChallengeStateFullAdminDto> dtos = new ArrayList<>();
        for (ChallengeState challengeState : challengeStates)
            dtos.add(ChallengeStateFullAdminDto.toFullAdminDto(challengeState));

        return dtos;
    }

    public CommonResult adminCheck(int challengeStateId, int checkId){
        if(checkId != CheckState.SUCCEEDED && checkId != CheckState.FAIL)
            return CommonResult.builder()
                    .msg("유효하지 않은 확인 번호입니다.")
                    .build();

        ChallengeState challengeState = challengeStateRepository.findById(challengeStateId).orElse(null);
        CheckState checkState = checkStateRepository.findById(checkId).orElse(null);

        if(challengeState == null)
            return CommonResult.builder()
                    .msg("유효하지 않는 챌린지 번호입니다.")
                    .build();

        challengeState.setCheckState(checkState);
        challengeState.setCheckDate(LocalDate.now());

        challengeStateRepository.save(challengeState);

        return CommonResult.builder()
                .msg("성공 여부를 저장하는데 성공했습니다.")
                .code(1)
                .success(true).build();
    }
}
