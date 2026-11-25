package com.example.jogosapi.controller;

import com.example.jogosapi.dto.VendaDto;
import com.example.jogosapi.dto.VendaResponseDto;
import com.example.jogosapi.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Vendas", description = "Endpoints para gerenciamento de vendas")
@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @Operation(summary = "Realizar venda", description = "Registra uma nova compra, valida e abate o estoque dos jogos. Requer usuário autenticado")
    @PostMapping
    public ResponseEntity<VendaResponseDto> realizarVenda(@RequestBody @Valid VendaDto vendaDto) {
        VendaResponseDto novaVenda = vendaService.realizarVenda(vendaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }

    @Operation(summary = "Listar todas as vendas", description = "Retorna todas as vendas paginadas. Requer perfil ADMIN")
    @GetMapping
    public ResponseEntity<Page<VendaResponseDto>> listarTodas(
            @PageableDefault(size = 10, sort = "dataCompra", direction = Sort.Direction.DESC) Pageable paginacao
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(vendaService.listarTodas(paginacao));
    }

    @Operation(summary = "Listar vendas por usuário", description = "Retorna o histórico de compras paginado de um usuário específico. Requer usuário autenticado")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<VendaResponseDto>> listarPorUsuario(
            @PathVariable UUID usuarioId,
            @PageableDefault(size = 10, sort = "dataCompra", direction = Sort.Direction.DESC) Pageable paginacao
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(vendaService.listarPorUsuario(usuarioId, paginacao));
    }

    @Operation(summary = "Buscar venda por ID", description = "Retorna os detalhes e itens de uma venda específica. Requer usuário autenticado")
    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(vendaService.buscarPorId(id));
    }
}
