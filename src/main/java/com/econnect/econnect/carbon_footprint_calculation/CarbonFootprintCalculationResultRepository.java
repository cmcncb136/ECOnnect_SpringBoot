package com.econnect.econnect.carbon_footprint_calculation;

import com.econnect.econnect.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarbonFootprintCalculationResultRepository extends JpaRepository<CarbonFootprintCalculationResult, Integer> {
    List<CarbonFootprintCalculationResult> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
    List<CarbonFootprintCalculationResult> findAllByMemberAndDateBetween(Member member, LocalDate startDate, LocalDate endDate);
    List<CarbonFootprintCalculationResult> findAllByMember(Member member);
}
