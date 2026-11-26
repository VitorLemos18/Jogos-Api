package com.example.jogosapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Schema(description = "Dados do pedido para realizar uma compra")
public record VendaDto(
        @Schema(description = "ID do usuário comprador", example = "00000000-0000-0000-0000-000000000001")
        @NotNull(message = "O ID do usuário é obrigatório")
        UUID usuarioId,

        @Schema(description = "Lista de jogos e quantidades no carrinho")
        @NotEmpty(message = "A venda deve conter pelo menos um item")
        @Valid
        List<ItemVendaDto> itens
) {
}