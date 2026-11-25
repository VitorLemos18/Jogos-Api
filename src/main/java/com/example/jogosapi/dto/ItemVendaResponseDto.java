package com.example.jogosapi.dto;

import com.example.jogosapi.database.models.ItemVenda;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemVendaResponseDto(
        UUID id,
        UUID jogoId,
        String tituloJogo,
        Integer quantidade,
        BigDecimal precoUnitario,
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