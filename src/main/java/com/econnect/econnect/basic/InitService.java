package com.econnect.econnect.basic;

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
    @SneakyThrows//상위로 예외던짐
    private void initDB() {
        ObjectMapper objectMapper = new ObjectMapper();

        initProduct(objectMapper);
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
