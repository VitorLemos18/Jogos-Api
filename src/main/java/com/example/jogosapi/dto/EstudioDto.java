package com.example.jogosapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(description = "Dados para cadastro ou atualização de um estúdio")
public record EstudioDto(

        @Schema(description = "Nome do estúdio", example = "FromSoftware")
        @NotNull(message = "O nome do Estudio é obrigatorio")
        String nome,

        @Schema(description = "Descrição do estúdio", example = "Estúdio japonês conhecido por Dark Souls e Elden Ring")
        @NotNull(message = "A descrição do Estudio é obrigatoria")
        @Size(max = 200, message = "A descrição pode ter até 200 caracteres")
        String descricao,

        @Schema(description = "Data de fundação do estúdio", example = "1986-11-01")
        @NotNull(message = "A data da criação do Estudio é obrigatoria")
        @PastOrPresent(message = "A data da criação do Estudio não pode ser futura ")
        LocalDate dataFundacao


) {
}
