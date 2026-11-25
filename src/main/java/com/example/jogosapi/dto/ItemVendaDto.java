package com.example.jogosapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ItemVendaDto(
        @NotNull(message = "O ID do jogo é obrigatório")
        UUID jogoId,

        @NotNull(message = "A quantidade não pode ser nula")
        @Positive(message = "A quantidade deve ser maior que zero")
        Integer quantidade
) {
}