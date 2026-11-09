package com.example.jogosapi.dto;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JogoDto (
        @NotBlank(message = "O nome do jogo é obrigatório")
        @Size(max = 50, message = "O nome pode ter no maximo 50 caracteres")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 2000, message = "A descrição deve ter no máximo 2000 caracteres")
        String descricao,

        @NotNull(message = "A quantidade é obrigatoria")
        @PositiveOrZero(message = "A quantidade não pode ser um numero negativo")
        Integer quantidade,

        @NotNull(message = "O preço é obrigatorio")
        @DecimalMin(value ="0.01", message = "O preço tem que ser superior a 0.01")
        BigDecimal preco,

        @NotNull(message = "A data do lançamento é obrigatoria")
        // Sem PastOrPresent pois a loja irá ter pre-vendas
        LocalDate dataLancamento

)
{ }
