package com.econnect.econnect.carbon_footprint_calculation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarbonFootprintRankRepository extends JpaRepository<CarbonFootprintRank, Integer> {
}
