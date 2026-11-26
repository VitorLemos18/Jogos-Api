package com.example.jogosapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para autenticação de usuário")
public record LoginDto(
        @Schema(description = "Email cadastrado do usuário", example = "admin@aokrys.com")
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @Schema(description = "Senha do usuário", example = "admin123")
        @NotBlank(message = "A senha é obrigatória")
        String senha
) {
}
