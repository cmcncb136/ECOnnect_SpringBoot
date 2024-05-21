package com.econnect.econnect.carbon_footprint_calculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //생성자 자동 주입(CarbonFootprintCalculationResult)
@Service
public class CarbonFootprintCalculationResultService {
    private final CarbonFootprintCalculationResultRepository repository;

    public List<CarbonFootprintCalculationResult> getList(){
        return repository.findAll();
    }

    public CarbonFootprintCalculationResult getCarbonFootprintCalculationResult(Integer id){
        Optional<CarbonFootprintCalculationResult> carbonFootprintCalculationResult = repository.findById(id);
        if(carbonFootprintCalculationResult.isPresent()){
            return carbonFootprintCalculationResult.get();
        }else{
            return null;
        }
    }

}
