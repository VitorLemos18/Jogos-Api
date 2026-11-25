package com.example.jogosapi.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ErrorResponseDto(
        int status,
        String mensagem,
        LocalDateTime timestamp
) {
    public ErrorResponseDto(int status, String mensagem) {
        this(status,mensagem,LocalDateTime.now());
    }

}
