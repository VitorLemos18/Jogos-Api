package com.example.jogosapi.dto;


import com.example.jogosapi.database.models.Jogo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record JogoResponseDto(
        UUID id,
        String titulo,
        String descricao,
        Integer quantidade,
        BigDecimal preco,
        LocalDate dataLancamento,
        EstudioResponseDto estudio
) {
    public JogoResponseDto(Jogo jogo){
        this(
                jogo.getId(),
                jogo.getTitulo(),
                jogo.getDescricao(),
                jogo.getQuantidade(),
                jogo.getPreco(),
                jogo.getDataLancamento(),
                new EstudioResponseDto(jogo.getEstudio())

        );
    }
}
