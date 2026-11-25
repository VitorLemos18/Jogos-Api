package com.example.jogosapi.dto;

import com.example.jogosapi.database.models.Venda;
import com.example.jogosapi.enums.StatusRole;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public record VendaResponseDto(
        UUID id,
        UUID usuarioId,
        String nomeUsuario,
        LocalDate dataCompra,
        StatusRole status,
        BigDecimal valorTotal,
        List<ItemVendaResponseDto> itens
) {
    public VendaResponseDto(Venda venda) {
        this(
                venda.getId(),
                venda.getUsuario().getId(),
                venda.getUsuario().getNome(),
                venda.getDataCompra(),
                venda.getStatus(),
                venda.getValor_total(),
                venda.getItens() != null
                        ? venda.getItens().stream().map(ItemVendaResponseDto::new).toList()
                        : Collections.emptyList()
        );
    }
}