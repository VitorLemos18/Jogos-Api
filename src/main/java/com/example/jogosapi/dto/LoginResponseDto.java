package com.example.jogosapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta da autenticação contendo o Token JWT")
public record LoginResponseDto(
        @Schema(description = "Token JWT para autenticação Bearer", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token
) {
}
