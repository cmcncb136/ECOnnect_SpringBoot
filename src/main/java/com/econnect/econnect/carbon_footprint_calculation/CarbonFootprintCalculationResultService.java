package com.econnect.econnect.carbon_footprint_calculation;

import com.econnect.econnect.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //생성자 자동 주입(CarbonFootprintCalculationResult)
@Service
public class CarbonFootprintCalculationResultService {
    private final CarbonFootprintCalculationResultRepository carbonFootprintCalculationResultRepository;

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

    //날자별 조회
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


}
