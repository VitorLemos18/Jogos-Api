package com.example.jogosapi.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Dados para cadastro ou atualização de um jogo")
public record JogoDto (
        @Schema(description = "Título do jogo", example = "Elden Ring")
        @NotBlank(message = "O nome do jogo é obrigatório")
        @Size(max = 50, message = "O nome pode ter no maximo 50 caracteres")
        String titulo,

        @Schema(description = "Descrição detalhada do jogo", example = "RPG de ação em mundo aberto dirigido por Hidetaka Miyazaki")
        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 2000, message = "A descrição deve ter no máximo 2000 caracteres")
        String descricao,

        @Schema(description = "Quantidade disponível em estoque", example = "50")
        @NotNull(message = "A quantidade é obrigatoria")
        @PositiveOrZero(message = "A quantidade não pode ser um numero negativo")
        Integer quantidade,

        @Schema(description = "Preço atual do jogo", example = "249.90")
        @NotNull(message = "O preço é obrigatorio")
        @DecimalMin(value ="0.01", message = "O preço tem que ser superior a 0.01")
        BigDecimal preco,

        @Schema(description = "Data de lançamento do jogo", example = "2022-02-25")
        @NotNull(message = "A data do lançamento é obrigatoria")
        // Sem PastOrPresent pois a loja irá ter pre-vendas
        LocalDate dataLancamento

)
{ }
