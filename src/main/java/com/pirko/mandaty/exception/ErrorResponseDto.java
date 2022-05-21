package com.pirko.mandaty.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {

    private String message;

    public static ErrorResponseDto fromException(RuntimeException exception) {
        return ErrorResponseDto.builder()
                .message(exception.getMessage())
                .build();
    }

}
