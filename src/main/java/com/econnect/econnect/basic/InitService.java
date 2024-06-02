package com.econnect.econnect.basic;

import com.econnect.econnect.challenge.*;
import com.econnect.econnect.product.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class InitService implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductConditionRepository productConditionRepository;
    private final ProductRepository productRepository;
    private final CheckStateRepository checkStateRepository;
    private final ChallengeInformationRepository challengeInformationRepository;

    private void initProduct(ObjectMapper objectMapper) throws JsonProcessingException {
        ArrayList<CategoryDto> categories
                = objectMapper.readValue(
                resourceJsonToString("temp_data/category.json"),
                new TypeReference<ArrayList<CategoryDto>>(){}
        );

        ArrayList<ProductConditionDto> conditions
                = objectMapper.readValue(
                resourceJsonToString("temp_data/product_condition.json"),
                new TypeReference<ArrayList<ProductConditionDto>>(){}
        );

        ArrayList<ProductDto> products
                = objectMapper.readValue(
                resourceJsonToString("temp_data/product.json"),
                new TypeReference<ArrayList<ProductDto>>(){}
        );

        //아래 있는 구문은 조금 코스트가 있는 편이지만 어쩔수 없지...
        for(CategoryDto category : categories)
            if(!categoryRepository.existsById(category.getCategoryId()))
                categoryRepository.save(
                        Category.toEntity(category)
                );

        for(ProductConditionDto condition : conditions)
            if(!productConditionRepository.existsById(condition.getProductConditionId()))
                productConditionRepository.save(
                        ProductCondition.toEntity(condition)
                );

        for(ProductDto product : products)
            if(!productRepository.existsById(product.getProductId())) {
                Product p = Product.toEntity(product);
                p.setRegisterDate(LocalDate.now());
                productRepository.save(p);
            }
    }

    private void initChallenge(ObjectMapper objectMapper) throws JsonProcessingException {
        ArrayList<CheckStateDto> checkStates
                = objectMapper.readValue(
                resourceJsonToString("temp_data/check_state.json"),
                new TypeReference<ArrayList<CheckStateDto>>(){}
        );

        ArrayList<ChallengeInformationDto> challengeInformationDtos
                = objectMapper.readValue(
                resourceJsonToString("temp_data/challenge_information.json"),
                new TypeReference<ArrayList<ChallengeInformationDto>>(){}
        );

        for(CheckStateDto checkState : checkStates){
            if(!checkStateRepository.existsById(checkState.getCompleteState())){
                CheckState c = CheckState.toEntity(checkState);
                checkStateRepository.save(c);
            }
        }

        for(ChallengeInformationDto challengeInformationDto : challengeInformationDtos){
            if(!challengeInformationRepository.existsById(challengeInformationDto.getChallengeId())){
                ChallengeInformation ci = ChallengeInformation.toEntity(challengeInformationDto);
                challengeInformationRepository.save(ci);
            }
        }
    }

    @SneakyThrows//상위로 예외던짐
    private void initDB() {
        ObjectMapper objectMapper = new ObjectMapper();
        initProduct(objectMapper);
        initChallenge(objectMapper);
    }

    @SneakyThrows//상위로 예외던짐
    public static String resourceJsonToString(String path){
        String str = "";
        String sb = "";
        ClassPathResource resource = null;
        InputStreamReader reader = null;

        //static폴더의 json파일을 string으로 저장
        try {
            resource = new ClassPathResource("static/" + path);
            reader = new InputStreamReader(resource.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            while ((str = br.readLine()) != null) {
                sb += str + "\n";
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return sb;
    }

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        initDB();
    }
}
