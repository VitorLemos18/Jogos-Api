package com.example.jogosapi.dto;

import com.example.jogosapi.database.models.ItemVenda;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Detalhes de um item no recibo da venda")
public record ItemVendaResponseDto(
        @Schema(description = "ID único do item da venda")
        UUID id,
        @Schema(description = "ID do jogo comprado")
        UUID jogoId,
        @Schema(description = "Título do jogo comprado", example = "Elden Ring")
        String tituloJogo,
        @Schema(description = "Quantidade comprada", example = "2")
        Integer quantidade,
        @Schema(description = "Preço unitário congelado no momento da compra", example = "249.90")
        BigDecimal precoUnitario,
        @Schema(description = "Subtotal calculado (quantidade * precoUnitario)", example = "499.80")
        BigDecimal subtotal
) {
    public ItemVendaResponseDto(ItemVenda itemVenda) {
        this(
                itemVenda.getId(),
                itemVenda.getJogo().getId(),
                itemVenda.getJogo().getTitulo(),
                itemVenda.getQuantidade(),
                itemVenda.getPreco_unitario(),
                itemVenda.getPreco_unitario().multiply(BigDecimal.valueOf(itemVenda.getQuantidade()))
        );
    }
}