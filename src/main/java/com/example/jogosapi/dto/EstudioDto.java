package com.example.jogosapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EstudioDto(

        @NotNull(message = "O nome do Estudio é obrigatorio")
        String nome,

        @NotNull(message = "A descrição do Estudio é obrigatoria")
        @Size(max = 200, message = "A descrição pode ter até 200 caracteres")
        String descricao,

        @NotNull(message = "A data da criação do Estudio é obrigatoria")
        @PastOrPresent(message = "A data da criação do Estudio não pode ser futura ")
        LocalDate dataLancamento


) {
}
