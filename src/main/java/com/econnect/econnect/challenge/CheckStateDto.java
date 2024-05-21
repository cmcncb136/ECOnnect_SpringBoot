package com.econnect.econnect.challenge;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckStateDto {
    private Integer completeState;
    private String result;

    public static CheckStateDto toDto(CheckState entity){
        return CheckStateDto.builder()
                .completeState(entity.getCompleteState())
                .result(entity.getResult())
                .build();
    }
}
