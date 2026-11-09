package com.example.jogosapi.dto;


import com.example.jogosapi.database.models.Estudio;

import java.time.LocalDate;
import java.util.UUID;

public record EstudioResponseDto(
        UUID id,
        String nome,
        String descricao,
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
