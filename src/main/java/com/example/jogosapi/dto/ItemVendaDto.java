package com.example.jogosapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

@Schema(description = "Item individual do carrinho de compras")
public record ItemVendaDto(
        @Schema(description = "ID do jogo a ser comprado")
        @NotNull(message = "O ID do jogo é obrigatório")
        UUID jogoId,

        @Schema(description = "Quantidade de cópias (mínimo 1)", example = "2")
        @NotNull(message = "A quantidade não pode ser nula")
        @Positive(message = "A quantidade deve ser maior que zero")
        Integer quantidade
) {
}