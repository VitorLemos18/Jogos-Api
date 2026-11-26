package com.example.jogosapi.dto;

import com.example.jogosapi.database.models.Venda;
import com.example.jogosapi.enums.StatusRole;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Schema(description = "Recibo completo da venda realizada")
public record VendaResponseDto(
        @Schema(description = "ID único da venda")
        UUID id,
        @Schema(description = "ID do usuário comprador")
        UUID usuarioId,
        @Schema(description = "Nome do usuário comprador", example = "Vitor Lemos")
        String nomeUsuario,
        @Schema(description = "Data em que a venda foi realizada", example = "2026-11-26")
        LocalDate dataCompra,
        @Schema(description = "Status atual da venda", example = "PAGA")
        StatusRole status,
        @Schema(description = "Valor total calculado da compra", example = "499.80")
        BigDecimal valorTotal,
        @Schema(description = "Itens comprados na venda")
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