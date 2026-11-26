package com.example.jogosapi.dto;


import com.example.jogosapi.database.models.Estudio;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Dados retornados de um estúdio")
public record EstudioResponseDto(
        @Schema(description = "ID único do estúdio")
        UUID id,
        @Schema(description = "Nome do estúdio", example = "FromSoftware")
        String nome,
        @Schema(description = "Descrição do estúdio", example = "Estúdio japonês conhecido por Dark Souls e Elden Ring")
        String descricao,
        @Schema(description = "Data de fundação", example = "1986-11-01")
        LocalDate dataFundacao
) {
    public EstudioResponseDto(Estudio estudio){
        this(
                estudio.getId(),
                estudio.getNome(),
                estudio.getDescricao(),
                estudio.getDataFundacao()
        );
    }
}
