package com.example.jogosapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados para cadastro de um novo usuário")
public record UsuarioDto(

        @Schema(description = "Nome completo do usuário", example = "Vitor Lemos")
        @NotBlank(message = "Nome do usuario é obrigatorio")
        String nome,

        @Schema(description = "Email único do usuário", example = "vitor@email.com")
        @NotBlank(message = "O email do usuario é obrigatorio")
        @Email(message = "Formato de E-mail inválido ")
        String email,

        @Schema(description = "Senha de acesso (mínimo 6 caracteres)", example = "123456")
        @NotBlank(message = "A Senha é obrigatoria")
        @Size(min = 6, message = "A senha deve ter 6 caracteres ou mais")
        String senha,

        @Schema(description = "CPF válido (apenas números)", example = "11144477735")
        @NotBlank(message = "O Cpf é obrigatorio")
        @org.hibernate.validator.constraints.br.CPF(message = "CPF invalido")
        String cpf,

        @Schema(description = "Data de nascimento no passado", example = "2000-05-15")
        @NotNull(message = "Data de nascimento é obrigatoria")
        @Past(message = "A data de nascimento dever uma data passada ")
        LocalDate dataNascimento


) {
}
