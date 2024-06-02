package com.econnect.econnect.carbon_footprint_calculation;


import com.econnect.econnect.member.Member;
import com.econnect.econnect.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.loader.ast.spi.Loadable;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carbon_result")
public class CarbonFootprintCalculationResultController {
    private final CarbonFootprintCalculationResultService carbonFootprintCalculationResultService;
    private final MemberService memberService;

    //이번달 했는지 체크
    @PostMapping("/check/")
    public CarbonFootprintCalculationResultDto monthCarbonFootprintCalculationResult(@RequestParam("uid") String uid) {
        List<CarbonFootprintCalculationResult> list;
        try {
            list = carbonFootprintCalculationResultService.getListByMemberAndMonth(
                    memberService.getMember(uid),
                    LocalDate.now());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return  list != null && !list.isEmpty() ?
                CarbonFootprintCalculationResultDto.toDto(list.get(0)) : null;
    }

    //추가하는 경우
    @PostMapping("/add/")
    public boolean addCarbonFootprintCalculationResult(@RequestBody CarbonFootprintCalculationResultDto dto) {
        CarbonFootprintCalculationResult carbonFootprintCalculationResult = CarbonFootprintCalculationResult.toEntity(
                dto, memberService.getMember(dto.getMemberId()));

        //이번 달에 이미 데이터가 있다면
        carbonFootprintCalculationResult.setDate(LocalDate.now());
        if(monthCarbonFootprintCalculationResult(dto.getMemberId()) != null) return false;

        carbonFootprintCalculationResultService.save(carbonFootprintCalculationResult);
        return true;
    }

    //전체조회
    @PostMapping("/findByUid/")
    public List<CarbonFootprintCalculationResultDto> finByUidCarbonFootprintCalculationResult(@RequestParam("uid") String uid) {
        Member member = memberService.getMember(uid); //조회한 사용자가 있는지 확인
        if(member == null) return null;

        List<CarbonFootprintCalculationResult> list = carbonFootprintCalculationResultService.getListByMember(member);
        if(list == null) return null;

        List<CarbonFootprintCalculationResultDto> dtos = new ArrayList<>();

        for(CarbonFootprintCalculationResult rst : list){
            dtos.add(CarbonFootprintCalculationResultDto.toDto(rst));
        }

        return dtos;
    }
}
