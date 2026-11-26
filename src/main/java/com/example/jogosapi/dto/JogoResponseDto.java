package com.example.jogosapi.dto;


import com.example.jogosapi.database.models.Jogo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Dados retornados de um jogo")
public record JogoResponseDto(
        @Schema(description = "ID único do jogo")
        UUID id,
        @Schema(description = "Título do jogo", example = "Elden Ring")
        String titulo,
        @Schema(description = "Descrição do jogo")
        String descricao,
        @Schema(description = "Quantidade em estoque", example = "50")
        Integer quantidade,
        @Schema(description = "Preço atual", example = "249.90")
        BigDecimal preco,
        @Schema(description = "Data de lançamento", example = "2022-02-25")
        LocalDate dataLancamento,
        @Schema(description = "Estúdio responsável pelo jogo")
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
