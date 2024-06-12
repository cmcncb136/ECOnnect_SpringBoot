package com.econnect.econnect.carbon_footprint_calculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //생성자 자동 주입
@Service
public class CarbonFootprintRankService {
    private final CarbonFootprintRankRepository carbonFootprintRankRepository;

    public void save(CarbonFootprintRank carbonFootprintRank) {
        carbonFootprintRankRepository.save(carbonFootprintRank);
    }

    public void saveAll(List<CarbonFootprintRank> carbonFootprintRanks) {
        carbonFootprintRankRepository.saveAll(carbonFootprintRanks);
    }

    public CarbonFootprintRank getCarbonFootprintRank(Integer id){
        return carbonFootprintRankRepository.findById(id).orElse(null);
    }

    public List<CarbonFootprintRank> getAllCarbonFootprintRank() {
        return carbonFootprintRankRepository.findAll();
    }

    public CarbonFootprintRank getCarbonFootprintRankByScore(double total) {
        List<CarbonFootprintRank> carbonFootprintRankList = carbonFootprintRankRepository.findAll();
        int i;

        for( i = carbonFootprintRankList.size() - 1; i >= 1; i--)
            if(carbonFootprintRankList.get(i).getStandard() >= total)
                break;

        return carbonFootprintRankList.get(i);
    }
}
