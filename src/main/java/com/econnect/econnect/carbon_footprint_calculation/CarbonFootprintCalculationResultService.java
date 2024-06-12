package com.econnect.econnect.carbon_footprint_calculation;

import com.econnect.econnect.member.Member;
import com.econnect.econnect.member.MemberRepository;
import com.econnect.econnect.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //생성자 자동 주입(CarbonFootprintCalculationResult)
@Service
public class CarbonFootprintCalculationResultService {
    private final CarbonFootprintCalculationResultRepository carbonFootprintCalculationResultRepository;
    private final CarbonFootprintRankService carbonFootprintRankService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public List<CarbonFootprintCalculationResultDto> entityListToDtoList(List<CarbonFootprintCalculationResult> entityList){
        if(entityList == null) return null;
        List<CarbonFootprintCalculationResultDto> dtoList = new ArrayList<>();
        for (CarbonFootprintCalculationResult entity : entityList)
            dtoList.add(CarbonFootprintCalculationResultDto.toDto(entity));

        return dtoList;
    }

    public void save(CarbonFootprintCalculationResult carbonFootprintCalculationResult) {
        carbonFootprintCalculationResultRepository.save(carbonFootprintCalculationResult);
    }

    public List<CarbonFootprintCalculationResult> getListByMember(Member member){
        return carbonFootprintCalculationResultRepository.findAllByMember(member);
    }

    public List<CarbonFootprintCalculationResult> getList(){
        return carbonFootprintCalculationResultRepository.findAll();
    }

    public CarbonFootprintCalculationResult getCarbonFootprintCalculationResult(Integer id){
        Optional<CarbonFootprintCalculationResult> carbonFootprintCalculationResult = carbonFootprintCalculationResultRepository.findById(id);
        if(carbonFootprintCalculationResult.isPresent()){
            return carbonFootprintCalculationResult.get();
        }else{
            return null;
        }
    }

    //년도별 조회
    public List<CarbonFootprintCalculationResult> getListByYear(String uid, LocalDate year) throws ParseException {
        Member member = memberRepository.findById(uid).orElse(null);
        if(member == null) return null;

        List<CarbonFootprintCalculationResult> rst, tmp;
        rst = new ArrayList<>();

        for(int i = 1; i <= year.getMonthValue(); i++){
            tmp = getListByMemberAndMonth(member, year.withMonth(i));
            if(tmp != null && !tmp.isEmpty())
                rst.add(tmp.get(0));
        }

        return rst;
    }

    //https://recordsoflife.tistory.com/836
    public List<CarbonFootprintCalculationResult> getListByMemberAndMonth(Member member, LocalDate localDate) throws ParseException {
        return getListByMemberAndMonth(member, localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    public List<CarbonFootprintCalculationResult> getListByMemberAndMonth(Member member, String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        //1일과 말일을 저장할 변수를 선언
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();

        startCal.setTime(formatter.parse(date));
        endCal.setTime(formatter.parse(date));


        startCal.set(Calendar.DAY_OF_MONTH, 1);
        endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("start : " + dateFormat.format(startCal.getTime()));
        System.out.println("end : " + dateFormat.format(endCal.getTime()));

        return getPostListByMember(member,
                startCal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endCal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    public List<CarbonFootprintCalculationResult> getPostList(String startDate, String endDate){
        if(startDate.trim().equals(""))
            startDate = "00010101";
        if(endDate.trim().equals(""))
            endDate = "99991231";

        return getPostList(
                LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd")),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    public List<CarbonFootprintCalculationResult> getPostList(LocalDate startDate, LocalDate endDate){
        return carbonFootprintCalculationResultRepository.findAllByDateBetween(startDate, endDate);
    }



    public List<CarbonFootprintCalculationResult> getPostListByMember(Member member, String startDate, String endDate){
        if(startDate.trim().equals(""))
            startDate = "00010101";
        if(endDate.trim().equals(""))
            endDate = "99991231";

        return getPostListByMember(
                member,
                LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd")),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    public List<CarbonFootprintCalculationResult> getPostListByMember(Member member, LocalDate startDate, LocalDate endDate){
        return carbonFootprintCalculationResultRepository.findAllByMemberAndDateBetween(
            member, startDate, endDate
        );
    }

    public CarbonFootprintCalculationResult calculation(CarbonFootprintCalculationResultDto dto){
        CarbonFootprintCalculationResult result =
                CarbonFootprintCalculationResult.toEntity(dto, memberService.getMember(dto.getMemberId()));

        result.setTotal(dto.getGasScore()
                + dto.getWaterScore()
                + dto.getElectricityScore());

        result.setCarbonFootprintRank(
                carbonFootprintRankService.getCarbonFootprintRankByScore(result.getTotal())
        );

        return result;
    }



}
