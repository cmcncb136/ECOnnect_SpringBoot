package com.econnect.econnect.product;

import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String categoryId;
    private String  name;

    public static CategoryDto toDto(Category entity){
        return CategoryDto.builder()
                .categoryId(entity.getCategoryId())
                .name(entity.getName())
                .build();
    }
}
