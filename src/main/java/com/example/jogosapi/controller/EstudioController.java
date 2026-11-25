package com.example.jogosapi.controller;


import com.example.jogosapi.dto.EstudioDto;
import com.example.jogosapi.dto.EstudioResponseDto;
import com.example.jogosapi.service.EstudioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.UUIDEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Estúdios", description = "Endpoints para gerenciamento de estúdios de jogos")
@RestController
@RequestMapping("/estudios")
@RequiredArgsConstructor
public class EstudioController {

    private final EstudioService estudioService;

    @Operation(summary = "Cadastrar estúdio", description = "Cria um novo estúdio de jogos. Requer perfil ADMIN")
    @PostMapping
    public ResponseEntity<EstudioResponseDto> cadastrar(@RequestBody @Valid EstudioDto estudioDto){
        EstudioResponseDto novoEstudio = estudioService.cadastrar(estudioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEstudio);
    }

    @Operation(summary = "Listar estúdios", description = "Retorna todos os estúdios ativos de forma paginada (rota pública)")
    @GetMapping
    public ResponseEntity<Page<EstudioResponseDto>> buscarTodosEstudios(@PageableDefault(size =  10, sort = "nome",
            direction = Sort.Direction.ASC) Pageable paginacao){
        Page<EstudioResponseDto> pagina = estudioService.listarTodosOsEstudios(paginacao);
        return ResponseEntity.status(HttpStatus.OK).body(pagina);
    }

    @Operation(summary = "Buscar estúdio por ID", description = "Retorna os detalhes de um estúdio específico (rota pública)")
    @GetMapping("/{id}")
    public ResponseEntity<EstudioResponseDto> buscarEstudioPorId(@PathVariable UUID id){
        return  ResponseEntity.status(HttpStatus.OK).body(estudioService.buscarPorId(id));
    }

    @Operation(summary = "Atualizar estúdio", description = "Atualiza os dados de um estúdio existente. Requer perfil ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<EstudioResponseDto> atualizarEstudio(@PathVariable UUID id, @RequestBody @Valid EstudioDto estudioDto){
        EstudioResponseDto atualizarEstudio = estudioService.atualizar(id, estudioDto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizarEstudio);
    }

    @Operation(summary = "Remover estúdio", description = "Realiza a exclusão lógica (soft delete) de um estúdio. Requer perfil ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<EstudioResponseDto> removerEstudio(@PathVariable UUID id){
        estudioService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
