package com.example.jogosapi.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UsuarioDto(

        @NotBlank(message = "Nome do usuario é obrigatorio")
        String nome,

        @NotBlank(message = "O email do usuario é obrigatorio")
        @Email(message = "Formato de E-mail inválido ")
        String email,

        @NotBlank(message = "A Senha é obrigatoria")
        @Size(min = 6, message = "A senha deve ter 6 caracteres ou mais")
        String senha,

        @NotBlank(message = "O Cpf é obrigatorio")
        @org.hibernate.validator.constraints.br.CPF(message = "CPF invalido")
        String cpf,

        @NotNull(message = "Data de nascimento é obrigatoria")
        @Past(message = "A data de nascimento dever uma data passada ")
        LocalDate dataNascimento


) {
}
